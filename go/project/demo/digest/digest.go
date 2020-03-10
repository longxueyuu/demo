package digest

import (
	"crypto/md5"
	"crypto/sha256"
	"encoding/hex"
	"github.com/spaolacci/murmur3"
)

func MD5(s string) string {
	m := md5.New()
	m.Write([]byte(s))
	return hex.EncodeToString(m.Sum(nil))
}

func SHA256(s string) string {
	h := sha256.New()
	h.Write([]byte(s))
	return hex.EncodeToString(h.Sum(nil))
}

func Murmur3(s string) uint64 {
	mur := murmur3.New64()
	mur.Write([]byte(s))
	return mur.Sum64()
}
