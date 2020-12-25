FROM openjdk:8-jdk-alpine
MAINTAINER "zygx"
LABEL description="rcs"
ARG JAR_FILE=target/rcs-1.0.0.jar

WORKDIR /opt/rcs
ADD ${JAR_FILE} /opt/rcs/rcs.jar


#java 启动参数
ENV JAVA_OPTS="-Xmx256m -Xms256m"
#web端口
ENV port="8080"

EXPOSE $port

ENV nacos_ip="127.0.0.1"
ENV nacos_port="8848"

CMD java ${JAVA_OPTS} -jar /opt/rcs/rcs.jar --server.port=$port --spring.cloud.nacos.discovery.server-addr=$nacos_ip:$nacos_port