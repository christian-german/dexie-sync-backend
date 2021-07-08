# Build
FROM maven:3.6.3-jdk-11 AS MAVEN_BUILD
COPY pom.xml /build/
WORKDIR /build/
RUN mvn dependency:resolve
COPY src /build/src/
RUN mvn package

# Run
FROM openjdk:11-jre-slim
RUN mkdir -p /opt/application
WORKDIR /opt/application
RUN groupadd spring && useradd -g spring spring
USER spring:spring
COPY --from=MAVEN_BUILD --chown=spring:spring /build/target/dexie-sync-backend-*-SNAPSHOT.jar dexie-sync-backend.jar
ENTRYPOINT [ "java", "-jar", "/opt/application/dexie-sync-backend.jar" ]

