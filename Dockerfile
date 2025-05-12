FROM eclipse-temurin:21-jre

COPY target/*.jar my-finance-watcher.jar

EXPOSE 8089

ENTRYPOINT ["java", "-jar", "my-finance-watcher.jar"]