## install dlv
go get -u github.com/go-delve/delve/cmd/dlv

## compile
  go build -gcflags "all=-N -l" ${go_package_name}

## run
  dlv --listen=:2345 --headless=true --api-version=2 --accept-multiclient exec ${go_executable_bin}

## kill dlv
  ps -ef | grep "dlv" | grep -v grep | awk '{print $2}' | xargs kill -9

## code structure
  go-callvis -minlen 4  -group type,pkg  -include ${go_package_name},${go_package_name} ${go_package_name
  http://localhost:7878
