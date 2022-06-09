FROM maven:3.8.4-openjdk-11-slim as build
RUN mkdir ikea
WORKDIR /ikea
COPY ./ ./
RUN mvn clean package spring-boot:repackage
RUN ls
RUN ls ./target

FROM gcr.io/distroless/java:11
COPY --from=build /ikea/target/*.jar /jar-files/ikea.jar
WORKDIR /jar-files

EXPOSE 8080
CMD [ "ikea.jar" ]