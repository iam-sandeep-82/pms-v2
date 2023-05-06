FROM openjdk:17-oracle
VOLUME /tmp
EXPOSE 8080
COPY patient-app/patient_app-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "patient_app-0.0.1-SNAPSHOT.jar"]