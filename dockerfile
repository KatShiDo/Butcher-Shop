FROM ubuntu
RUN apt update \
    && apt install -y openjdk-21-jre

ADD target/butcher_shop-1.0-SNAPSHOT.jar /home/server.jar

WORKDIR /home

CMD ["java", "-jar", "server.jar"]