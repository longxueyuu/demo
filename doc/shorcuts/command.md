# grpc compile
  protoc --go_out=plugins=grpc:.  user.proto

# python
  python -m SimpleHTTPServer 8894

# https cert
./letsencrypt-auto certonly --standalone --email xxx@xxx.com -d localhost -d 127.0.0.1
