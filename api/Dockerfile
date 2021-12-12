FROM docker.io/library/openjdk:11-slim AS main

COPY ./target/evcharging-api-*.jar ./
EXPOSE 8080
CMD java -jar evcharging-api-*.jar
