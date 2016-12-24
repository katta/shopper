#!/usr/bin/env bash

docker run --name jenkins -d \
      -v /var/run/docker.sock:/var/run/docker.sock \
      -v /var/jenkins_home \
      -v ~/.docker/config.json:/var/jenkins_home/config.json \
      -p 8080:8080 jenkins