docker network create my_net
docker build infrast/spring_app:v1 .
(src/main/resources/database/Dockerfile)
docker build infrast/pg:v1 .
docker run -it -d -p 5432:5432 --name postgres --network my_net infrast/pg:v1
docker run -it -d -p 8080:8080 --name app --network my_net infrast/spring_app:v1