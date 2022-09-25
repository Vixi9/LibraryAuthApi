FROM maven:3.8.6-openjdk-11

WORKDIR /libraryAuthApi
COPY . .
RUN mvn clean install
CMD mvn -B package --file pom.xml

FROM openjdk:11
EXPOSE 9000
WORKDIR .
COPY target/LibraryAuthApi-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "LibraryAuthApi-0.0.1-SNAPSHOT.jar"]
