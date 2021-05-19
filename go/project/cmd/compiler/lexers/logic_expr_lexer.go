package lexers

import (
	"fmt"
	"github.com/bbuck/go-lexer"
	"strings"
	"unicode"
)

const (
	TokenUnknown lexer.TokenType = iota
	TokenNumber
	TokenBracketLeft
	TokenBracketRight
	TokenAnd
	TokenOr
)

func ErrHandler(e string) {
	panic(e)
}

func StartState(l *lexer.L) lexer.StateFunc {
	r := l.Next()
	if r == lexer.EOFRune {
		return nil
	}

	if unicode.IsDigit(r) {
		l.Rewind()
		return NumberState
	}

	switch r {
	case ' ', '\t', '\n':
		l.Ignore()
	case '(':
		l.Emit(TokenBracketLeft)
	case ')':
		l.Emit(TokenBracketRight)
	case 'a', 'A':
		l.Rewind()
		return AndState
	case 'o', 'O':
		l.Rewind()
		return OrState
	default:
		l.Error(fmt.Sprintf("unknown token: %s", string(r)))
		return nil
	}

	return StartState
}

func NumberState(l *lexer.L) lexer.StateFunc {
	l.Take("0123456789")
	l.Emit(TokenNumber)
	return StartState
}

func AndState(l *lexer.L) lexer.StateFunc {
	r := l.Next()
	for unicode.IsLetter(r) {
		r = l.Next()
	}
	l.Rewind()
	if strings.ToLower(l.Current()) != "and" {
		l.Error("and expected")
		return nil
	}

	l.Emit(TokenAnd)
	return StartState
}

func OrState(l *lexer.L) lexer.StateFunc {
	r := l.Next()
	for unicode.IsLetter(r) {
		r = l.Next()
	}
	l.Rewind()
	if strings.ToLower(l.Current()) != "or" {
		l.Error("or expected")
		return nil
	}

	l.Emit(TokenOr)
	return StartState
}
