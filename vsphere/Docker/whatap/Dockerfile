FROM openjdk:8-jdk-alpine

RUN mkdir -p /whatap

COPY ./whatap.conf /whatap/
COPY ./paramkey.txt /whatap/
COPY ./whatap-agent.proxy-2.2.12.jar whatap-virtual-2.2.12.jar
COPY ./whatap.agent-2.2.12.jar /whatap/

EXPOSE 8085

ENTRYPOINT ["java", "-javaagent:/whatap/whatap.agent-2.2.12.jar", "-Dwhatap.micro.enabled=true", "-cp", "whatap-virtual-2.2.12.jar", "-Dwhatap.port=8085", "-Duser.timezone=Asia/Seoul", "-Dfile.encoding=UTF-8", "com.virtual.App"]

