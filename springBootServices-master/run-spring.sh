#!/bin/bash

java -jar /home/mary/projetcs/springBootServices/configServer/target/*.jar &

java -jar /home/mary/projetcs/springBootServices/eurekaServer/target/*.jar &

java -jar /home/mary/projetcs/springBootServices/myRestRepoMySql/target/*.jar &

java -jar /home/mary/projetcs/springBootServices/myMvcMongo/target/*.jar &

java -jar /home/mary/projetcs/springBootServices/myKafka/target/*.jar &

java -jar /home/mary/projetcs/springBootServices/zuulGateWay/target/*.jar &


#ps -ef | grep java | grep target 

#kill -9 $(ps -ef | grep java | grep target | awk '{ print $2}')

