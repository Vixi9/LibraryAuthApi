FROM openjdk:11
EXPOSE 9000
WORKDIR .
COPY target/LibraryAuthApi-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "LibraryAuthApi-0.0.1-SNAPSHOT.jar"]
