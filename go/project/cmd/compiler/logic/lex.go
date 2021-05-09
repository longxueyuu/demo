package main

import (
	"fmt"
	"strconv"
)

const (
	TypeTokenUnknown      = 0
	TypeTokenNumber       = 1
	TypeTokenBracketLeft  = 2
	TypeTokenBracketRight = 3
	TypeTokenAnd          = 4
	TypeTokenOr           = 5
)

type Token struct {
	Type  int
	Value string
}

func (t *Token) IsEqual(token *Token) bool {
	if t == token {
		return true
	}

	if t == nil {
		return false
	}

	if t.Type == token.Type && t.Value == token.Value {
		return true
	}

	return false
}

type Tokenizer struct {
	Tokens  []*Token
	Current int
}

func (tz *Tokenizer) HasNext() bool {
	if tz.Current == len(tz.Tokens)-1 {
		return false
	}

	return true
}

func (tz *Tokenizer) LookAhead() *Token {
	return tz.Tokens[tz.Current]
}

func (tz *Tokenizer) Next() *Token {
	if tz.Current == len(tz.Tokens)-1 {
		return nil
	}

	tz.Current++
	return tz.Tokens[tz.Current]
}

func (tz *Tokenizer) Match(token *Token) {
	if !tz.LookAhead().IsEqual(token) {
		panic(fmt.Sprintf("syntax: %v expected, %v found", token.Value, tz.LookAhead().Value))
	}

	tz.Next()
}

func NewTokenizer(s string) *Tokenizer {
	t := &Tokenizer{Current: -1}
	if s == "" {
		return t
	}

	t.Tokens = InitTokens(s)
	return t
}

func InitTokens(s string) []*Token {
	if s == "" {
		return []*Token{}
	}

	ts := make([]*Token, 0)

	for i := 0; i < len(s); i++ {
		switch rune(s[i]) {
		case ' ', '\t', '\n':
			continue
		case '0', '1', '2', '3', '4', '5', '6', '7', '8', '9':
			n, _ := strconv.ParseInt(string(s[i]), 10, 64)
			i++
			for i < len(s) {
				if s[i] < '0' || s[i] > '9' {
					break
				}
				x, _ := strconv.ParseInt(string(s[i]), 10, 64)
				n = 10*n + x
				i++
			}
			i--

			t := &Token{Type: TypeTokenNumber, Value: strconv.FormatInt(n, 10)}
			ts = append(ts, t)
		case '(':
			t := &Token{Type: TypeTokenNumber, Value: string(s[i])}
			ts = append(ts, t)
		case ')':
			t := &Token{Type: TypeTokenBracketRight, Value: string(s[i])}
			ts = append(ts, t)
		case 'a', 'A':
			var and string
			for i < len(s) && s[i] >= 'A' && s[i] <= 'z' {
				and += string(s[i])
				i++
			}
			i--

			if and != "and" {
				panic("syntax: and expected")
			}

			t := &Token{Type: TypeTokenAnd, Value: and}
			ts = append(ts, t)
		case 'o', 'O':
			var or string
			for i < len(s) && s[i] >= 'A' && s[i] <= 'z' {
				or += string(s[i])
				i++
			}
			i--

			if or != "or" {
				panic("syntax: or expected")
			}
			t := &Token{Type: TypeTokenOr, Value: or}
			ts = append(ts, t)
		default:
			panic("syntax err")
		}
	}

	return ts
}
