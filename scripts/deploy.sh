#!/bin/bash

TODAY=`date "+%Y%m%d"`
CURRENT_TIME=`date "+%H:%M:%S"`
LOGDIR=/home/ec2-user/app/deploy/Log/$TODAY.log
REPOSITORY=/home/ec2-user/app/deploy
PROJECT_NAME=shophere

echo "[$CURRENT_TIME] ------------------------ [ Start ] ---------------------------" | tee -a $LOGDIR
echo "[$CURRENT_TIME] >> Build 파일 복사" | tee -a $LOGDIR
cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "[$CURRENT_TIME] >> 현재 구동중인 어플리케이션 PID 확인" | tee -a $LOGDIR
CURRENT_PID=$(pgrep -fl shophere | grep jar | awk '{print $1}')

echo "[$CURRENT_TIME] >> 현재 구동 중인 pid: $CURRENT_PID" | tee -a $LOGDIR

if [ -z "$CURRENT_PID" ]; then
        echo "[$CURRENT_TIME] >> 현재 구동 중인 애플리케이션이 없으므로 종료하지 않습니다." | tee -a $LOGDIR
else
        echo ">> kill -15 $CURRENT_PID" | tee -a $LOGDIR
        kill -15 $CURRENT_PID
        sleep 5
fi

echo "[$CURRENT_TIME] >> 새로운 어플리케이션 배포" | tee -a $LOGDIR

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)
echo "[$CURRENT_TIME] >> JAR Name : $JAR_NAME" | tee -a $LOGDIR
echo "[$CURRENT_TIME] >> $JAR_NAME 실행 권한 추가" | tee -a $LOGDIR
chomod +x $JAR_NAME

echo "[$CURRENT_TIME] >> $JAR_NAME 실행" | tee -a $LOGDIR
nohup java -jar -Dspring.config.location=classpath:/application.properties,\
/home/ec2-user/app/properties/application-oauth.properties,\
/home/ec2-user/app/properties/application-real-db.properties \
-Dspring.profiles.active=real \
$JAR_NAME > $REPOSITORY/nohup.out 2>&1 &

echo "[$CURRENT_TIME] ------------------------ [ Finish ] ---------------------------" | tee -a $LOGDIR
echo " "