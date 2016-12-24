FROM jenkins:latest

USER root
RUN apt-get update \
      && apt-get install -y docker.io \
      && apt-get install -y sudo \
      && rm -rf /var/lib/apt/lists/*
RUN echo "jenkins ALL=NOPASSWD: ALL" >> /etc/sudoers
RUN mkdir /root/.docker

#USER jenkins
#COPY plugins.txt /usr/share/jenkins/plugins.txt
#RUN /usr/local/bin/install-plugins.sh /usr/share/jenkins/plugins.txt