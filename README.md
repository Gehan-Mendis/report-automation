# Report Automation
Selenium-based Robotic Process Automation Service to Extract Report Data.

## Project Path
Assume Project Path is:
/rezsystem/workspace_antartica/report-automation

## Clearing Project Dependencies

To clear project dependencies, run:
/rezsystem/workspace_antartica/report-automation$ mvn clean

## Executing the Project

To execute this project, use:

For Dev Environment
/rezsystem/workspace_antartica/report-automation$ mvn clean install -DactiveProfile=dev -DconfigFile=config-dev.properties 

## Creating Jar in IntelliJ

    Open IntelliJ
    Navigate to: File -> Project Structures -> Add -> Jar -> From modules with dependencies
    Select Main Class (Runner.java)
    Click Apply

## Locating Created Jar File in Project

The Jar file is located at:
/rezsystem/workspace_antartica/report-automation/out/artifacts/report_automation_jar 

## Executing Jar

Run the following command:
java -jar report-automation.jar (parameters)

## Docker Build

Build the Docker image:
/rezsystem/workspace_antartica/report-automation$ docker build --build-arg MAVEN_OPTS="-DactiveProfile=dev -DconfigFile=config-dev.properties" -t selenium-app .

## Docker Execution

Run the Docker container:
/rezsystem/workspace_antartica/report-automation$ docker run selenium-app

## Path Locations and Environment Configuration

'application.properties' file indicates where the configuration config.properties is located. 
Ideally, you may not need to change this configuration.

# 'application.properties' file contents
Adjust the paths relative to the deployment environment:
<!-- configFilePath=src/main/resources/config.properties (Default) -->
configFilePath=src/main/resources/config-dev.properties (Dev environment)
<!-- configFilePath=src/main/resources/config_localqa.properties (Local QA environment) -->
<!-- configFilePath=src/main/resources/config_localqa.properties (Local QA environment) -->

# 'config.properties' file contents

Backup-File Location
<!-- relativeDownloadLocation = /rezsystem/workspace_antartica/report-automation/Downloads -->
relativeDownloadLocation = Downloads/

File-Location for Back-End Service
<!-- absoluteDownloadLocation = /rezsystem/workspace_antartica/backend-service/src/main/resources/report -->
absoluteDownloadLocation = src/main/resources/report/

Project Directory
<!-- project.directory=/rezsystem/workspace_antartica/report-automation/ -->
project.directory=.

Maven Home
maven.home=/rezsystem/apache-maven-3.9.2

Availability Report properties
availabilityReportFileName=Availability Report.csv

Comprehensive report properties
comprehensiveReportFileName=Comprehensive Sales Report Detailed.csv

# 'Configuration.xlsm' file contents
This Excel file contains reports to be downloaded or skipped. 
Save relevant values against each report and save in macros-enabled format.

# 'testng.xml' related file path
Based on the deployment path, adjust this path location mentioned in the file:
E.g /rezsystem/workspace_antartica/report-automation/

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE suite SYSTEM "http://testng.org/testng-1.0.dtd">
<suite name="All Test Suite">
    <test verbose="2" preserve-order="true" name="/rezsystem/workspace_antartica/report-automation/">
        <classes>
            <class name="Model.DownloadReports"></class>
        </classes>
    </test>
</suite>

