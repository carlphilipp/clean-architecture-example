## Clean Architecture Example

### Pre-requisite

Java 10

```
> java --version
java 10 2018-03-20
Java(TM) SE Runtime Environment 18.3 (build 10+46)
Java HotSpot(TM) 64-Bit Server VM 18.3 (build 10+46, mixed mode)
```

### Compile

`./gradlew clean build`

### Run Spring example

`java -jar application/spring-app/build/libs/spring-app-1.0.0.jar`

### Run Vertx example

`java -jar application/vertx-app/build/libs/vertx-app-1.0.0-fat.jar`

### Use the webbapps

#### Create User
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

#### Get all users
```
GET: http://localhost:8080/users
```

#### Get one user
```
GET: http://localhost:8080/users/0675171368e011e882d5acde48001122
```

#### Login
```
GET: http://localhost:8080/login?email=test@test.com&password=mypassword
```
