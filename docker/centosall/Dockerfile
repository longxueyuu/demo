FROM centos:latest

MAINTAINER longxueyuu <longxueyuu@gmail.com>

# root用户密码
ARG ROOT_PASSWORD=123456
# java 版本
ARG JDK_TAR_GZ=jdk-8u172-linux-x64.tar.gz
ENV JAVA_VERSION=jdk1.8.0_172
# nginx版本
ARG NGINX_VERSION=1.12.2
# redis版本
ARG REDIS_VERSION=4.0.10
# redis密码
ARG REDIS_PASS=123456

# 添加本地配置文件到容器
ADD ./etc/ /lxy/etc/

# set up group & user
RUN groupadd -r lxy && useradd -r -g lxy lxy

RUN chown lxy:lxy /lxy

ARG buildDeps="sudo gcc automake autoconf libtool make wget tcl tcl-devel which cmake g++ gcc-c++ ncurses-devel bison"
RUN yum -y install $buildDeps

# 当前工作目录
WORKDIR /lxy

RUN mkdir downloads

WORKDIR downloads

RUN set -ex \
    # redis dep: tcl
    # && wget -O tcl.tar.gz https://sourceforge.net/projects/tcl/files/Tcl/8.6.8/tcl8.6.8-src.tar.gz \
    # && mkdir tcl \
    # && tar -xzvf tcl.tar.gz -C ./tcl --strip-components=1 \
    # && cd tcl/unix \
    # && sudo ./configure \
    # && sudo make \
    # && sudo make install \
    # && cd /lxy/downloads \

    # redis
    && wget -O redis.tar.gz http://download.redis.io/releases/redis-${REDIS_VERSION}.tar.gz \
    && mkdir redis \
    && tar -xzvf redis.tar.gz -C ./redis --strip-components=1 \
    && cd redis \
    && make \
    && make test \
    && make install \
    && mkdir -p /usr/local/redis/bin \
    && mkdir -p /usr/local/redis/data \
    && cp ./src/redis-server /usr/local/redis/bin/ \
    && cp ./src/redis-cli /usr/local/redis/bin/ \
    && cp ./src/redis-benchmark /usr/local/redis/bin/ \
    && cp ./redis.conf /etc/redis.conf \
    && cp ./sentinel.conf /etc/sentinel.conf \
    && sed -i 's/bind 127.0.0.1/bind 0.0.0.0/' /etc/redis.conf \
    && sed -i "s/# requirepass foobared/requirepass ${REDIS_PASS}/" /etc/redis.conf \
    && sed -i 's/logfile ""/logfile \/usr\/local\/redis\/data\/redis.log/' /etc/redis.conf \
    && sed -i 's/dir .\//dir \/usr\/local\/redis\/data\//' /etc/redis.conf \
    && sed -i 's/dbfilename dump.rdb/dbfilename redis.rdb/' /etc/redis.conf \
    && cd /lxy/downloads \
    && rm -f redis.tar.gz && rm -rf redis

# mysql by source code
RUN set -ex \
    && mkdir -p /usr/local/boost \
    && wget -O boost.tar.gz http://www.sourceforge.net/projects/boost/files/boost/1.59.0/boost_1_59_0.tar.gz \
    && tar -xzvf boost.tar.gz -C /usr/local/boost --strip-components=1 \
    && cd /lxy/downloads \
    && wget -O mysql.tar.gz https://dev.mysql.com/get/Downloads/MySQL-5.7/mysql-5.7.22.tar.gz \
    && mkdir mysql \
    && tar -xzvf mysql.tar.gz -C ./mysql --strip-components=1 \
    && cd mysql \
    && cmake . \
              -DCMAKE_INSTALL_PREFIX=/usr/local/mysql \
              -DMYSQL_DATADIR=/usr/local/mysql/data \
              -DDEFAULT_CHARSET=utf8 \
              -DDEFAULT_COLLATION=utf8_general_ci \
              -DEXTRA_CHARSETS=all \
              -DENABLED_LOCAL_INFILE=1 \
              -DDOWNLOAD_BOOST=1 \
              -DWITH_BOOST=/usr/local/boost \
    && make \
    && make install \
    && cd /usr/local/mysql \
    && cp support-files/* ./bin \
    && ./bin/mysqld --initialize-insecure --basedir=/usr/local/mysql --datadir=/usr/local/mysql/data --user=lxy \
    # && su - lxy /usr/local/mysql/bin/mysql.server start \
    # && ./bin/mysql -h localhost -u root < /lxy/etc/initroot.sql \
    # && ./bin/mysql -h localhost -u root -p123456 < /lxy/etc/initdatabaselxy.sql \
    # && su - lxy /usr/local/mysql/bin/mysql.server stop \
    && cd /lxy/downloads \
    && rm -f mysql.tar.gz && rm -rf mysql

# jdk
COPY $JDK_TAR_GZ java.tar.gz
RUN set -ex \
    && mkdir -p /usr/local/java \
    && cd /lxy/downloads \
    && ls -lah \
    && tar -xzvf java.tar.gz -C /usr/local/java \
    && sed -i '$a\JAVA_HOME=/usr/local/java/${JAVA_VERSION}' /etc/profile \
    && sed -i '$a\PATH=$PATH:$JAVA_HOME/bin' /etc/profile \
    && sed -i '$a\CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JRE_HOME/lib' /etc/profile \
    && sed -i '$a\export JAVA_HOME PATH CLASSPATH' /etc/profile \
    && rm -f java.tar.gz

RUN yum -y remove $buildDeps

# inf component port
EXPOSE 80 3306 6379

# Application
VOLUME /tmp
WORKDIR /lxy/app
ADD springboot-template-0.0.1-SNAPSHOT.jar app.jar

# app Port
EXPOSE 8079 8080

#ENV 设置环境变量
ENV JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8080"

COPY docker-entrypoint.sh /usr/local/bin/
RUN chmod o+x /usr/local/bin/docker-entrypoint.sh

ENTRYPOINT [ "sh", "-c", "docker-entrypoint.sh" ]
# CMD ["param1","param2"] # as default parameters to ENTRYPOINT if ENTRYPOINT exists.
