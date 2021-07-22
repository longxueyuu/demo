package lexers

import (
	"fmt"
	"github.com/bbuck/go-lexer"
	"testing"
)

func TestLogicLexer(t *testing.T) {
	s := "(1 and 2) or (30000 and 4) and 500"
	l := lexer.New(s, StartState)
	l.ErrorHandler = ErrHandler
	l.Start()

	for token, ok := l.NextToken(); !ok; token, ok = l.NextToken() {
		fmt.Println(token)
	}
}
