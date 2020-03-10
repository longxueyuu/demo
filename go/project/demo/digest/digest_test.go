package digest

import (
	"log"
	"strings"
	"testing"
)

func GetMFixedRandoms(s string, m, n int) []int {
	result := make([]int, 0, m)
	for i := 1; i <= m; i++ {
		v := strings.Repeat(s, i)
		p := int(Murmur3(v) % uint64(n))

		result = append(result, p)
	}

	return result
}

func TestFixedRandoms(t *testing.T) {
	s := "abcd"
	index := GetMFixedRandoms(s, 3, 10)
	log.Printf("index=%v", index)
}

func TestMD5(t *testing.T) {
	s := "abcd"
	a := MD5(s)
	b := SHA256(s)
	log.Printf("a=%v b=%v", a, b)

	s = "secret"
	x := SHA256(s)
	log.Printf("x=%v", x)
}
