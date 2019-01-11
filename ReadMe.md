Prequistes: Java 1.8, Maven

Tools used in Project:

- Spring Boot with Web,JPA,Hibernate
- H2DB database in embedded mode
- Swagger UI for APIs
- Bootstrap for html web pages and also Jquery
- Embedded Tomcat Server(Hence no need of tomcat servlet container)

Deployment or Running application:

  Step 1 : Import as maven project into eclipse and run as java application (OR)                                   
  	   Goto Project folder,get command prompt console and type "mvn clean install" command
  Step 2 : Goto Target Folder and copy .jar file(spring-hibernate-0.0.1-SNAPSHOT.jar) to your desired directory
  Step 3 : Goto that directory and open command prompt and run jar file (java -jar jarfilename  eg: java -jar spring-hibernate-0.0.1-		   SNAPSHOT.jar) , application gets started
  Step 4 : Hit http://localhost:8080/ you will see application

  
DataBase Access Guidelines:
   Url : http://localhost:8080/h2
   Credentials use default and press connect(jdbc url = jdbc:h2:mem:testdb , userName =sa , no password required)
   
Created Swagger-ui as documentation for APIs in  http://localhost:8080/swagger-ui.html.
