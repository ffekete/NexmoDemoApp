# Sms application demo
### run:
spring-boot:run

### compile
mvn clean install

### usage
Send a POST message to http://localhost:8000/message/to/{phoneNumber}
Where {phoneNumer} must be a valid phone number.

### ToDo before first compile
Before the first compile a valid api key and api secret value needs to be specified in application.yml.

