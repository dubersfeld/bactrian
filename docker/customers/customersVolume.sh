#!/bin/bash

# Create a named volume

# from image bactrian/customers-mysql to volume bactrian_customers_db

docker run --name customers_create -d --rm --mount source=bactrian_customers_db,target=/var/lib/mysql \
--env MYSQL_DATABASE=bactrian_customers --env MYSQL_USER=dbuser --env MYSQL_PASSWORD=password1234 bactrian/customers-mysql


