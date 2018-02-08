#!/bin/bash

while ! `nc -z $BACTRIAN_CUSTOMERS_HOST $BACTRIAN_CUSTOMERS_PORT`; do 
    echo "****************************************************************************"
    echo "Waiting for the customers server to start on port " $BACTRIAN_CUSTOMERS_PORT
    echo "****************************************************************************"
    sleep 4 
done

echo "Customers server up and running at $BACTRIAN_CUSTOMERS_HOST:$BACTRIAN_CUSTOMERS_PORT"


while ! `nc -z $BACTRIAN_USERS_HOST $BACTRIAN_USERS_PORT`; do 
    echo "****************************************************************************"
    echo "Waiting for the customers server to start on port " $BACTRIAN_USERS_PORT
    echo "****************************************************************************"
    sleep 4
done

echo "Users server up and running at $BACTRIAN_USERS_HOST:$BACTRIAN_USERS_PORT"


java -Djava.security.egd=file:/dev/./urandom -Dspring.cloud.config.uri=$BACTRIAN_CONFIG_URI -Dspring.profiles.active=$PROFILE -jar /app.jar
