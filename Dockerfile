FROM maven:3.5.3-jdk-8-alpine as builder
RUN mkdir -p /usr/local/src/builder
WORKDIR /usr/local/src/builder
COPY . ${WORKDIR}

RUN mvn clean package -DskipTests

FROM openjdk:8u121-jdk-alpine

ENV JAVA_OPTS="-server -Xmx512m -XX:MinHeapFreeRatio=20 -XX:MaxHeapFreeRatio=40 -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=70"

RUN apk update && \
    apk add --no-cache ttf-dejavu \
	font-adobe-100dpi

COPY --from=builder /usr/local/src/builder/target/*.jar  /app.jar

EXPOSE 8080
CMD java ${JAVA_OPTS} -Djava.security.egd=file:/dev/./urandom -jar /app.jar
