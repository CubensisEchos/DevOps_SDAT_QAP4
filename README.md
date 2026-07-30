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
i) <img width="535" height="1010" alt="AddNewMember" src="https://github.com/user-attachments/assets/05d3a853-f72b-4575-ba01-cf22858d2b1a" />

ii) <img width="790" height="1094" alt="GetAllMembers" src="https://github.com/user-attachments/assets/e2774909-984b-4f9a-9ad0-8bbfdcff8710" />

iii) <img width="676" height="1017" alt="SearchByName" src="https://github.com/user-attachments/assets/c1a552c5-3f34-49e7-80c3-7936c865f26f" />

iv) <img width="690" height="955" alt="SearchByMembership" src="https://github.com/user-attachments/assets/b0757d2e-c137-446b-b23c-76397b54fa7e" />

v) <img width="713" height="905" alt="SearchByPhoneNumber" src="https://github.com/user-attachments/assets/3e51ee30-8d91-4d54-8638-3da86be2aed0" />

vi) <img width="612" height="926" alt="SearchForMembersByTournamentStartDate" src="https://github.com/user-attachments/assets/d89f542e-e01d-432c-b7a4-3a833cd1d852" />

vii) <img width="598" height="913" alt="AddNewTournament" src="https://github.com/user-attachments/assets/247d81a0-8754-4ba0-9f20-23dc6877e3db" />

viii) <img width="652" height="946" alt="GetAllTournaments" src="https://github.com/user-attachments/assets/e2238a9e-b31d-4a1e-a0c0-39f05ad4aa63" />

ix) <img width="538" height="932" alt="SearchByLocation" src="https://github.com/user-attachments/assets/8f79ea66-e163-4b64-8459-f151a22ae648" />

x) <img width="540" height="916" alt="SearchTournamentsByStartDate" src="https://github.com/user-attachments/assets/fb26feb9-82bd-4120-9ce2-7d75c4f9b3ac" />

xi) <img width="590" height="1025" alt="RegisterMemberForTournament" src="https://github.com/user-attachments/assets/36926020-2433-4594-bc1a-01b7cd2be747" />


### Docker screenshots
i) <img width="2552" height="1388" alt="dockerRunning" src="https://github.com/user-attachments/assets/48248307-cfd4-4779-82c8-d646b1c7b293" />
