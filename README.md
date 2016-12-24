# Spring boot applications with docker and kubernetes

### WIP

This repository demonstrates the complete CI/CD of spring boot applications running as docker containers and deployed on
 to kubernetes cluster.

## Prerequisites

* Install latest version of docker on the host
* Docker hub is used as registry for containers 
* Expect the user to have run `docker login` on host to create docker auth config on host

## Running jenkins 
 
* Run `jenkins/build.sh` to build jenkins image 
* Run `jenkins/run.sh` to run jenkins container
* Open the url `http://localhost:8080` in your browser
* Enter the admin password which can be looked up by running `docker logs -f jenkins`
* Install default plugins and finish admin setup

### Creating shopper-build job in jenkins

* Create a new job named 'shopper-build`
* Use `pipeline` as the job template
* Configure the SCM to be pulled source from the build script as Jenkins file from SCM(github.com)

You are good to go. On successful builds, images gets pushed to your dockerhub account.



