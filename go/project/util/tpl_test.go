package util

import (
	"html/template"
	"os"
	"testing"
)

type Ctx struct {
	Name string
}

func (c *Ctx) Test(a, b string) string {
	return a + b
}

func TestTPL(t *testing.T) {
	str := `
		header

		{{ .Test "a" "b" }}
			if
		{{ if or (eq .Name "tom" ) }}
            else if
		{{ else }}
			else
		{{ end }}

		trailer
		`

	tpl, err := template.New("str").Parse(str)
	if err != nil {
		panic(err)
	}

	ctx := &Ctx{Name: "tom"}
	err = tpl.Execute(os.Stdout, ctx)
	if err != nil {
		panic(err)
	}
}
