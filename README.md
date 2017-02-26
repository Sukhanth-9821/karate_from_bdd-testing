# Karate BDD Testing Examples

Examples for writing test scenarios the behavior-driven-development way using Java and [Karate].

[Karate] is a new testing frameworks that eases the task of testing web-services and integrates with well-known tools like JUnit or TestNG.


## Starting the RESTful Webservice for Test

Simply run the following command using [Maven].

```
mvn exec:java -Dexec.mainClass=com.hascode.tutorial.App
```

Afterwards the REST service is accessible at _localhost_, _port 9000_.


## Supported Operations

### Listing all users

```
curl -XGET http://localhost:9000/user
```

### Creating new user

```
curl -XPOST -H"Content-type: application/json" -d'{"id":"","name":"Fred"}' http://localhost:9000/user
```

### Fetching user by id

```
curl -XGET http://localhost:9000/user/BC1572C2-E281-44CE-9CC0-AEA175D1CDFE
```

## Running Karate BDD Tests


```
mvn test
```


## More
For more of my experiments please feel free to visit my blog at [www.hascode.com] or to have a look at my [project repositories].

----

**2017 Micha Kops / hasCode.com**

  [www.hascode.com]:http://www.hascode.com/
  [project repositories]:https://bitbucket.org/hascode/
  [Karate]:https://github.com/intuit/karate
  [Maven]:http://maven.apache.org
