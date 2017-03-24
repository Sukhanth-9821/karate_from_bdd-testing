# Karate BDD Testing Examples

Examples for writing test scenarios the behavior-driven-development way using Java and [Karate].

[Karate] is a new testing frameworks that eases the task of testing web-services and integrates with well-known tools like JUnit or TestNG.


## Running the Karate Tests

Simply run it using [Maven]:

```
mvn test
```


## Starting the RESTful Webservice

Simply run the following command using [Maven].

```
mvn exec:java -Dexec.mainClass=com.hascode.tutorial.App
```

Afterwards the REST service is accessible at _localhost_, _port 9000_.


## Supported Operations

REST resources of karate-bdd-testing:
1.0.0

### Return all users

GET user:
 Request:
  No body

 Response:
  Content-Type: application/json
  Status Codes: 200
   Response Body: com.hascode.tutorial.UserResource$1
    {}

### Create new user

POST user:
 Request:
  Content-Type: application/json
  Request Body: com.hascode.tutorial.User
   {"age":0,"id":"string","name":"string","password":"string"}

 Response:
  Content-Type: application/json
  Status Codes: 200
   Response Body: com.hascode.tutorial.User
    {"age":0,"id":"string","name":"string","password":"string"}


### Remove user

DELETE user:
 Request:
  No body

 Response:
  Content-Type: */*
  Status Codes: 200

### Login user

POST user/login:
 Request:
  Content-Type: */*
  Request Body: com.hascode.tutorial.UserResource$Credential
   {"id":"string","password":"string"}

 Response:
  Content-Type: */*
  Status Codes: 200
   Response Body: java.lang.String

  Status Codes: 401

### Secured API: Fetch a date

GET user/secured/date:
 Request:
  No body
  Header Param: Auth-Token, java.lang.String

 Response:
  Content-Type: application/json
  Status Codes: 200
   Response Body: java.lang.String

  Status Codes: 403

### Get user by id

GET user/{userId}:
 Request:
  No body
  Path Param: userId, java.lang.String

 Response:
  Content-Type: application/json
  Status Codes: 200
   Response Body: 


## More
For more of my experiments please feel free to visit my blog at [www.hascode.com] or to have a look at my [project repositories].

----

**2017 Micha Kops / hasCode.com**

  [www.hascode.com]:http://www.hascode.com/
  [project repositories]:https://bitbucket.org/hascode/
  [Karate]:https://github.com/intuit/karate
  [Maven]:http://maven.apache.org
