package main

import (
	"fmt"
	"strconv"
)

const (
	OpAnd = "and"
	OpOr  = "or"
)

type Node struct {
	ID       string
	AndOr    string
	Left     *Node
	Right    *Node
	ChildIDs []string
}

/*

	expr = expr "or" term | term
	term = term "and" factor | factor

	factor = number, {number} | "(" expr ")"
	number = "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9"

				||
				||
		       	\/

	A = Aα | β

	A = βR
	R = αR | ε

	β = term
	α = "or" term
	expr =  term expr_r
	expr_r = "or" term expr_r | ε

	β = factor
	α = "and" factor
	term = factor term_r
	term_r = "and" factor term_r | ε

	factor = number, {number} | "(" expr ")"
	number = "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" | "8" | "9"
*/

type Parser struct {
	Tokenizer  *Tokenizer
	NextNodeID int64
}

func NewParser(tokenizer *Tokenizer) *Parser {
	return &Parser{
		Tokenizer:  tokenizer,
		NextNodeID: 1,
	}
}

func (p *Parser) GetNodeID() string {
	x := p.NextNodeID
	p.NextNodeID++
	return strconv.FormatInt(x, 10)
}

func (p *Parser) Parse() *Node {
	p.Tokenizer.Next()
	return p.Expr()
}

func (p *Parser) Expr() *Node {
	value := p.Term()
	root := p.ExprR(value)
	return root
}

func (p *Parser) ExprR(leftValue *Node) *Node {
	if OpOr == p.Tokenizer.LookAhead().Value {
		p.Tokenizer.Match(p.Tokenizer.LookAhead())

		rightValue := p.Term()

		pNode := &Node{
			ID:       p.GetNodeID(),
			AndOr:    OpOr,
			Left:     leftValue,
			Right:    rightValue,
			ChildIDs: []string{leftValue.ID, rightValue.ID},
		}

		return p.ExprR(pNode)
	} else {
		return leftValue
	}
}

func (p *Parser) Term() *Node {
	leftValue := p.Factor()
	parent := p.TermR(leftValue)
	return parent
}

func (p *Parser) TermR(leftValue *Node) *Node {
	if OpAnd == p.Tokenizer.LookAhead().Value {
		p.Tokenizer.Match(p.Tokenizer.LookAhead())

		rightValue := p.Factor()

		pNode := &Node{
			ID:       p.GetNodeID(),
			AndOr:    OpAnd,
			Left:     leftValue,
			Right:    rightValue,
			ChildIDs: []string{leftValue.ID, rightValue.ID},
		}

		return p.TermR(pNode)
	} else {
		return leftValue
	}
}

func (p *Parser) Factor() *Node {
	if "(" == p.Tokenizer.LookAhead().Value {
		p.Tokenizer.Match(p.Tokenizer.LookAhead())
		node := p.Expr()
		p.Tokenizer.Match(&Token{Type: TypeTokenBracketRight, Value: ")"})
		return node
	} else if p.Tokenizer.LookAhead().Type == TypeTokenNumber {
		tokenValue := p.Tokenizer.LookAhead().Value
		p.Tokenizer.Match(p.Tokenizer.LookAhead())
		node := &Node{ID: tokenValue}
		return node
	} else {
		panic(fmt.Sprintf("syntax: current %v, expect ( or number", p.Tokenizer.LookAhead().Value))
	}
}
