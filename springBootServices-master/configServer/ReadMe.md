Configuration servers that Spring Cloud supports are the following backends:
GIT(local or github..)
Vault
JDBC

---------------CICD------------------------
my first docker-jenkins files (named ..1) does not work, because mvn command create jar in a different 
folder than docker-build-context. to solve this, i used customWorkspace in building docker image
, but it did not work. some fellow said it is jenkins problem!
so I tried to have maven and java in one docker file.