# How to run 
The following was discovered as part of building and run this project:

* First we need an up and running mysql server, in the main project folder there is a docker-compose file which will run docker mysql server. 
```
docker-compose up
```

*  Go to your project directory and run
```
 mvn spring-boot:run
```

*  Alternatively build Spring Boot Project with Maven and run with java -jar command
``` 
mvn clean install
java -jar target/survey-api-0.0.1-SNAPSHOT.jar
```


# Test Api call

* Successful request will return http status code 201 (created)

``` 
 curl -H "Content-Type: application/json" -X POST -d '{
    "name": "Otto",
    "skinCondition": 10,
    "sleepLastNight": 1 
 }'  http://localhost:8080/survey/save
``` 

* Validation errors will return  http status 400 (Bad Request)

``` 
 curl -H "Content-Type: application/json" -X POST -d '{
    "name": "Otto",
    "skinCondition": 20,
    "sleepLastNight": 1 
 }'  http://localhost:8080/survey/save
``` 
response:
``` 
{
    "field": "skinCondition",
    "message": "must be less than or equal to 10"
}
``` 


``` 
 curl -H "Content-Type: application/json" -X POST -d '{
    "skinCondition": 10,
    "sleepLastNight": 1 
 }'  http://localhost:8080/survey/save
``` 
response:
``` 
{
    "field": "name",
    "message": "Name is mandatory"
}
``` 