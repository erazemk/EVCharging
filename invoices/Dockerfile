FROM docker.io/library/openjdk:11-slim AS invoices

#COPY --from=evcharging-build /build/invoices/target/evcharging-invoices-*.jar ./
COPY ./target/evcharging-invoices-*.jar ./
EXPOSE 8082
CMD java -jar evcharging-invoices-*.jar
