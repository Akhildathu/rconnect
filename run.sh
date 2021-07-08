#!/bin/bash
RCONNECT_IMG=$(docker image ls | grep -c rconnect)
if [ $RCONNECT_IMG -ne 1 ]; then
  echo "ERROR: run docker image build before running the application"
  exit 3
fi

RCONNECT_NETWORK_AVAIL=$(docker network ls | grep rconnect_bridge | wc -l)
if [ $RCONNECT_NETWORK_AVAIL -ne 1 ]; then
    echo "INFO: creating rconnect bridge network"
    docker network create rconnect_bridge
fi

MYSQL_CONTAINER_RUN=$(docker container ls | grep mysql8server | wc -l)
if [ $MYSQL_CONTAINER_RUN -ne 1 ]; then
    if [ ! -d /u01/data/mysql ]; then
        echo "INFO: /u01/data/mysql directory is missing creating new"
        sudo mkdir -p /u01/data/mysql
        sudo chmod u+rwx /u01/data/mysql
    fi
    docker container run -d --name mysql8server --network rconnect_bridge -v /u01/data/mysql:/var/lib/mysql -e MYSQL_ROOT_PASSWORD=root mysql:8.0.25    
    echo "waiting for mysql8server to start....."
    sleep 30s
    echo "mysql8server started proceeding to launch rconnect container"    
fi

RCONNECT_CONTAINER_RUN=$(docker container ls | grep -c rconnect)
if [ $RCONNECT_CONTAINER_RUN -eq 1 ]; then
    echo "WARNING: rconnect container already found, stopping and removing to re-run"
    docker container stop rconnect
    docker container rm rconnect
fi
docker container run -d --name rconnect -p 8081:8080 --network rconnect_bridge --env-file=rconnect.env rconnect:1.0
echo "rconnect container launched"







