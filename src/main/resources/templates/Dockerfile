FROM openjdk:8-jdk-alpine
VOLUME /app
{{#mavenBuild}}
ADD target/{{artifactId}}-{{version}}.{{packaging}} /app/{{artifactId}}-{{version}}.{{packaging}}
{{/mavenBuild}}

{{^mavenBuild}}
ADD build/libs/{{artifactId}}-{{version}}.{{packaging}} /app/{{artifactId}}-{{version}}.{{packaging}}
{{/mavenBuild}}

ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /{{artifactId}}-{{version}}.{{packaging}}" ]