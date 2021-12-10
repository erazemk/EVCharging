FROM docker.io/library/openjdk:11-slim AS run-main

ENV VER=0.5.0

COPY --from=evcharging-build /package/api/target/evcharging-api-${VER}.jar ./
EXPOSE 8080
CMD java -jar evcharging-api-${VER}.jar
