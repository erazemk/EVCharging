FROM docker.io/library/eclipse-temurin:11-jre-alpine AS invoices

COPY ./target/evcharging-invoices-*.jar ./
EXPOSE 80
CMD java -jar evcharging-invoices-*.jar
