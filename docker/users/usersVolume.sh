#!/bin/bash

# Create a named volume

# from image bactrian/users-mysql to volume bactrian_users_db

docker run --name users_create -d --rm --mount source=bactrian_users_db,target=/var/lib/mysql \
--env MYSQL_DATABASE=bactrian_users --env MYSQL_USER=dbuser --env MYSQL_PASSWORD=password1234 bactrian/users-mysql


