package util

import (
	"fmt"
	"github.com/test/project/demo/digest"
	"math/rand"
	"testing"
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
