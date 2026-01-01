## we can(should) use jre instead of jdk, since jdk will occupy a lot of space as it has a lot of extra tools/dependencies which we don't need
##FROM openjdk:17
##
##COPY target/*.jar app.jar
##
##ENTRYPOINT["java","-jar","/app.jar"]
#
##using jre instead of jdk to cut down the size
#FROM eclipse-temurin:17.0.4.1_1-jre as builder
#WORKDIR extracted
#ADD target/*.jar app.jar
##java supports exptraction in different layeres(from 9 or 10 ) - and then putting all those layers in extracted directory/folder
#RUN java -Djarmode=layertools -jar app.jar extract
#
#FROM eclipse-temurin:17.0.4.1_1-jre
#WORKDIR application
#COPY --from=builder extracted/dependencies/ ./
#COPY --from=builder extracted/spring-boot-loader/ ./
#COPY --from=builder extracted/snapshot-dependencies/ ./
#COPY --from=builder extracted/application/ ./
#EXPOSE 8080
#ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher"]
#
#
#
#
#

