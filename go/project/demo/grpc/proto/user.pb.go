// Code generated by protoc-gen-go. DO NOT EDIT.
// source: user.proto

package user

import (
	context "context"
	fmt "fmt"
	proto "github.com/golang/protobuf/proto"
	grpc "google.golang.org/grpc"
	codes "google.golang.org/grpc/codes"
	status "google.golang.org/grpc/status"
	math "math"
)

// Reference imports to suppress errors if they are not otherwise used.
var _ = proto.Marshal
var _ = fmt.Errorf
var _ = math.Inf

// This is a compile-time assertion to ensure that this generated file
// is compatible with the proto package it is being compiled against.
// A compilation error at this line likely means your copy of the
// proto package needs to be updated.
const _ = proto.ProtoPackageIsVersion3 // please upgrade the proto package

// The request message containing the user's name.
type HelloRequest struct {
	Name                 string   `protobuf:"bytes,1,opt,name=name,proto3" json:"name,omitempty"`
	XXX_NoUnkeyedLiteral struct{} `json:"-"`
	XXX_unrecognized     []byte   `json:"-"`
	XXX_sizecache        int32    `json:"-"`
}

func (m *HelloRequest) Reset()         { *m = HelloRequest{} }
func (m *HelloRequest) String() string { return proto.CompactTextString(m) }
func (*HelloRequest) ProtoMessage()    {}
func (*HelloRequest) Descriptor() ([]byte, []int) {
	return fileDescriptor_116e343673f7ffaf, []int{0}
}

func (m *HelloRequest) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_HelloRequest.Unmarshal(m, b)
}
func (m *HelloRequest) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_HelloRequest.Marshal(b, m, deterministic)
}
func (m *HelloRequest) XXX_Merge(src proto.Message) {
	xxx_messageInfo_HelloRequest.Merge(m, src)
}
func (m *HelloRequest) XXX_Size() int {
	return xxx_messageInfo_HelloRequest.Size(m)
}
func (m *HelloRequest) XXX_DiscardUnknown() {
	xxx_messageInfo_HelloRequest.DiscardUnknown(m)
}

var xxx_messageInfo_HelloRequest proto.InternalMessageInfo

func (m *HelloRequest) GetName() string {
	if m != nil {
		return m.Name
	}
	return ""
}

// The response message containing the greetings
type HelloReply struct {
	Message              string            `protobuf:"bytes,1,opt,name=message,proto3" json:"message,omitempty"`
	Kv                   map[string]string `protobuf:"bytes,2,rep,name=kv,proto3" json:"kv,omitempty" protobuf_key:"bytes,1,opt,name=key,proto3" protobuf_val:"bytes,2,opt,name=value,proto3"`
	Id                   []string          `protobuf:"bytes,3,rep,name=id,proto3" json:"id,omitempty"`
	XXX_NoUnkeyedLiteral struct{}          `json:"-"`
	XXX_unrecognized     []byte            `json:"-"`
	XXX_sizecache        int32             `json:"-"`
}

func (m *HelloReply) Reset()         { *m = HelloReply{} }
func (m *HelloReply) String() string { return proto.CompactTextString(m) }
func (*HelloReply) ProtoMessage()    {}
func (*HelloReply) Descriptor() ([]byte, []int) {
	return fileDescriptor_116e343673f7ffaf, []int{1}
}

func (m *HelloReply) XXX_Unmarshal(b []byte) error {
	return xxx_messageInfo_HelloReply.Unmarshal(m, b)
}
func (m *HelloReply) XXX_Marshal(b []byte, deterministic bool) ([]byte, error) {
	return xxx_messageInfo_HelloReply.Marshal(b, m, deterministic)
}
func (m *HelloReply) XXX_Merge(src proto.Message) {
	xxx_messageInfo_HelloReply.Merge(m, src)
}
func (m *HelloReply) XXX_Size() int {
	return xxx_messageInfo_HelloReply.Size(m)
}
func (m *HelloReply) XXX_DiscardUnknown() {
	xxx_messageInfo_HelloReply.DiscardUnknown(m)
}

var xxx_messageInfo_HelloReply proto.InternalMessageInfo

func (m *HelloReply) GetMessage() string {
	if m != nil {
		return m.Message
	}
	return ""
}

func (m *HelloReply) GetKv() map[string]string {
	if m != nil {
		return m.Kv
	}
	return nil
}

func (m *HelloReply) GetId() []string {
	if m != nil {
		return m.Id
	}
	return nil
}

func init() {
	proto.RegisterType((*HelloRequest)(nil), "user.HelloRequest")
	proto.RegisterType((*HelloReply)(nil), "user.HelloReply")
	proto.RegisterMapType((map[string]string)(nil), "user.HelloReply.KvEntry")
}

func init() { proto.RegisterFile("user.proto", fileDescriptor_116e343673f7ffaf) }

var fileDescriptor_116e343673f7ffaf = []byte{
	// 217 bytes of a gzipped FileDescriptorProto
	0x1f, 0x8b, 0x08, 0x00, 0x00, 0x00, 0x00, 0x00, 0x02, 0xff, 0x5c, 0x90, 0xc1, 0x4a, 0xc4, 0x30,
	0x10, 0x86, 0x4d, 0xb2, 0x5a, 0x77, 0x14, 0x59, 0x06, 0x0f, 0x61, 0x4f, 0x25, 0xa7, 0x9c, 0x7a,
	0xa8, 0x08, 0x22, 0x78, 0x14, 0x05, 0x6f, 0xf1, 0x09, 0x22, 0x3b, 0xc8, 0xd2, 0xec, 0xb6, 0x26,
	0x69, 0x20, 0x4f, 0xe2, 0xeb, 0x4a, 0x63, 0x8b, 0xb8, 0xb7, 0xff, 0x4f, 0xfe, 0x99, 0x6f, 0x66,
	0x00, 0xc6, 0x40, 0xbe, 0x19, 0x7c, 0x1f, 0x7b, 0x5c, 0x4d, 0x5a, 0x29, 0xb8, 0x7e, 0x25, 0xe7,
	0x7a, 0x43, 0x5f, 0x23, 0x85, 0x88, 0x08, 0xab, 0xa3, 0x3d, 0x90, 0x64, 0x35, 0xd3, 0x6b, 0x53,
	0xb4, 0xfa, 0x66, 0x00, 0x73, 0x68, 0x70, 0x19, 0x25, 0x54, 0x07, 0x0a, 0xc1, 0x7e, 0x2e, 0xa9,
	0xc5, 0xa2, 0x06, 0xde, 0x25, 0xc9, 0x6b, 0xa1, 0xaf, 0x5a, 0xd9, 0x14, 0xd6, 0x5f, 0x5d, 0xf3,
	0x96, 0x9e, 0x8f, 0xd1, 0x67, 0xc3, 0xbb, 0x84, 0x37, 0xc0, 0xf7, 0x3b, 0x29, 0x6a, 0xa1, 0xd7,
	0x86, 0xef, 0x77, 0xdb, 0x7b, 0xa8, 0xe6, 0x6f, 0xdc, 0x80, 0xe8, 0x28, 0xcf, 0xad, 0x27, 0x89,
	0xb7, 0x70, 0x9e, 0xac, 0x1b, 0x49, 0xf2, 0xf2, 0xf6, 0x6b, 0x1e, 0xf9, 0x03, 0x6b, 0x9f, 0xa0,
	0x7a, 0xf1, 0x44, 0x91, 0x3c, 0xb6, 0x70, 0xf9, 0x6e, 0x73, 0xc1, 0x21, 0xfe, 0x63, 0x97, 0xc5,
	0xb6, 0x9b, 0xd3, 0x79, 0xd4, 0xd9, 0xc7, 0x45, 0xb9, 0xc4, 0xdd, 0x4f, 0x00, 0x00, 0x00, 0xff,
	0xff, 0x3d, 0x56, 0x06, 0xb0, 0x17, 0x01, 0x00, 0x00,
}

// Reference imports to suppress errors if they are not otherwise used.
var _ context.Context
var _ grpc.ClientConn

// This is a compile-time assertion to ensure that this generated file
// is compatible with the grpc package it is being compiled against.
const _ = grpc.SupportPackageIsVersion4

// GreeterClient is the client API for Greeter service.
//
// For semantics around ctx use and closing/ending streaming RPCs, please refer to https://godoc.org/google.golang.org/grpc#ClientConn.NewStream.
type GreeterClient interface {
	// Sends a greeting
	SayHello(ctx context.Context, in *HelloRequest, opts ...grpc.CallOption) (*HelloReply, error)
}

type greeterClient struct {
	cc *grpc.ClientConn
}

func NewGreeterClient(cc *grpc.ClientConn) GreeterClient {
	return &greeterClient{cc}
}

func (c *greeterClient) SayHello(ctx context.Context, in *HelloRequest, opts ...grpc.CallOption) (*HelloReply, error) {
	out := new(HelloReply)
	err := c.cc.Invoke(ctx, "/user.Greeter/SayHello", in, out, opts...)
	if err != nil {
		return nil, err
	}
	return out, nil
}

// GreeterServer is the server API for Greeter service.
type GreeterServer interface {
	// Sends a greeting
	SayHello(context.Context, *HelloRequest) (*HelloReply, error)
}

// UnimplementedGreeterServer can be embedded to have forward compatible implementations.
type UnimplementedGreeterServer struct {
}

func (*UnimplementedGreeterServer) SayHello(ctx context.Context, req *HelloRequest) (*HelloReply, error) {
	return nil, status.Errorf(codes.Unimplemented, "method SayHello not implemented")
}

func RegisterGreeterServer(s *grpc.Server, srv GreeterServer) {
	s.RegisterService(&_Greeter_serviceDesc, srv)
}

func _Greeter_SayHello_Handler(srv interface{}, ctx context.Context, dec func(interface{}) error, interceptor grpc.UnaryServerInterceptor) (interface{}, error) {
	in := new(HelloRequest)
	if err := dec(in); err != nil {
		return nil, err
	}
	if interceptor == nil {
		return srv.(GreeterServer).SayHello(ctx, in)
	}
	info := &grpc.UnaryServerInfo{
		Server:     srv,
		FullMethod: "/user.Greeter/SayHello",
	}
	handler := func(ctx context.Context, req interface{}) (interface{}, error) {
		return srv.(GreeterServer).SayHello(ctx, req.(*HelloRequest))
	}
	return interceptor(ctx, in, info, handler)
}

var _Greeter_serviceDesc = grpc.ServiceDesc{
	ServiceName: "user.Greeter",
	HandlerType: (*GreeterServer)(nil),
	Methods: []grpc.MethodDesc{
		{
			MethodName: "SayHello",
			Handler:    _Greeter_SayHello_Handler,
		},
	},
	Streams:  []grpc.StreamDesc{},
	Metadata: "user.proto",
}
