#!/usr/bin/env bash

docker run --name jenkins -d \
      -v /var/run/docker.sock:/var/run/docker.sock \
      -v /var/jenkins_home -p 8080:8080 jenkins