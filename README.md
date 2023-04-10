# Faculty grade manager

Application for college grade management consisting of 3 services:

1. User service responsible for authenthication, registration,managing profile and storing all user types in it's own database.
2. Grade service responsible for entering grades in database (if user is professor), storing grades in it's own database and viewing grades (if user is sutdent)
3. Service for notifications, it recieves instruction from user and grade services via message broker and sends notification emails to users (example when password is changed or when their grade is signed).

# What I learned

* Many microservice concepts
* How message broker works
* How API gateway for microservices works (Zuul)
* How service discovery works (Eureka)
