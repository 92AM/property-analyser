# property-analyser

RESTful Web API for retrieving properties details.

## Introduction

The `property-analyser` is a backend RESTful microservice developed using Spring Boot (https://spring.io/projects/spring-boot) framework and Java 8. It offers three web APIs' and these are listed under the `API` section below.

## Requirements

* Apache Maven 
* Java 8 
* Lombok (https://projectlombok.org/)
* IDE - recommend IntelliJ IDEA (https://www.jetbrains.com/idea/download/)
* Access to the internet to pull down all the required dependencies
* A browser or Postman (https://www.getpostman.com/) to invoke the REST endpoints

Note : please ensure that Lombok plugin is installed in your IDE before building / running the application. For Java and Maven I recommend SDKMAN as it offers seamless switching between versions - https://sdkman.io/install

## Download or clone project

### Download 

* Either download the project from GitHub (https://github.com/92AM/property-analyser) or download the zip file from email
* Unzip the extractable file

### Clone 

* ```git clone https://github.com/92AM/property-analyser.git```

## Building the project

In order to build the project please ensure that you are in the project root directory (i.e. same level as pom.xml) and execute `mvn clean package`. This will execute the goals in sequence, so the project will be cleaned first before being packaged up. Once successfully ran, the `property-analyser.jar` can be found under `\target` directory under the root of project structure. 

### Other build commands

#### Maven clean and install build

`mvn clean install`

#### Maven clean and install build (excluding tests and clover instrumentation)

`mvn clean install -DskipTests -Dmaven.clover.skip=true`

#### Maven clean and test

`mvn clean test`

## Running the application

Once the `property-analyser.jar` file is produced as a result of maven build, run the application by executing `java -jar property-analyser.jar` command from your terminal.

The above command will start up the application on port `8080`, this can be accessed by going to http://localhost:8080/ 

### Running on a different port

The application can be started up in a different port, by changing the configuration as below in `application.yml` file. 

```
server:
  port: 8080
  ```
  
  After changing this, run the application as above; this should take effect as long as the `application.yml` file is in the same location as the `property-analyser.jar` file.
  
  ## API
  
  ### GET /mean/{postcode}/outward
  
  Gets the mean price of all properties for the given postcode outward.
  
  Note : postcode outward is the first part of the postcode. For example, the postcode outward for ‘W1D 3QU’ would be ‘W1D’. The value is case sensitive. 
  
  #### Request Example 1
  
  ```http://localhost:8080/mean/W1F/outward```
  
  #### Response Example 1
  
  ```1158750.0```
  
  #### Request Example 2
  
  ```http://localhost:8080/mean/CV3/outward```
  
  #### Response Example 2
  
  ```0.0```
  
  Note : the response will be `0.0` when no properties are found.
  
  ### GET /average-difference/property/{firstType}/property/{secondType}
  
  Gets the difference in average property prices between two types of properties.
  
  The request will trigger the application to work out the average price of Detached and Flat properties separately and then calculate the difference by reducing the average of Flats from average of Detached properties.
  
  Note : the value is case sensitive. 
  
  #### Request Example 1
  
  ```http://localhost:8080/average-difference/property/Detached/property/Flat```
  
  #### Response Example 1
  
  ```92386.0```
  
   #### Request Example 2
  
  ```http://localhost:8080/average-difference/property/Bungalow/property/Cottage```
  
  #### Response Example 2
  
  ```0.0```
  
  Note : the response will be `0.0` when the specified property types are not found.
  
  ### GET /top/{percent}
  
  Gets a list of most expensive properties which falls within the top given percentage of the price bracket.
  
  The value of `0.10` will trigger the application to work out the 10% of the most expensive properties in the given dataset.
  
  Note : the value needs to match regex ```^(?:0*(?:\.\d+)?|1(\.0*)?)$```
  
  #### Request Example 1
  
  ```http://localhost:8080/top/0.10``` 
  
  #### Response Example 1
  
  ```
  [
    {
        "propertyReference": 12,
        "price": 7500000,
        "bedrooms": 11,
        "bathrooms": 4,
        "houseNumber": "",
        "address": "Brighton Road ",
        "region": "Surrey",
        "postcode": "GU13 4DD",
        "propertyType": "Mansion"
    }
]
```

#### Request Example 2
  
  ```http://localhost:8080/top/some-invalid-string``` 
  
  #### Response Example 2
  
  ```
  {
    "timestamp": "2019-10-16T23:16:20.030+0000",
    "status": 400,
    "error": "Bad Request",
    "message": "Invalid parameter provided : some-invalid-string \n | Reason : uk.co.rightmove.propertyanalyser.exception.PropertyAnalyserException: Input parameter (i.e. some-invalid-string) doesn't match required regex of ^(?:0*(?:\\.\\d+)?|1(\\.0*)?)$. A valid example is 0.10",
    "path": "/top/some-invalid-string"
}
```
  Note : in case of invalid input paramter the response will be the above error message in JSON format, providing the reason of failure.
  
  ## Code coverage
  
  The application was built with TDD approach, this means the unit tests are at the heart of application development. To help keep track of code coverage OpenClover (http://openclover.org/) was used. Once the project is build using maven build tool, please navigate to `property-analyser/target/site/clover/dashboard.html` and open with your favourite browser to check the coverage report; currently it stands at `100%`.
  
  ## Conclusion 
  
  I thoroughly enjoyed taking part in building this solution, I learn a lot along the way! 
  
  ### Future improvements
  
  * Going forward I would love to improve on error handling throughout the application, I feel like currently it is a bit flakey
  * Introduce a front end application (using React - https://reactjs.org/) that could potetially invoke all the APIs' of property-analyser
  * Introduce a BDD module in order to increase visibility, ensure software development meets user need and just to gain more confidence on the solution
