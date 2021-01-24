FROM openjdk:8-jdk-alpine

RUN apk add --no-cache curl tar bash

ARG MAVEN_VERSION=3.6.3
ARG BASE_URL=https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries

RUN mkdir -p /usr/share/maven /usr/share/maven/ref \
  && curl -fsSL -o /tmp/apache-maven.tar.gz ${BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
  && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
  && rm -f /tmp/apache-maven.tar.gz

ENV M2_HOME=/usr/share/maven
ENV M2=$M2_HOME/bin
ENV PATH=$M2:$PATH

WORKDIR /data
COPY . /data
EXPOSE 8080

RUN mvn package

WORKDIR /data/target
CMD ["/usr/bin/java", "-jar", "test-0.0.1-SNAPSHOT.jar"]