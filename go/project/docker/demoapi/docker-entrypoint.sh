#!/bin/bash

set -ex

go env

ls -lah /app/go/project && cd /app/go/project && make demoapi

./demoapi.linux-amd64