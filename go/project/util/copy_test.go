package util

import (
	"encoding/json"
	dc2 "github.com/barkimedes/go-deepcopy"
	"github.com/gogo/protobuf/proto"
	"github.com/mohae/deepcopy"
	"log"
	"testing"
)

type ProtoObj struct {
}

func (p ProtoObj) Reset() {
	//TODO implement me
	panic("implement me")
}

func (p ProtoObj) String() string {
	//TODO implement me
	panic("implement me")
}

func (p ProtoObj) ProtoMessage() {
	//TODO implement me
	panic("implement me")
}

var (
	obj = &ProtoObj{}

	bsProto []byte
	bsJson  []byte
)

func init() {
	var err error
	bsProto, err = proto.Marshal(obj)
	if err != nil {
		panic(err)
	}

	bsJson, err = json.Marshal(obj)
	if err != nil {
		panic(err)
	}
}

func CopyManual2(u *ProtoObj) *ProtoObj {
	return &ProtoObj{}
}

func CopyManual(u *ProtoObj) *ProtoObj {
	r := *u
	return &r
}

func TestCopy(t *testing.T) {
	u := CopyManual(obj)
	u2 := CopyManual(obj)
	log.Printf("obj=%p u=%p u2=%p", obj, u, u2)
}

func BenchmarkCopyManual(b *testing.B) {
	var t *ProtoObj
	b.ReportAllocs()
	for i := 0; i < b.N; i++ {
		t = CopyManual(obj)
	}

	_ = t
}

func BenchmarkCopyManual2(b *testing.B) {
	var t *ProtoObj
	b.ReportAllocs()
	for i := 0; i < b.N; i++ {
		t = CopyManual2(obj)
	}

	_ = t
}

func BenchmarkCopyProtoUnmarshal(b *testing.B) {
	var t ProtoObj
	b.ReportAllocs()
	for i := 0; i < b.N; i++ {
		err := proto.Unmarshal(bsProto, &t)
		if err != nil {
			panic(err)
		}
	}

	_ = t
}

func BenchmarkCopyProtoClone(b *testing.B) {
	var t proto.Message
	b.ReportAllocs()
	for i := 0; i < b.N; i++ {
		t = proto.Clone(obj)
		_ = t
	}

	_ = t
}

func BenchmarkCopyReflect(b *testing.B) {
	var t interface{}
	b.ReportAllocs()
	for i := 0; i < b.N; i++ {
		var err error
		t, err = dc2.Anything(obj)
		if err != nil {
			panic(err)
		}
	}

	_ = t
}

func BenchmarkCopyReflect2(b *testing.B) {
	var t interface{}
	b.ReportAllocs()
	for i := 0; i < b.N; i++ {
		var err error
		t = deepcopy.Copy(obj)
		if err != nil {
			panic(err)
		}
	}

	_ = t
}

func BenchmarkCopyJsonUnmarshal(b *testing.B) {
	var t ProtoObj
	b.ReportAllocs()
	for i := 0; i < b.N; i++ {
		err := json.Unmarshal(bsJson, &t)
		if err != nil {
			panic(err)
		}
	}

	_ = t
}
