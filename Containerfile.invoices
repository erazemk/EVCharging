FROM docker.io/library/openjdk:11-slim AS run-invoices

ENV VER=0.5.0

COPY --from=evcharging-build /package/invoices/target/evcharging-invoices-${VER}.jar ./
EXPOSE 8082
CMD java -jar evcharging-invoices-${VER}.jar
