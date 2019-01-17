# PART1: 基础镜像信息
FROM java:8


# PART2: MAINTAINER 维护者信息
MAINTAINER longxueyuu  <longxueyuu@gmail.com>

WORKDIR /app

VOLUME /tmp

VOLUME /Users/username/lxy/docker

ADD springboot-template-0.0.1-SNAPSHOT.jar app.jar

# RUN sh -c 'touch /app.jar'

# Inside Port
EXPOSE 8079 8080

#ENV 设置环境变量
ENV JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8080"

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app/app.jar" ]