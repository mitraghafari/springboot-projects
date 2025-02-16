#sudo docker build -t my-eureka-server:1.0 -f eurekaServer/Dockerfile .
#sudo docker run  --name myeurekaserver my-eureka-server:1.0


FROM openjdk:8-jdk-alpine
LABEL maintainer="mary@gmail.com"
COPY target/*.jar /app.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","app.jar"]
