FROM docker.io/library/eclipse-temurin:11-jre-alpine AS reports

COPY ./target/evcharging-reports-*.jar ./
EXPOSE 8081
CMD java -jar evcharging-reports-*.jar
