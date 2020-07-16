# For Java 11, try this
FROM adoptopenjdk/openjdk11

# Refer to Maven build -> finalName
ARG JAR_FILE=target/sakti-debtor-onboarding.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/sakti-debtor-onboarding.jar /opt/app/sakti-debtor-onboarding.jar
COPY ${JAR_FILE} sakti-debtor-onboarding.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","sakti-debtor-onboarding.jar"]