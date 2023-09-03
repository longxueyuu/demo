package util

import (
	"flag"
	"fmt"
	"log"
	"testing"
	"time"
)

var (
	custom = flag.String("custom", "", "test only")
)

func TestDuration(t *testing.T) {
	dayInMillis := 24 * time.Hour.Milliseconds()

	day8 := 8 * dayInMillis
	day25 := 25 * dayInMillis
	day75 := 75 * dayInMillis

	time.Time{}.UnixMilli()

	log.Printf("day8=%v day25=%v day75=%v ts=%v", day8, day25, day75, time.Time{}.UnixMilli())

	tk := time.NewTicker(10 * time.Second)
	tk.Stop()
	tk.Stop()
	<-tk.C
}

func TestZeroDay(t *testing.T) {
	d := time.Date(2022, 2, 1, 0, 0, 0, 0, time.Local)
	log.Printf("d=%v", d)

	m := make(map[string]map[int]struct{})

	subType2Empty, ok := m["a"]
	if !ok {
		subType2Empty = map[int]struct{}{}
	}
	subType2Empty[0] = struct{}{}
	m["a"] = subType2Empty

	subType2Empty, ok = m["a"]
	if !ok {
		subType2Empty = map[int]struct{}{}
	}
	subType2Empty[1] = struct{}{}
	m["a"] = subType2Empty

	log.Printf("m=%v", m)
}

func TestMain(m *testing.M) {
	flag.Parse()
	log.Printf("test TestMain flag, custom=%v", *custom)
	m.Run()
}

func TestMillis(t *testing.T) {
	s := time.Date(2019, 11, 1, 12, 0, 0, 0, time.Local)
	log.Printf("millis=%v", s.UnixNano())

	ft := time.UnixMilli(1633013998441)
	log.Printf("ft=%v", ft)
}

func TestRFC3339(t *testing.T) {
	s := "2023-06-11T07:04:34.309Z"
	s2 := "2014-10-02T15:01:23.045123456Z"

	t1, err := time.Parse(time.RFC3339, s)
	if err != nil {
		panic(err)
	}
	log.Printf("t1=%v ms=%v", t1, t1.UnixMilli())
	t2, err := time.Parse(time.RFC3339, s2)
	if err != nil {
		panic(err)
	}
	log.Printf("t2=%v ms=%v", t2, t2.UnixMilli())
}

func TestTime(t *testing.T) {
	test4 := time.Now().AddDate(0, 1, 0)
	fmt.Println(time.Now())
	fmt.Println(time.Date(test4.Year(), test4.Month(), 1, 0, 0, 0, 0, time.Local))
}
