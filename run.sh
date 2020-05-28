#!/bin/bash

PROJECT_JAR=./build/libs/

echo " -------------[ Build Start ] ---------------"
./gradlew clean build

echo " >> Build Jar Copy & Paste"
COUNT=`ls -alrt | grep RunDir | wc -l`

if [ $COUNT == 0 ]
then
	mkdir RunDir
fi

cp -p $PROJECT_JAR/*.jar ./RunDir/

echo " >> Start Backend Server"
java -jar ./RunDir/*.jar
