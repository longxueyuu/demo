package main

func main() {
	var (
		a float64 = 100.5
		b float64 = 3.1
		c float64 = 3.2
		d float64 = 3.3
		e float64 = 3.4
	)
	z1 := a + b
	z2 := c + d
	z3 := z1 + z2 + e
	z := z1 + z2 + z3

	println(z1, z)
}
