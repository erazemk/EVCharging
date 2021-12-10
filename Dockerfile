FROM docker.io/library/maven:3.8.4-openjdk-11-slim AS build

RUN mkdir /package
COPY ./ /package
WORKDIR /package
RUN mvn clean package

FROM docker.io/library/openjdk:11-slim AS run-main

ENV VER=0.5.0

COPY --from=build /package/api/target/evcharging-api-${VER}.jar ./
EXPOSE 8080
CMD java -jar evcharging-api-${VER}.jar

FROM docker.io/library/openjdk:11-slim AS run-reports

ENV VER=0.5.0

COPY --from=build /package/api/target/evcharging-reports-${VER}.jar ./
EXPOSE 8081
CMD java -jar evcharging-reports-${VER}.jar

FROM docker.io/library/openjdk:11-slim AS run-invoices

ENV VER=0.5.0

COPY --from=build /package/api/target/evcharging-invoices-${VER}.jar ./
EXPOSE 8082
CMD java -jar evcharging-invoices-${VER}.jar
