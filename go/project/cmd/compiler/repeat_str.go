package main

import (
	"log"
	"strconv"
	"strings"
)

/*
	expr = expr st | st
	st = digit "[" expr "]" | digit char | char

	expr = st term
	term = st term | e
	st = digit "[" expr "]" | digit char | char

*/

type Tokens struct {
	tokens  []string
	current int
}

func (to *Tokens) Next() string {
	if to.current >= len(to.tokens) {
		return ""
	}

	s := to.tokens[to.current]
	to.current++
	return s
}

func (to *Tokens) Peek() string {
	if to.current >= len(to.tokens) {
		return ""
	}

	s := to.tokens[to.current]
	return s
}

func Tokenize(pattern string) *Tokens {
	ts := &Tokens{
		tokens:  make([]string, 0),
		current: 0,
	}

	i := 0
	for i < len(pattern) {
		ts.tokens = append(ts.tokens, pattern[i:i+1])
		i++
	}
	return ts
}

func main() {
	pattern := "bcd2a3[xy2[m2n]]"
	tokens := Tokenize(pattern)

	s := Expr(tokens)
	log.Printf("s=%v", s)
}

func Expr(tokens *Tokens) string {
	p := st(tokens)
	t := term(tokens)

	return p + t
}

func st(tokens *Tokens) string {
	s := tokens.Next()

	if IsAlpha(s) {
		return s
	}

	if !IsNumber(s) {
		panic("number required")
	}
	v, _ := strconv.ParseInt(s, 10, 32)

	peek := tokens.Peek()
	if peek == "[" {
		tokens.Next()
		t := Expr(tokens)
		next := tokens.Next()
		if next != "]" {
			panic("] required")
		}

		return strings.Repeat(t, int(v))
	}

	t := tokens.Next()

	return strings.Repeat(t, int(v))
}

func term(tokens *Tokens) string {
	peek := tokens.Peek()
	if peek == "" || (!IsNumber(peek) && !IsAlpha(peek)) {
		return ""
	}

	p := st(tokens)
	t := term(tokens)
	return p + t
}

func IsAlpha(s string) bool {
	return (s[0] >= 'a' && s[0] <= 'z') || (s[0] >= 'A' && s[0] <= 'Z')
}

func IsNumber(s string) bool {
	return s[0] >= '1' && s[0] <= '9'
}
