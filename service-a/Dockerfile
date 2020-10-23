FROM maven:3-jdk-8-slim as builder
COPY setting.xml /root/.m2/setting.xml
COPY pom.xml pom.xml
RUN mvn dependency:go-offline -B
COPY src src
RUN mvn package


FROM harbor.aigauss.com/base-images/openjdk:8-slim as runner
ENV SPECIALAGENT=/opt/opentracing-specialagent-1.7.4.jar \
    JAR_NAME=solution-servicea-0.0.1-SNAPSHOT.jar
COPY opentracing-specialagent-1.7.4.jar $SPECIALAGENT
COPY --from=builder target/$JAR_NAME app.jar

ENV JAVA_OPTS="-javaagent:$SPECIALAGENT -Dsa.exporter=jaeger -Dsa.log.level=INFO -Dsa.init.defer=true -DJAEGER_SERVICE_NAME=service-a"
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar app.jar" ]