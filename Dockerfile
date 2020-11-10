FROM adoptopenjdk/openjdk11-openj9:alpine-jre

COPY ./target/*.jar /usr/local/flex-connect/application.jar

RUN adduser -u 10001 -s /sbin/nologin -D -h /dev/null -g flexconnect flexconnect

# https://www.eclipse.org/openj9/docs/introduction/#cloud-optimizations
# RUN APPLICATION
CMD ["java", \
# Use 80% of the memory, and specify we are running in a containerized environment
"-XX:+UseContainerSupport","-XX:MaxRAMPercentage=80","-Xtune:virtualized", \
# Enable quick start https://www.eclipse.org/openj9/docs/xquickstart/
"-Xquickstart", \
"-jar","/usr/local/flex-connect/application.jar"]