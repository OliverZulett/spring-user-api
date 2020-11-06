FROM openjdk:8-jdk-alpine
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
ENV APP_PORT=8093
ENV API_USER=userAdmin
ENV API_PASSWORD=userAdmin
ENV API_USER_ROLE=ADMIN
ENV DB_URL=jdbc:postgresql://postgres-users:5432/USERS_DB
ENV DB_USER=super_admin
ENV DB_PASSWORD=super_password
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} api.jar
ENTRYPOINT ["java","-Dspring.profiles.active=docker","-jar","api.jar"]