FROM jenkins:latest

USER root
RUN apt-get update \
      && apt-get install -y docker.io \
      && apt-get install -y sudo \
      && rm -rf /var/lib/apt/lists/*
RUN echo "jenkins ALL=NOPASSWD: ALL" >> /etc/sudoers
RUN mkdir /root/.docker \
      && mkdir /root/.kube \
      && touch /root/.kube/config \
      && chmod a+rw /root/.kube/config

RUN curl -LO https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl \
      && mv kubectl /usr/bin/ \
      && chmod a+x /usr/bin/kubectl

COPY kube-config /root/.kube/config