#!/bin/bash

# docker build -t longxueyuu/centos:all .
# docker run -p 6379:6379 -p 4000:8079 -p 4001:8080 -p 3306:3306  -v /zqx/dockersharing/data/mysql:/usr/local/mysql/data -v /zqx/dockersharing/data/redis:/usr/local/redis/data longxueyuu/centos:all

set -ex
source /etc/profile


nohup /usr/local/redis/bin/redis-server /etc/redis.conf &

echo `javac`

echo `java`

echo `java -version`

DIRECTORY=/usr/local/mysql/data
if [[ "`ls -A $DIRECTORY`" = "" ]]; then
  /usr/local/mysql/bin/mysqld --initialize-insecure --basedir=/usr/local/mysql --datadir=/usr/local/mysql/data --user=lxy \
    && su - lxy /usr/local/mysql/bin/mysql.server start \
    && /usr/local/mysql/bin/mysql -h localhost -u root < /lxy/etc/initroot.sql \
    && /usr/local/mysql/bin/mysql -h localhost -u root -p123456 < /lxy/etc/initdatabaselxy.sql
else
  su - lxy /usr/local/mysql/bin/mysql.server start
fi


java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /lxy/app/app.jar
