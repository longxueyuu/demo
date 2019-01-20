FROM centos:latest

MAINTAINER longxueyuu <longxueyuu@gmail.com>

# 当前工作目录
WORKDIR /lxy/downloads

# java 版本
ARG JDK_TAR_GZ=jdk-8u172-linux-x64.tar.gz
ENV JAVA_VERSION=jdk1.8.0_172

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

RUN groupadd -r lxy && useradd -m -r -g  lxy lxy

ARG buildDeps="sudo openssh-server git"
RUN yum -y install $buildDeps

RUN chown -R lxy:lxy /lxy

USER lxy

ADD gerrit-2.15.2.war gerrit.war

RUN set -ex \
    && source /etc/profile \
    && cd /lxy/downloads/ \
    && java -jar gerrit.war init --batch -d /lxy/gerrit_site \
    && /lxy/gerrit_site/bin/gerrit.sh stop \
    && rm -f gerrit.war


# app Port
EXPOSE 82 8080 29418

USER root
# nginx yum repo安装
COPY nginx.repo /etc/yum.repos.d/
RUN yum -y install nginx

# nginx-gerrit配置
COPY nginx-gerrit.conf /etc/nginx/conf.d/

# nexus 安装
EXPOSE 8081
COPY latest-unix.tar.gz nexus.tar.gz
RUN set -ex \
    && mkdir -p /usr/local/nexus \
    && cd /lxy/downloads \
    && tar -xzvf nexus.tar.gz -C /usr/local/nexus --strip-components=1 \
    && cd /usr/local/nexus \
    && mv nexus* nexus

# gerrit配置
COPY gerrit.password /lxy/gerrit_site/etc/
RUN set -ex \
    && cd /home/lxy/ \
    && mkdir .ssh \
    && cd .ssh \
    && ssh-keygen -t rsa -C 'longxueyuu@gmail.com' -P "" -f ./longxueyuu_id_rsa \
    # gerrit.canonicalWebUrl pattern: http://nginx-server_name:nginx-listen_port/
    && git config --file /lxy/gerrit_site/etc/gerrit.config  gerrit.canonicalWebUrl 'http://gerrit.docker.com:9000/' \
    && git config --file /lxy/gerrit_site/etc/gerrit.config  auth.type 'HTTP' \
    && git config --file /lxy/gerrit_site/etc/gerrit.config  sendemail.smtpServer 'smtp.163.com' \
    && git config --file /lxy/gerrit_site/etc/gerrit.config  sendemail.smtpServerPort '465' \
    && git config --file /lxy/gerrit_site/etc/gerrit.config  sendemail.smtpEncryption 'ssl' \
    && git config --file /lxy/gerrit_site/etc/gerrit.config  sendemail.sslVerify 'true' \
    && git config --file /lxy/gerrit_site/etc/gerrit.config  sendemail.smtpUser 'lxy_gerrit@163.com' \
    && git config --file /lxy/gerrit_site/etc/gerrit.config  sendemail.smtpPass 'password' \
    && git config --file /lxy/gerrit_site/etc/gerrit.config  sendemail.from 'lxy_gerrit@163.com'
    # && git config --file /lxy/gerrit_site/etc/gerrit.config  gitweb.type 'gitweb' \
    # && git config --file /lxy/gerrit_site/etc/gerrit.config  gitweb.cgi 'gitweb.cgi'


#ENV 设置环境变量
ENV JAVA_OPTS="-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=9999"
COPY docker-entrypoint.sh /usr/local/bin/
RUN chmod a+x /usr/local/bin/docker-entrypoint.sh
ENTRYPOINT [ "sh", "-c", "docker-entrypoint.sh" ]
