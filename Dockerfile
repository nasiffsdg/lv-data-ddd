FROM openjdk:17-alpine
RUN sed -i 's/dl-cdn.alpinelinux.org/mirrors.aliyun.com/g' /etc/apk/repositories && apk update && apk add busybox-extras
ADD ./target/lv-data-ddd-0.0.1-SNAPSHOT.jar /app/lv-data-ddd-0.0.1-SNAPSHOT.jar
CMD java -jar /app/lv-data-ddd-0.0.1-SNAPSHOT.jar
