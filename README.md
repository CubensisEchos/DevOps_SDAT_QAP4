# DevOps SDAT QAP4
This is a Spring Boot REST API I made for my DevOps SDAT QAP4. This API has 3 layers, a repository layer, which allows communication with the MYSQL database. A service layer, which holds the logic for each endpoint, 
and a controller layer that handles the HTTP requests and defines the API routes. Each package contains these 3 layers. Search endpoint explanations can be found below, followed by screenshots for submission can be found at the bottom of the read me.

## How to Run in Docker
To run this project in docker, you can follow these steps

1. First ensure that docker desktop is currently running.

2. Then one a terminal in the project root(Folder that contains dockerfile and docker-compose.yml).

3. Then you can build the docker image by running the following command in the project root folder terminal: docker compose build

4. Once the build is complete you can run this command next, to start it: docker compose up

5. The API should now be available at: http://localhost:8080

## Search endpoints

### Member searches
#### GET /members/search/name/{name}

example:

  GET http://localhost:8080/members/search/name/John
  
  This returns all members whose name contains John.

#### GET /members/search/type/{membershipType}

example:

  GET http://localhost:8080/members/search/type/ANNUAL
  
  This returns all members with an annual membership.

#### GET /members/search/phone/{phoneNumber}

example:

  GET http://localhost:8080/members/search/phone/7091235544
  
  This returns all members who's phone number contains 7091235544.
  
#### GET /members/search/tournament-date/{startDate}

example:

  GET http://localhost:8080/members/search/tournament-date/2026-08-15
  
  This returns all members who are registered for a tournament that started on 2026-08-15.

  
### Tournament searches

#### GET /tournaments/search/date/{startDate}

example:

  GET http://localhost:8080/tournaments/search/date/2026-08-15
  
  This returns all tournaments that started on 2026-08-15.

#### GET /tournaments/search/location/{location}

example:

  GET http://localhost:8080/tournaments/search/location/Test
  
  This returns all tournaments taking place in a location that contains Test.
  

## Screenshots

### Postman screenshots

### Docker screenshots

### 

