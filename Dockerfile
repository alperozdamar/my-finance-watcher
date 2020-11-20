FROM openjdk:8
ADD target/my-finance-watcher-1.0.0.jar my-finance-watcher-1.0.0.jar
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "my-finance-watcher-1.0.0.jar"]


