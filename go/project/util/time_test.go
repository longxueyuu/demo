package util

import (
	"log"
	"testing"
	"time"
)

func TestDuration(t *testing.T) {
	dayInMillis := 24 * time.Hour.Milliseconds()

	day8 := 8 * dayInMillis
	day25 := 25 * dayInMillis
	day75 := 75 * dayInMillis

	log.Printf("day8=%v day25=%v day75=%v", day8, day25, day75)
}
