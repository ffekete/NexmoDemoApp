# Sms application demo
### run:
    spring-boot:run

### compile
    mvn clean install

### usage
Send a POST message to http://localhost:8000/message

With a request body containing:

    from: Where is the message from?
    to: A valid phone number to send the message to.
    body: the message text.

### ToDo before first compile
Before the first compile a valid api key and api secret value needs to be specified in application.yml.

## Docker support
### Build with docker
    mvn clean install docker:build
### Run with docker
    docker run -p {portNumber}:8000 -t nexmodemo

Specify the port number for the docker image.

### Swagger
Added swagger support. To access the api documentation visit:

    http://localhost:8000/swagger-ui.html

### Metrics
Metrics can be accessed by the admin path:

    /admin
    
Implemented metrics:
* Number of started requests
* Latency time of requests