FROM docker.io/library/maven:3.8.4-openjdk-11-slim AS build

RUN mkdir /package
COPY ./ /package
WORKDIR /package
RUN mvn clean package
