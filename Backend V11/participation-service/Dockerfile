FROM openjdk:17
EXPOSE 8083
ADD target/participation-service-0.0.1-SNAPSHOT.jar participation.jar
ENTRYPOINT ["java","-jar", "/participation.jar"]