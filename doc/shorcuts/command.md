# grpc compile
  protoc --go_out=plugins=grpc:.  user.proto

# python
  python -m SimpleHTTPServer 8894
  python3 -m pip install yaml  --trusted-host=pypi.python.org --trusted-host=pypi.org --trusted-host=files.pythonhosted.org

# https cert
./letsencrypt-auto certonly --standalone --email xxx@xxx.com -d localhost -d 127.0.0.1
