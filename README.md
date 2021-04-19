# Elcar App
Car sale per year. 

The application is using next technologies:
- based on Maven dependency manager
- JSF framework
- Mongo as database
- Bootstrap library
- Highchart library
- Spring boot

There is a list of electric car sales per year. You can add new ones in a modal dialog. 
Implemented all CRUD methods for this list.

Implemented some call to external rest service. You can test this in a form by filling the nonce field with the click on a button.

For dashboard I used Highchart library and rest service for getting chart data.

# Hi
This app can be currently run as standalone app. It is not yet appropriate to run on Tomcat or Weblogic.

### Tested on environment
- openjdk 11.0.2
- maven 3.6.2

### Build
  mvn clean package spring-boot:repackage

### Run
  java -jar target/elcar-0.0.1-SNAPSHOT.jar
