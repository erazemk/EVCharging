FROM docker.io/library/eclipse-temurin:11-jre-alpine AS main

COPY ./target/evcharging-api-*.jar ./
EXPOSE 8080
CMD java -jar evcharging-api-*.jar
