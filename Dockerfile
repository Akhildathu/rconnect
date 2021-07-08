FROM ubuntu:21.04
ARG ARG_JDK_DOWNLOAD_URL=https://download.java.net/openjdk/jdk11/ri/openjdk-11+28_linux-x64_bin.tar.gz
ARG ARG_JDK_DIST_FILE=openjdk-11+28_linux-x64_bin
ARG ARG_TOMCAT_DOWNLOAD_URL=https://mirrors.estointernet.in/apache/tomcat/tomcat-9/v9.0.46/bin/apache-tomcat-9.0.46.tar.gz
ARG ARG_TOMCAT_DIST_FILE=apache-tomcat-9.0.46

ENV PATH=${PATH}:${JAVA_HOME}/bin

RUN mkdir -p /u01/data
RUN mkdir -p /u01/data/sh
RUN mkdir -p /u01/data/sql

WORKDIR /u01/data
ADD ${ARG_JDK_DOWNLOAD_URL} .
RUN gunzip ${ARG_JDK_DIST_FILE}.tar.gz
RUN tar -xvf ${ARG_JDK_DIST_FILE}.tar
RUN rm -rf ${ARG_JDK_DIST_FILE}.tar

ADD ${ARG_TOMCAT_DOWNLOAD_URL} .
RUN gunzip ${ARG_TOMCAT_DIST_FILE}.tar.gz
RUN tar -xvf ${ARG_TOMCAT_DIST_FILE}.tar
RUN rm -rf ${ARG_TOMCAT_DIST_FILE}.tar

RUN apt update -y
RUN apt install -y mysql-client


COPY target/rconnect.war ./${ARG_TOMCAT_DIST_FILE}/webapps
COPY sh/startup.sh ./sh/
RUN chmod u+x ./sh/startup.sh

COPY src/main/db/db-schema.sql ./sql

ENTRYPOINT [ "/u01/data/sh/startup.sh" ]
CMD ["tail","-f","/dev/null"]
HEALTHCHECK --interval=30s --timeout=30s --start-period=5s --retries=3 CMD curl -f http://localhost:8080/health || exit 1














