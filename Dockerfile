# For Java 8, this will pull up an Alpine linux image with java 8 installed
FROM openjdk:8-jdk-alpine

# Refer to jar file
ARG JAR_FILE=build/libs/circle-area-calculator*.jar

# cd /opt/app and copy the jar
WORKDIR /opt/app
COPY ${JAR_FILE} circle-area-calculator-app.jar

EXPOSE 8081

# java -jar /opt/app/circle-area-calculator-app.jar
ENTRYPOINT ["java","-jar","circle-area-calculator-app.jar"]
