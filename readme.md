 
docker network create my_net Markdown. 
docker build infrast/spring_app:v1 . Markdown. 
(src/main/resources/database/Dockerfile) Markdown. 
docker build infrast/pg:v1 . Markdown. 
docker run -it -d -p 5432:5432 --name postgres --network my_net infrast/pg:v1 Markdown. 
docker run -it -d -p 8080:8080 --name app --network my_net infrast/spring_app:v3 Markdown. 
