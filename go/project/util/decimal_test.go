package util

import (
	"github.com/shopspring/decimal"
	"log"
	"testing"
)

func TestDecimal(t *testing.T) {
	a := decimal.NewFromInt(300)
	b := decimal.NewFromInt(7)
	c := a.Div(b)

	d := c.Floor().IntPart()
	log.Printf("c=%v d=%v", c, d)
}
