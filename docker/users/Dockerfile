FROM mysql

# Environment variables
ENV MYSQL_ROOT_PASSWORD strata15

# Create Database

COPY ["./users.sql", "/docker-entrypoint-initdb.d"] # push forward
