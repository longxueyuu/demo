package main

import (
	"flag"
	"github.com/test/project/config"
	"github.com/test/project/env"
	"github.com/test/project/util/fileex"
	"github.com/test/project/util/logex"
	"log"
	"net/http"
	_ "net/http/pprof"
	"os"
	"path/filepath"
	"runtime/trace"
)

var (
	confPath = flag.String("conf", "conf/dev/demo/demo.yml", "configuration file")
	conf     config.Demo
)

func main() {
	flag.Parse()
	curDir := filepath.Dir(os.Args[0])
	absDir, err := filepath.Abs(curDir)
	wdDird, err := os.Getwd()
	log.Printf("main:info, confPath=%v curDir=%v absDir=%v wdDird=%v err=%v", *confPath, curDir, absDir, wdDird, err)

	err = config.ParseYML(*confPath, &conf)
	if err != nil {
		log.Panicf("main: parseYML, confPath=%v err=%v", *confPath, err)
	}

	f, err := fileex.AppendFile(conf.Log.Trace)
	if err != nil {
		log.Panicf("main: create trace file, confPath=%v err=%v", conf.Log.Trace, err)
	}
	defer f.Close()

	//  trace
	err = trace.Start(f)
	if err != nil {
		log.Panicf("main: start trace, err=%v", err)
	}
	defer trace.Stop()

	// init log
	apiLog, af, err := logex.GetLogger(conf.Log.Api, "")
	if err != nil {
		af.Close()
		log.Panicf("main: init apilog, err=%v", err)
	}
	defer af.Close()
	env.ApiLog = apiLog

	errLog, ef, err := logex.GetLogger(conf.Log.Err, "")
	if err != nil {
		ef.Close()
		log.Panicf("main: init errLog, err=%v", err)
	}
	defer ef.Close()
	env.ErrLog = errLog

	// init biz/service

	// init server
	env.ApiLog.Printf("main: service initlized")
	log.Printf("main: service initlized")

	err = http.ListenAndServe(conf.Bind, nil)

	env.ErrLog.Printf("main: server is down, err=%v", err)
	log.Printf("main: server is down, err=%v", err)
}
