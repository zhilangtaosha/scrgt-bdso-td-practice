FROM salientcrgt2/ft_base_chrome:latest

ENV APP_HOME=/usr/app/
ARG FRONTEND_DEV_URL
ENV GRADLE_HOME /usr/app/
ENV GRADLE_USER_HOME $GRADLE_HOME
ENV PATH $PATH:$GRADLE_HOME/

WORKDIR $APP_HOME
RUN nohup bash -c "/usr/bin/Xvfb :99 &"
COPY . .
RUN gradlew clean build -Dwebdriver.base.url=$FRONTEND_DEV_URL -PignoreTestFailure=true
