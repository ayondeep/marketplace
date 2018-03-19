#Overview

This is an application for a marketplace for employers (seller) to post projects and prospective employees (buyers)
to bid for the projects.

There are REST resources available in this project for creating  and retrieving sellers, buyers, projects and bids.
A project is associated with a seller and a bid is associated with a project and a buyer. It is assumed that a seller
can post multiple projects and a buyer can bid for multiple projects as well as bid for a single project multiple times.
A project has a deadline for placing bids and a maximum budget amount. Any bid that is placed after deadline or exceeds
the maximum budget amount will be rejected.

There is an auction scheduler(AuctionScheduler.java) thread that runs in background when the application starts up.
It polls every 10 seconds to check for projects that has reached its deadline and closes the auction for the project.
The lowest bidder, if any, gets notified. The notification is simply a statement on the server console.

#How to run the application
This is a spring boot application and can be run using maven with following command from the project root:

mvn spring-boot:run

The application starts up at port 8080 and runs in a Tomcat servlet container using H2 in-memory database.

Alternatively, you can run the executable jar file that is produced by "mvn package" command. For example:

java -jar /.target/marketplace-0.0.1-SNAPSHOT.jar

The application is build and tested using Java 1.8. The application should run with Java 9 as well,
however some unit tests may not work with Java 9.

#API Documentation
The api documentation can be found at:

 http://localhost:8080/api-docs

There is also a swagger-ui page for easy listing of the REST resources:

http://localhost:8080/swagger-ui.html


#Comments
- The time the exercise took (after dev environment is set up).
4 hours

- Exercise Difficulty: Easy, Moderate, Difficult, Very Difficult
Moderate

- How did you feel about the exercise itself? (1 lowest, 10 highest—awesome way to assess coding ability) .
10

- How do you feel about coding an exercise as a step in the interview process?  (1 lowest, 10 highest—awesome way to assess coding ability) .
10

- What would you change in the exercise and/or process?
I liked the exercise overall. Some hints on expected level of validation and test code coverage would have been
helpful. Also, any expectation for API versioning and security would he helpful. Currently, I assumed versioning and
security are not a requirement.