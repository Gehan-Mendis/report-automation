# Use the official Maven image as a build stage
FROM maven:3.8-openjdk-17 AS builder

# Set the working directory
WORKDIR /app

# Copy the source code to the working directory
COPY . .

# Build the Maven project and create the JAR file
ARG MAVEN_OPTS="-DactiveProfile=dev -DconfigFile=config-dev.properties"
RUN mvn clean install $MAVEN_OPTS

# Use the official OpenJDK 17 image as the final image
FROM openjdk:17

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file from the builder stage
COPY --from=builder /app/target/report-automation.jar /app/report-automation.jar

# Copy the appropriate config file based on Maven profile
COPY src/main/resources/config-dev.properties /app/src/main/resources/config-dev.properties

# Copy the Configuration.xlsm file from the source to the working directory in the container
COPY src/main/resources/Configuration.xlsm /app/src/main/resources/Configuration.xlsm

# Expose the port (if your application listens on a specific port)
# EXPOSE 8080

# Set the entry point for the container (replace with your main class)
ENTRYPOINT ["java", "-jar", "report-automation.jar"]
