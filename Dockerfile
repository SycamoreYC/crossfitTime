# Dockerfile

FROM openjdk:11-jdk AS builder
MAINTAINER yechun.song
COPY . /source
WORKDIR /source
RUN ./gradlew bootJar

FROM openjdk:11-jdk
COPY --from=builder /source/build/libs/crossfitTime-0.0.1-SNAPSHOT.jar /project/
WORKDIR /project
EXPOSE 8080
CMD ["java", "-jar", "crossfitTime-0.0.1-SNAPSHOT.jar"]
