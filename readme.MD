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

## Docker support
### Build with docker
mvn clean install docker:build
### Run with docker
docker run -p 8000:8000 -t nexmodemo

Specify the port number for the docker image.