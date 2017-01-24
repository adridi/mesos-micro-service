Simple app to be deployed to Mesos using Spring Boot and Docker

$ mvn clean package docker:build
//cd to dockerfile and
$ docker build -t springapp .

//Docker in mac'
$ docker run -d -p 8080:8080 springapp

//nxgin in linux
$ docker run -d -p 8080:8080 springapp
