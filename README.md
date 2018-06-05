### Clean Architecture Example

#### Compile

`./gradlew clean build`

### Run Spring example

`java -jar application/spring-app/build/libs/spring-app-1.0.0.jar`

### Run Vertx example

`java -jar application/vertx-app/build/libs/vertx-app-1.0.0-fat.jar`

###Use the webbapps

#####Create User
```
POST: http://localhost:8080/users/1234
Body:
{
  "email": "test@test.com",
  "password": "mypassword",
  "lastName": "Doe",  
  "firstName": "John"
}
```

#####Get all users
```
GET: http://localhost:8080/users
```

#####Get one user
```
GET: http://localhost:8080/users/0675171368e011e882d5acde48001122
```

#####Login
```
GET: http://localhost:8080/login?email=test@test.com&password=mypassword
```
