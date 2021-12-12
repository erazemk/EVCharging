FROM docker.io/library/openjdk:11-slim AS reports

COPY ./target/evcharging-reports-*.jar ./
EXPOSE 8081
CMD java -jar evcharging-reports-*.jar
