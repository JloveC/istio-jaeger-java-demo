FROM openjdk:8-slim as base
RUN sed -i 's,http://.*.debian.org,http://mirrors.aliyun.com,g' /etc/apt/sources.list &&\
    apt update &&\
    apt install -y wget &&\
    rm -rf /var/lib/apt/lists/* &&\
    wget -O opentracing-specialagent-1.7.4.jar "https://repo1.maven.org/maven2/io/opentracing/contrib/specialagent/opentracing-specialagent/1.7.4/opentracing-specialagent-1.7.4.jar"

FROM maven:3-jdk-8-slim as builder
COPY settings.xml /root/.m2/settings.xml
COPY pom.xml pom.xml
RUN mvn dependency:go-offline -B
COPY src src
RUN mvn package

FROM base as runner
COPY --from=builder target/jlovec-serviceb-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar app.jar" ]