FROM maven:3-openjdk-18 as build

COPY src /app/src
COPY pom.xml /app

WORKDIR /app

RUN mvn clean install

FROM openjdk:18-alpine
COPY --from=build /app/target/apicnpj-1.0.1.jar /app/app.jar



WORKDIR /app

EXPOSE 8080

CMD ["java","-jar","app.jar"]