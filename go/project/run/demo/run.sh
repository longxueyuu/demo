#!/bin/sh
export LANG=en_US.UTF-8
export LC_ALL='en_US.UTF-8'
echo "`date '+%Y-%m-%d %H:%M:%S'` demoapi restart" >> ./demoapi_err.log

exec ./demoapi.darwin-amd64 >> ./demoapi_err.log 2>&1
