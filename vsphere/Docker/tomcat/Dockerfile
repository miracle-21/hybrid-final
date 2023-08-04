FROM tomcat:9.0.75

RUN rm -rf /usr/local/tomcat/conf/context.xml
RUN rm -rf /usr/local/tomcat/webapps/ROOT

COPY ./context.xml /usr/local/tomcat/conf/context.xml
COPY ./ROOT.war /usr/local/tomcat/webapps/ROOT.war
COPY ./mysql-connector-j-8.0.33.jar /usr/local/tomcat/lib/mysql-connector-j-8.0.33.jar
EXPOSE 8080
