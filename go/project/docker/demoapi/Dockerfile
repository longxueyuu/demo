FROM golang:1.14

MAINTAINER longxueyuu <longxueyuu@gmail.com>

WORKDIR /app/go/project

RUN set -ex \
    && apt-get update && apt install net-tools

COPY ./docker/demoapi/docker-entrypoint.sh /usr/local/bin/

RUN chmod a+x /usr/local/bin/docker-entrypoint.sh

ENTRYPOINT [ "sh", "-c", "docker-entrypoint.sh" ]