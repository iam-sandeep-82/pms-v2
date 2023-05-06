FROM openjdk:17-oracle
VOLUME /tmp
EXPOSE 8000
COPY service-app/service_app-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "service_app-0.0.1-SNAPSHOT.jar"]