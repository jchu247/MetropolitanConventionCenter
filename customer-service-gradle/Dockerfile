FROM gradle:jdk10 as builder
COPY --chown=gradle:gradle . /customer-service-gradle
WORKDIR /customer-service-gradle
RUN gradle bootJar
FROM openjdk:8-jdk-alpine
EXPOSE 8080
VOLUME /tmp
ARG LIBS=customer-service-gradle/build/libs
COPY --from=builder ${LIBS}/ /customer-service-gradle/lib
ENTRYPOINT [“java”,“-jar”,“/build/libs/customer-service-gradle-0.0.1-SNAPSHOT.jar”]
#First, Make sure no other docker images are using port 8080
#build new docker image ‘docker build -t dockerexample .’
#check status of docker image ‘docker images’
#run docker image ‘docker run -p 8080:8080 -it dockerexample’