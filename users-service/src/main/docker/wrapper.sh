#!/bin/sh

while ! `nc -z $CONFIGSERVER_HOST $CONFIGSERVER_PORT`; do 
    echo "********************************************************"
    echo "Waiting for the configuration server to start on port 8888"
    echo "********************************************************"
    sleep 4
done

echo "Config Server up and running at $CONFIGSERVER_HOST:$CONFIGSERVER_PORT"


while ! `nc -z $DATABASE_HOST $DATABASE_PORT`; do 
    echo "********************************************************"
    echo "Waiting for the MySQL server to start on port 3306"
    echo "********************************************************"
    sleep 4 
done

echo "MySQL server up and running at $DATABASE_HOST:$DATABASE_PORT"

java -Djava.security.egd=file:/dev/./urandom -Dspring.cloud.config.uri=$BACTRIAN_CONFIG_URI -Dspring.profiles.active=$PROFILE -jar /app.jar
