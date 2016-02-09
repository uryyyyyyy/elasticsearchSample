FROM centos:7
MAINTAINER uryyyyyyy <koki305@gmail.com>
RUN yum update -y
RUN yum clean all
RUN yum install tcl gcc wget make vim net-tools -y

RUN wget -c -O /tmp/jdk-8u65-linux-x64.rpm --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u65-b17/jdk-8u65-linux-x64.rpm
RUN yum -y localinstall /tmp/jdk-8u65-linux-x64.rpm

# install elasticsearch
RUN wget https://download.elasticsearch.org/elasticsearch/release/org/elasticsearch/distribution/tar/elasticsearch/2.2.0/elasticsearch-2.2.0.tar.gz
RUN tar zxvf elasticsearch-2.2.0.tar.gz

RUN adduser elastic
RUN chown -R elastic:elastic elasticsearch-2.2.0/

CMD [ "/bin/bash" ]
