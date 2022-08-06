  #!/bin/bash

  REPOSITORY=/home/ec2-user/app/step2
  PROJECT_NAME=Spring_BootPJ_booboo

  echo "> Build 파일 복사"

  cp $REPOSITORY/zip/*.jar $REPOSITORY

  echo "> 현재 구동중인 애플리케이션 pid 확인"

  CURRENT_PID=$(pgrep -fl Spring_BootPJ_booboo | grep jar | awk '{print $1}')

  echo "현재 구동중인 애플리케이션 : $CURRENT_PID"

  if [ -z "$CURRNET_PID" ]; then
    echo "> kill -15 $CURRENT_PID"
    kill -15 $CURRENT_PID
    sleep 5
  fi

  echo "> 새 애플리케이션 배포"

  JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

  echo "> JAR Name : $JAR_NAME"

  echo "> $JAR_NAME 에 실행권한 추가"

  chmod +x $JAR_NAME

  echo "> $JAR_NAME 실행"

  nohup java -jar \
    -D spring.config.location=classpath:/application.properties,classpath:/application-real.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
    -D spring.profiles.active=real \
    $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &
