package util

import (
	"encoding/base64"
	"fmt"
	"github.com/test/project/demo/digest"
	"golang.org/x/crypto/bcrypt"
	"log"
	"math/rand"
	"testing"
	"time"
)

func SampleByHash(s string, m, n int) []int {
	if m > n {
		m = n
	}
	mh := int64(digest.Murmur3(s))
	r := rand.New(rand.NewSource(mh))
	result := r.Perm(n)
	return result[0:m]
}

func TestFixedRand(t *testing.T) {
	uid := "abcd"
	ids := SampleByHash(uid, 2, 5)
	fmt.Println(ids)
}

func TestRand(t *testing.T) {
	id := "abc"
	r := rand.New(rand.NewSource(int64(digest.Murmur3(id))))
	start := time.Now()
	for i := 0; i < 100; i++ {
		r.Intn(10000)
		// fmt.Println(x)
	}
	end := time.Since(start)
	fmt.Println(end)
}

func BenchmarkRand(b *testing.B) {
	b.ReportAllocs()
	for i := 0; i < b.N; i++ {
		rand.Intn(1000)
	}
}

func TestBcrpt(t *testing.T) {
	x := "abc"
	hash, err := bcrypt.GenerateFromPassword([]byte(x), bcrypt.DefaultCost)
	if err != nil {
		t.Fatal(err)
	}

	v := base64.StdEncoding.EncodeToString(hash)
	log.Printf("v=%v", v)
}
