FROM docker.io/library/openjdk:11-slim AS run-reports

ENV VER=0.5.0

COPY --from=evcharging-build /package/reports/target/evcharging-reports-${VER}.jar ./
EXPOSE 8081
CMD java -jar evcharging-reports-${VER}.jar
