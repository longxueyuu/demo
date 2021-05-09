package main

import "log"

func main() {
	s := "(100 and (200 or 20))"
	tz := NewTokenizer(s)

	parser := NewParser(tz)
	root := parser.Parse()
	LevelTraverse(root)
}

func LevelTraverse(root *Node) {
	q := make([]*Node, 0)
	q = append(q, root)

	for len(q) != 0 {

		for _, x := range q {
			log.Printf("%v(%v)(%v)", x.ID, x.AndOr, x.ChildIDs)
		}

		n := len(q)
		lns := make([]*Node, 0)

		for i := 0; i < n; i++ {
			node := q[i]
			if node.Left != nil {
				lns = append(lns, node.Left)
			}

			if node.Right != nil {
				lns = append(lns, node.Right)
			}
		}

		q = lns
	}
}
