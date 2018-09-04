# J&J ATM Coding Challenge

## Technologies
 * Spring Boot
 * Mockito
 * JUnit

## Layers

* Controllers: following Spring specification
* Services: following Spring specification
* Repository: As I have no experience with DDBB and Spring Boot I have implemented a way to simulate the behavior of a DDBB using Java.
The models used correspond to the entity classes or tables in the supposed DDBB.


## Deploy

 1. Open a terminal in `jnjatm\jnj-atm-service`
 1. Execute `mvn package`
 1. `cd target`
 1. `java -jar jnj-atm-service-1.0.0-SNAPSHOT.jar`
And the application will start


## Integration tests

 1. Download [SOAPUI](https://www.soapui.org/downloads/soapui.html)
 1. File -> Switch workspace and select `jnjatm\jnj-atm-mock\jnj-atm-workspace.xml`
 1. Execute the test suites or the requests one by one
For more information visit [SOAPUI Documentation](https://www.soapui.org/soapui-projects/soapui-projects.html)
When running the test suites attention must be paid regarding the customer balance. Assertions are prepared to be successful with the initial balance.
As no deposit methods are implemented, the customers will run out of money soon.
For example, for testing Empty ATM, the application shoud be fresh.

## TODO

* Add DDBB connection to it to build a proper persistence layer as a third party will provide data
* In the repository package should there is some logic that should be moved to the services
* Add security to sanitize entries
* Add an exception handler to manage exceptions
* Add security so the pin and account number are not sent raw as parameters. Secure them in anyway they can still are included as parameters in each request as the briefing says.
* Use AOP for repeated code and validations
* Add logs system
* Find a way to use the same Mocks for various test classes (inheritance)

