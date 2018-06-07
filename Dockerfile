FROM openjdk:8u121-jdk-alpine

ENV JAVA_OPTS="-server -Xmx512m -XX:MinHeapFreeRatio=20 -XX:MaxHeapFreeRatio=40 -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70"

RUN apk update && \
    apk add --no-cache ttf-dejavu \
	font-adobe-100dpi

ADD target/*.jar /app.jar

EXPOSE 8080
CMD java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /app.jar
