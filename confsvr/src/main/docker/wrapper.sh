#!/bin/sh
echo "********************************************************"
echo "Starting Bactrian Configuration Server"
echo "********************************************************"

java -Djava.security.egd=file:/dev/./urandom -jar /app.jar
