FROM openjdk:8-jdk-alpine
MAINTAINER "zygx"
LABEL description="rcs"
ARG JAR_FILE=target/rcs-1.0.0.jar

WORKDIR /opt/rcs
ADD ${JAR_FILE} /opt/rcs/rcs.jar
EXPOSE 8088
CMD java -jar /opt/rcs/rcs.jar