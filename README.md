Weather App
=================================================
- - -

This project is basic Weather App using Spring Boot API and Auth0.


System Requirements
-------------------
- - -

To use this template, you will need the following to be already installed on your machine:

- Maven 3.x.x
- JDK 20 or above
- Git (Optional)
- Lombok plugin (Optional - Can be installed based on IDE used, either Intellij or Eclipse or any other IDE)

Quick Start
-----------

1. Replace the Auth0 & OpenWeatherMap credentials placeholder in application.yml file at path src/main/java/resouces.

2. Run the following `maven` command from the command line in the root directory of your project:

```
mvn clean package
java -jar target\weather-1.0.0.jar

```

Once started, go to [http://localhost:8085](http://localhost:8085/engine-rest/incident/count) in your browser to launch Weather App:

Weather App has the following functionality.
1. Auth0 authentication. Sign up and you are good to go.
2. Weather App to retrieve weather of major cities across Australia.
3. User Preference to set maximum temperature alert.

The project structure is as follows:

```
pom.xml                             -- Default POM dependencies required for Springboot application
src/
  main/
    java/                           -- Application Java source code goes here
    resources/                      -- Application properties, etc. go here
        application.yml             -- Use to specify configurations 
  test/
    java/                           -- Unit and application integration test Java code goes here
