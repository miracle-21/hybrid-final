FROM tomcat:9.0.76

RUN rm -rf /usr/local/tomcat/conf/context.xml
RUN rm -rf /usr/local/tomcat/webapps/ROOT

COPY context.xml /usr/local/tomcat/conf/
COPY ROOT.war /usr/local/tomcat/webapps/

COPY mysql-connector-j-8.0.33.jar /usr/local/tomcat/lib/

EXPOSE 8080
