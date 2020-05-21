# For Java 11, try this
FROM adoptopenjdk/openjdk11

# Refer to Maven build -> finalName
ARG JAR_FILE=target/sakti-debtor-onboarding-jkt-jar-with-dependencies.jar

# cd /opt/app
WORKDIR /opt/app

# cp target/ttf-lender.jar /opt/app/sakti-debtor-onboarding-jkt-jar-with-dependencies.jar
COPY ${JAR_FILE} ttf-lender.jar

# java -jar /opt/app/app.jar
ENTRYPOINT ["java","-jar","sakti-debtor-onboarding-jkt-jar-with-dependencies.jar"]