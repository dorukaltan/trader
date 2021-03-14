FROM openjdk:8-jre-slim

ARG CI_PROJECT_NAME

WORKDIR /home/app

COPY target/${CI_PROJECT_NAME}*.jar /home/app/

CMD java -jar ${CI_PROJECT_NAME}*.jar
