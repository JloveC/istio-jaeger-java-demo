# our base build image
# FROM harbor.aigauss.com/base-images/maven:3-jdk-8-slim as builder
# COPY ./pom.xml ./pom.xml
# RUN mvn dependency:go-offline -B
# COPY ./src ./src
# RUN mvn package


# FROM harbor.aigauss.com/base-images/openjdk:8-slim as runner
# ENV JAR_NAME=solution-serviceb-0.0.1-SNAPSHOT.jar
# COPY --from=builder target/$JAR_NAME ./app.jar
# ENV JAVA_OPTS=""
# ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar ./app.jar" ]


FROM harbor.aigauss.com/base-images/openjdk:8-slim as runner
COPY target/solution-servicec-0.0.1-SNAPSHOT.jar ./app.jar

ENV JAVA_OPTS=""
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -jar ./app.jar" ]