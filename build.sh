#!/bin/bash
echo "running mvn build"
mvn clean verify
MVN_BUILD=$?
if [ $MVN_BUILD -ne 0 ]; then
    echo "ERROR: maven build failed, please fix it to continue"
    exit 1
fi
echo "building docker image"
docker build -t rconnect:1.0 .
DOCKER_IMG=$?
if [ $DOCKER_IMG -ne 0 ]; then
  echo "ERROR: docker image build fail"
  exit 2
fi