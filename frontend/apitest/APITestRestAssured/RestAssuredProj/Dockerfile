FROM salientcrgt2/gradle_base:latest

ENV APP_HOME=/usr/app/
ARG API_DEV_URL
ENV GRADLE_HOME /usr/app/
ENV GRADLE_USER_HOME $GRADLE_HOME
ENV PATH $PATH:$GRADLE_HOME/

WORKDIR $APP_HOME

COPY . .
RUN gradlew clean build -Dwebdriver.base.url=$API_DEV_URL -PignoreTestFailure=true
