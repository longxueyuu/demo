package util

import (
	"log"
	"testing"
)

type UnionFind struct {
	node2Parent       map[string]string
	nodeParent2Weight map[string]float32
}

func (uf *UnionFind) FindRoot(x string) string {
	if x != uf.node2Parent[x] {
		p := uf.node2Parent[x]

		uf.node2Parent[x] = uf.FindRoot(p)
		uf.nodeParent2Weight[x] *= uf.nodeParent2Weight[p]

		return uf.node2Parent[x]
	}

	return x
}

/*
x    -->    rootx

y    -->    rooty
*/
func (uf *UnionFind) Insert(x, y string, value float32) {
	rootX := uf.FindRoot(x)
	rootY := uf.FindRoot(y)
	if rootX != rootY {
		uf.node2Parent[rootX] = rootY
		uf.nodeParent2Weight[rootX] = value * uf.nodeParent2Weight[y] / uf.nodeParent2Weight[x]
	}
}

func (uf *UnionFind) GetV(x, y string) float32 {
	xv := uf.FindRoot(x)
	yv := uf.FindRoot(y)
	if xv != yv {
		return -1
	}

	return uf.nodeParent2Weight[x] / uf.nodeParent2Weight[y]
}

type Div struct {
	UF *UnionFind
}

func NewDiv(eqs [][]string, values []float32) *Div {
	uf := &UnionFind{
		node2Parent:       make(map[string]string),
		nodeParent2Weight: make(map[string]float32),
	}
	for i, keys := range eqs {
		x := keys[0]
		y := keys[1]
		if _, ok := uf.node2Parent[x]; !ok {
			uf.node2Parent[x] = x
			uf.nodeParent2Weight[x] = 1.0
		}

		if _, ok := uf.node2Parent[y]; !ok {
			uf.node2Parent[y] = y
			uf.nodeParent2Weight[y] = 1.0
		}

		uf.Insert(x, y, values[i])
	}

	return &Div{UF: uf}
}

func (d *Div) Get(x, y string) float32 {
	return d.UF.GetV(x, y)
}

func TestDiv(t *testing.T) {
	eqs := [][]string{
		{"a", "b"},
		{"b", "c"},
		{"d", "c"},
		{"e", "c"},

		{"f", "g"},
		{"h", "g"},
		{"m", "h"},
		{"n", "h"},

		{"x", "y"},
	}
	values := []float32{2, 2, 4, 8, 10, 20, 30, 40, 50}

	div := NewDiv(eqs, values)

	v1 := div.Get("e", "a")
	v2 := div.Get("f", "m")
	v3 := div.Get("y", "x")
	v4 := div.Get("n", "g")

	log.Printf("v1=%v v2=%v v3=%v v4=%v", v1, v2, v3, v4)
	log.Printf("div=%+v ", *div.UF)
}

func TestPerm(t *testing.T) {
	nums := []int{1, 2, 2, 2}
	n := len(nums)

	res := make([][]int, 0)
	p := make([]int, n, n)
	visited := make([]bool, n, n)
	for i, _ := range nums {
		p[i] = -1
	}

	Perm(nums, 0, visited, p, &res)

	for i, x := range res {
		log.Printf("%v %v", i, x)
	}
}

func Perm(nums []int, pos int, visited []bool, p []int, res *[][]int) {
	if pos == len(nums) {
		*res = append(*res, append([]int(nil), p...))
		return
	}

	for i := 0; i < len(nums); i++ {
		if visited[i] || i > 0 && nums[i] == nums[i-1] && !visited[i-1] {
			continue
		}
		p[pos] = nums[i]
		visited[i] = true
		Perm(nums, pos+1, visited, p, res)
		visited[i] = false
		p[pos] = -1
	}
}

func TestBracket(t *testing.T) {
	res := make([]string, 0)
	backBracket(&res, "", 0, 0, 10)
	for i, x := range res {
		log.Printf("%v %v", i, x)
	}
}

func backBracket(res *[]string, cur string, l, r, n int) {
	if r == n {
		*res = append(*res, cur)
	}

	if l < n {
		backBracket(res, cur+"(", l+1, r, n)
	}

	if r < l {
		backBracket(res, cur+")", l, r+1, n)
	}
}

func TestBackStr(t *testing.T) {
	maxSub := backStr("abcdefg")
	log.Printf("maxsub=%v", maxSub)
}

func backStr(s string) string {
	var (
		max int
		sub string
	)

	n := len(s)
	dp := make([][]int, n, n)
	for i := 0; i < n; i++ {
		dp[i] = make([]int, n, n)
	}
	for k := 0; k < n-1; k++ {
		j := k
		for i := 0; i < n-1 && j < n; i++ {
			if s[i] == s[j] {
				if j-i < 3 {
					dp[i][j] = 1
				} else {
					dp[i][j] = dp[i+1][j-1]
				}
			}

			if dp[i][j] == 1 && j-i+1 > max {
				max = j - i + 1
				sub = s[i : j+1]
			}
			j++
		}
	}
	for i, x := range dp {
		log.Printf("%v %v", i, x)
	}

	return sub
}

func TestLongBracket(t *testing.T) {
	s := "(())()()()((((((((())))()()()()))))))))(((((()))"

	l := GetMaxLen(s)

	log.Printf("slen=%v l=%v", len(s), l)
}

func GetMaxLen(s string) int {
	var max int

	stack := []int{}

	for i, x := range s {
		if x == '(' {
			stack = append(stack, i)
		} else {
			if len(stack) == 0 || s[stack[len(stack)-1]] != '(' {
				stack = append(stack, i)
			} else {
				stack = stack[0 : len(stack)-1]
			}
		}
	}

	pos := len(s)
	for len(stack) > 0 {
		top := stack[len(stack)-1]
		l := pos - top - 1
		if l > max {
			max = l
		}

		pos = top

		stack = stack[0 : len(stack)-1]
	}

	if pos > max {
		max = pos
	}

	return max
}

func TestBS(t *testing.T) {
	arr := []int{1, 1, 1, 1, 1, 1, 1, 2, 3, 3, 3, 3, 3, 4, 6, 7}
	target := 5

	l := BSLeft(arr, target)
	r := BSRight(arr, target)

	if l <= r && arr[l] == arr[r] && arr[l] == target {
		log.Printf("ok: l=%v r=%v", l, r)
	} else {
		log.Printf("not found: l=%v r=%v", l, r)
	}

}

func BSLeft(nums []int, target int) int {
	low := 0
	high := len(nums) - 1

	for low < high {
		mid := low + (high-low)/2
		if nums[mid] >= target {
			high = mid
		} else {
			low = mid + 1
		}
	}

	return low
}

func BSRight(nums []int, target int) int {
	low := 0
	high := len(nums) - 1

	for low < high {
		mid := low + (high-low)/2 + 1
		if nums[mid] <= target {
			low = mid
		} else {
			high = mid - 1
		}
	}

	return low
}

func TestBS2(t *testing.T) {
	arr := []int{1, 2, 2, 2, 3, 4, 8, 10}
	target := 5

	l := BS(arr, target, true)
	r := BS(arr, target, false)

	if l <= r && r < len(arr) && arr[l] == arr[r] && arr[l] == target {
		log.Printf("ok: l=%v r=%v", l, r)
	} else {
		log.Printf("not found: l=%v r=%v", l, r)
	}

}

func BS(nums []int, target int, lower bool) int {
	low := 0
	high := len(nums) - 1
	x := len(nums)

	for low <= high {
		mid := low + (high-low)/2
		if nums[mid] > target || (lower && nums[mid] >= target) {
			high = mid - 1
			x = mid
		} else {
			low = mid + 1
		}
	}

	if !lower {
		return x - 1
	}

	return x
}

func TestCoverStr(t *testing.T) {
	s := "abcdefabcdefabcdef"
	tt := "aaabbbcccdddeee"

	sub := MinCover(s, tt)
	log.Printf("sub=%v", sub)
}

func MinCover(s string, t string) string {
	var (
		char2Cnt = make(map[rune]int)
		left     = 0
		right    = 0

		min   = len(s) + 1
		start = -1
	)

	for _, c := range t {
		char2Cnt[c] += 1
	}

	charNotInWindowCnt := len(char2Cnt)
	for i, c := range s {
		right = i
		if _, ok := char2Cnt[c]; !ok {
			continue
		}

		char2Cnt[c] -= 1
		if char2Cnt[c] == 0 {
			charNotInWindowCnt--
		}

		for charNotInWindowCnt == 0 {
			l := right - left + 1
			if l < min {
				min = l
				start = left
			}

			deleteChar := rune(s[left])
			left++

			if _, ok := char2Cnt[deleteChar]; !ok {
				continue
			}

			char2Cnt[deleteChar]++
			if char2Cnt[deleteChar] > 0 {
				charNotInWindowCnt++
			}
		}
	}
	if start == -1 {
		return "not found"
	}

	return s[start : start+min]
}
