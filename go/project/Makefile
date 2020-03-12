LINUX=GOOS=linux GOARCH=amd64
MAC=GOOS=darwin GOARCH=amd64

build: demoapi

.PHONY: demoapi demoapi_mac

clean:
	go clean github.com/test/project/cmd/demoapi

demoapi:
	${LINUX} go build -o ./demoapi.linux-amd64 github.com/test/project/cmd/demoapi

demoapi_mac:
	${MAC} go build -o ./demoapi.darwin-amd64 github.com/test/project/cmd/demoapi

