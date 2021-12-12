FROM docker.io/library/openjdk:11-slim AS reports

#COPY --from=evcharging-build /build/reports/target/evcharging-reports-*.jar ./
COPY ./target/evcharging-reports-*.jar ./
EXPOSE 8081
CMD java -jar evcharging-reports-*.jar
