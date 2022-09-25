FROM openjdk:11
EXPOSE 9000
WORKDIR .
COPY target/library_auth_api-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "library_auth_api-0.0.1-SNAPSHOT.jar"]
