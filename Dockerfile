FROM docker.io/library/maven:3.8-openjdk-11-slim AS build

WORKDIR /build
COPY . /build
RUN mvn dependency:resolve --fail-never && rm -rf /build/*

COPY . /build
RUN mvn clean package
