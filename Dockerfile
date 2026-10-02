# multi stage
# stage 1 : proses build
FROM maven:3.10-eclipse-temurin-25-alpine AS builder
WORKDIR /app
COPY pom.xml .
RUN --mount=type=cache,target=/root/.m2 \
    mvn -B -DskipTests dependency:go-offline

COPY /src ./src
RUN --mount=type=cache,target=/root/.m2 \
    mvn -B -DskipTests clean package

# stage 2 : proses run
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
COPY --from=builder /app/target/*.jar Lc-Loan-Manajement-System-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["java", "-jar", "/app/Lc-Loan-Manajement-System-0.0.1-SNAPSHOT.jar"]