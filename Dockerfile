FROM openjdk:11-jre-slim
WORKDIR /app

# copy sbt-built fat JAR (you’ll build with sbt assembly)
COPY target/scala-3.3.5/RabbitMQ-assembly-0.1.0-SNAPSHOT.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]
