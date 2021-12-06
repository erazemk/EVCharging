FROM docker.io/library/maven:3.8.4-openjdk-11-slim AS build

RUN mkdir /package
COPY ./ /package
WORKDIR /package
RUN mvn clean package

FROM docker.io/library/openjdk:11-slim AS run

ENV VER=0.0.0

COPY --from=build /package/api/target/evcharging-api-${VER}.jar ./
EXPOSE 8080
CMD java -jar evcharging-api-${VER}.jar
