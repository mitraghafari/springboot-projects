#sudo docker build -t my-config-server:1.0 -f ./Dockerfile .
#sudo docker run  --name myconfigserver my-config-server:1.0

FROM openjdk:8-jdk-alpine
LABEL maintainer="mary@gmail.com"
COPY myMvcMongo/target/*.jar /app.jar
EXPOSE 7070
ENTRYPOINT ["java","-jar","app.jar"]
