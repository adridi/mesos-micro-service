FROM java:8
VOLUME /tmp
ADD springapp-1.0-SNAPSHOT.jar app.jar
EXPOSE 8080
RUN bash -c 'touch /app.jar'
ENTRYPOINT ["java","-Dhttp.proxyHost=127.0.0.1 -Dhttp.proxyPort=4140 -Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]
