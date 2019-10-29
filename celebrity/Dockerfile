FROM salientcrgt2/gradle_base:latest as build_ms
ARG build
LABEL build=celebrity_ms_${build}
LABEL buildproject=celebrity_builder_ms_${build}

ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME

COPY . .
RUN ./gradlew build test jacocoTestReport


FROM openjdk:8-jre as release_ms
RUN chmod 777 /root
WORKDIR /root/
VOLUME /tmp
COPY --from=build_ms /usr/app/build/libs/celebrity-0.0.1-SNAPSHOT.jar app.jar
RUN chmod 777 /root/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/root/app.jar"]
EXPOSE 8080
