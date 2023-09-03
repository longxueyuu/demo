package resolvers

import (
	"google.golang.org/grpc/resolver"
	"strings"
)

func init() {
	resolver.Register(new(TestBuilder))
}

type TestBuilder struct{}

func (b *TestBuilder) Build(target resolver.Target, cc resolver.ClientConn, opts resolver.BuildOptions) (resolver.Resolver, error) {
	r := &TestResolver{target: target, clientConn: cc}
	go r.start()
	return r, nil
}

func (b *TestBuilder) Scheme() string {
	return "demo"
}

type TestResolver struct {
	target     resolver.Target
	clientConn resolver.ClientConn
}

func (r *TestResolver) ResolveNow(resolver.ResolveNowOptions) {
	r.start()
}

func (r *TestResolver) start() {
	addrs := make([]resolver.Address, 0)
	if r.target.Endpoint() == "" {
		return
	}

	raws := strings.Split(r.target.Endpoint(), ",")
	for _, x := range raws {
		addrs = append(addrs, resolver.Address{Addr: x})
	}

	r.clientConn.UpdateState(resolver.State{
		Addresses:     addrs,
		ServiceConfig: nil,
		Attributes:    nil,
	})
}

func (r *TestResolver) Close() {}
