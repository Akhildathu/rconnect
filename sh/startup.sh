#!/bin/bash
set -e
echo "running tomcat9 from tomcat home : $TOMCAT_HOME"
mysql -hmysql8server -uroot -proot < /u01/data/sql/db-schema.sql
$TOMCAT_HOME/bin/startup.sh &
echo "CMD INSTRUCTION : $@"
exec "$@"

