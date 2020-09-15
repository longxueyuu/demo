package main

import (
	"crypto/ecdsa"
	"crypto/elliptic"
	"crypto/rand"
	"crypto/sha256"
	"crypto/x509"
	"encoding/asn1"
	"encoding/base64"
	"encoding/hex"
	"encoding/pem"
	"errors"
	"log"
	"math/big"
	"os"
)

//生成密钥对
func generateECCKeyPair(c elliptic.Curve, privateKeyFile string, publicKeyFile string) error {
	//使用ECDSA生成密钥对
	privateKey, err := ecdsa.GenerateKey(c, rand.Reader)
	if err != nil {
		return err
	}
	//使用509
	private, err := x509.MarshalPKCS8PrivateKey(privateKey)
	if err != nil {
		return err
	}
	//pem
	block := pem.Block{
		Type:  "PRIVATE KEY",
		Bytes: private,
	}
	file, err := os.Create(privateKeyFile)
	if err != nil {
		return err
	}
	err = pem.Encode(file, &block)
	if err != nil {
		return err
	}
	file.Close()

	//处理公钥
	public := privateKey.PublicKey

	//x509序列化
	publicKey, err := x509.MarshalPKIXPublicKey(&public)
	if err != nil {
		return err
	}
	//pem
	public_block := pem.Block{
		Type:  "ecdsa public key",
		Bytes: publicKey,
	}
	file, err = os.Create(publicKeyFile)
	if err != nil {
		return err
	}
	//pem编码
	err = pem.Encode(file, &public_block)
	if err != nil {
		return err
	}
	return nil
}

type ECDSASignature struct {
	R *big.Int
	S *big.Int
}

func GetFileInBytes(filePath string) ([]byte, error) {
	file, err := os.Open(filePath)
	defer file.Close()
	if err != nil {
		return nil, err
	}
	info, err := file.Stat()
	if err != nil {
		return nil, err
	}
	buf := make([]byte, info.Size())
	_, err = file.Read(buf)
	if err != nil {
		return nil, err
	}
	return buf, nil
}

func GenEcdsaSignature(data []byte, privateKey []byte) ([]byte, error) {
	block, _ := pem.Decode(privateKey)
	p, err := x509.ParsePKCS8PrivateKey(block.Bytes)
	if err != nil {
		return nil, err
	}

	pkcs8Key, ok := p.(*ecdsa.PrivateKey)
	if !ok {
		return nil, errors.New("illegal ecdsa private key")
	}

	hashText := sha256.Sum256(data)

	r, s, err := ecdsa.Sign(rand.Reader, pkcs8Key, hashText[:])
	if err != nil {
		return nil, err
	}

	sig, err := asn1.Marshal(ECDSASignature{R: r, S: s})
	if err != nil {
		return nil, err
	}

	return sig, nil
}

func VerifyEcdsaSignature(r, s *big.Int, data []byte, publicKey []byte) bool {
	block, _ := pem.Decode(publicKey)
	pub, err := x509.ParsePKIXPublicKey(block.Bytes)
	if err != nil {
		return false
	}
	pkcs8Key, ok := pub.(*ecdsa.PublicKey)
	if !ok {
		return false
	}
	hashText := sha256.Sum256(data)
	res := ecdsa.Verify(pkcs8Key, hashText[:], r, s)
	return res
}

func main() {
	// 生成秘钥对
	//generateECCKeyPair(elliptic.P256(), "./private.pem", "./public.pem")
	data := []byte("hello world")

	// 生成签名
	privateBuf, err := GetFileInBytes("./private.p8")
	if err != nil {
		log.Printf("GetFileInBytes: err=%v", err)
		return
	}

	b, err := GenEcdsaSignature(data, privateBuf)
	sig := base64.StdEncoding.EncodeToString(b)
	if err != nil {
		log.Printf("GenEcdsaSignature: err=%v", err)
		return
	}
	log.Printf("GenEcdsaSignature: info, sig=%v hex=%v", sig, hex.EncodeToString(b))

	// 验证签名
	publicBuf, err := GetFileInBytes("./public.pem")
	if err != nil {
		log.Printf("GetFileInBytes: err=%v", err)
		return
	}

	b, _ = base64.StdEncoding.DecodeString(sig)
	x := ECDSASignature{}
	_, err = asn1.Unmarshal(b, &x)
	if err != nil {
		log.Printf("DecodeString: err=%v", sig)
		return
	}
	res := VerifyEcdsaSignature(x.R, x.S, data, publicBuf)
	if res {
		log.Printf("Signature is Accepted")
	} else {
		log.Printf("Signature is rejected")
	}
}
