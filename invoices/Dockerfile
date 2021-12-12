FROM docker.io/library/openjdk:11-slim AS invoices

COPY ./target/evcharging-invoices-*.jar ./
EXPOSE 8082
CMD java -jar evcharging-invoices-*.jar
