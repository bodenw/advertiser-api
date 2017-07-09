# Advertiser RESTful API
RESTful apis for advertiser, integrated with swagger for API documentation and actuator for application diagnostics

## Installation
Perform the following steps to begin developing in project.

1. Clone the git project.
1. Run `gradlew build eclipse` to build project for eclipse environment
1. Open eclipse and import the project

## Run tests
Perform the following steps to run the jacoco tests. It is important to run the test cases and make sure they all pass before pushing code in order to keep the git repository clean.

1. Run `gradlew test jacocoTestReport`
1. Open the file build\reports\jacoco\test\html\index.html to view the test report.
1. Make sure all test pass.
1. Make sure code coverage is complete for newly added functionality.

## Run the build local
Run the build locally for any manual testing and integration testing.

1. Run `gradlew bootRun`
1. Use a REST client, such as Advanced REST Client, Postman, or SoapUI to test the endpoints.

## View available endpoints
Swagger documentation is provided to view the endpoints that are available in the API. Open a browser to http://localhost:8080/swagger-ui.html to explore the API documentation.

## View actuator info
Actuator is configured to run on http://localhost:8090. The follow endpoints are helpful

* http://localhost:8090/info - contains basic information about the application
* http://localhost:8090/health - contains application health check information
* http://localhost:8090/metrics - contains jvm metrics such as memory usages, uptime, active sessions, etc.
* http://localhost:8090/trace - contains trace information for recent request to API



 