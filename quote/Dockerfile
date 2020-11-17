FROM adoptopenjdk/openjdk11-openj9:alpine-jre
ARG JAR_FILE=target/quote-*.jar
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]