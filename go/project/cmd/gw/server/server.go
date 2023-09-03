package server

import (
	"context"
	"github.com/grpc-ecosystem/grpc-gateway/v2/runtime"
	"github.com/test/project/cmd/gw/view"
	pb "github.com/test/project/proto/gen/demo"
	"google.golang.org/grpc"
	"google.golang.org/protobuf/proto"
	"log"
	"net/http"
	"strconv"
)

func StartGwServer() {
	conn, err := grpc.DialContext(
		context.Background(),
		"localhost:9001", // grpc server
		grpc.WithBlock(),
		grpc.WithInsecure(),
	)
	if err != nil {
		log.Fatalln("Failed to dial server: ", err)
	}
	mux := runtime.NewServeMux(runtime.WithForwardResponseOption(httpResponseModifier),
		runtime.WithIncomingHeaderMatcher(CustomMatcher),
		runtime.WithOutgoingHeaderMatcher(CustomMatcher),
	)
	err = pb.RegisterHelloServiceHandler(context.Background(), mux, conn)
	if err != nil {
		log.Fatalln("Failed to register gateway: ", err)
	}

	err = registerAPIHandler(mux)
	if err != nil {
		log.Fatalln("Failed to register custom api: ", err)
	}

	server := &http.Server{
		Addr:    ":8000",
		Handler: mux,
	}

	log.Println("Start gRPC Gateway Server on http://0.0.0.0:8000")
	err = server.ListenAndServe()
	if err != nil {
		log.Fatalln("Start Gateway Server failed: ", err)
	}
}

func registerAPIHandler(mux *runtime.ServeMux) error {
	err := mux.HandlePath(http.MethodPost, "/gw/code", view.GetCode)
	if err != nil {
		return err
	}

	return nil
}

func CustomMatcher(key string) (string, bool) {
	switch key {
	case "x-custom-header1":
		return key, true
	case "x-custom-header2":
		return "custom-header2", true
	default:
		return key, false
	}
}

func httpResponseModifier(ctx context.Context, w http.ResponseWriter, p proto.Message) error {
	md, ok := runtime.ServerMetadataFromContext(ctx)
	if !ok {
		return nil
	}

	// set http status code
	if vals := md.HeaderMD.Get("x-http-code"); len(vals) > 0 {
		code, err := strconv.Atoi(vals[0])
		if err != nil {
			return err
		}
		// delete the headers to not expose any grpc-metadata in http response
		delete(md.HeaderMD, "x-http-code")
		delete(w.Header(), "Grpc-Metadata-X-Http-Code")
		w.WriteHeader(code)
	}

	return nil
}
