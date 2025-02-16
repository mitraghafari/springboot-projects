#sudo docker build -t my-config-server:1.0 -f ./Dockerfile .
FROM openjdk:8-jdk-alpine
ARG path
RUN echo $path
#COPY $path/target/*.jar /app.jar
COPY configServer/target/*.jar /app.jar
EXPOSE 8888:8888
ENTRYPOINT ["java","-jar","app.jar"]
