#!/bin/bash

PROJECT_JAR=./build/libs/
PROJECT_NAME=shophere

function runFunction() {
	echo " >> Spring Boot App Start"
	java -jar ./RunDir/*.jar
}

function buildFunction() {
	echo " >> Build Start"
	./gradlew clean build

	echo " >> Build Jar Copy & Paste"
	cp -p $PROJECT_JAR/*.jar ./RunDir/
}

function springBootCheck() {
	SPRING_PID=`ps -ef | grep $PROJECT_NAME | head -1 | awk '{print $2}'`

	echo " >> Spring Boot Exit"
	echo " >> $SPRING_PID Exit"
	kill -9 $SPRING_PID
	sleep 5
}

echo " -------------[ Run Start ] ---------------"
echo " >> Spring Boot Pid Check"
springBootCheck

COUNT=`ls -alrt | grep RunDir | wc -l`
if [ $COUNT -ne 0 ]
then
	runFunction
else
	mkdir ./RunDir
	buildFunction
	runFunction
fi
