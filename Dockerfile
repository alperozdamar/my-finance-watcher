# Switch from openjdk to eclipse-temurin, but keep Java 8
FROM eclipse-temurin:8-jre

COPY target/*.jar my-finance-watcher.jar

EXPOSE 8089

ENTRYPOINT ["java", "-jar", "my-finance-watcher.jar"]
