FROM maven:3.8.6-openjdk-11 as build
COPY src usr/src/api-auth/src
COPY pom.xml usr/src/api-auth
RUN mvn -f usr/src/api-auth/pom.xml clean package -DskipTests=true

FROM openjdk:11
EXPOSE 9000
WORKDIR .
COPY --from=build /usr/src/api-auth/target/library_auth_api-0.0.1-SNAPSHOT.jar /usr/api-auth/library_auth_api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "/usr/api-auth/library_auth_api-0.0.1-SNAPSHOT.jar"]
