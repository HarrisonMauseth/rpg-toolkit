# RPG Toolkit Overview
In my free time, I love playing tabletop role-playing games with my friends. In the briefest of explanations, this can be thought of as collaborative storytelling that relies on a system of rules everyone at the table agrees to. Most players will craft a singular character and stick with it for weeks.

The role I often play is the person who runs the games. There are a lot of responsibilities that come with running a game which include adjudicating complex rulings, crafting encounters, challenging the players, and an astronomical amount of improvisation. Wearing that many hats gets tricky rather quickly.

This project aims to reduce my workload, increase the fun, and help me maintain steady pacing throughout each session. 

## Application Functionality
This is a server-side REST API which connects to a PostgreSQL by leveraging the DAO pattern and utilizing the Spring framework. Since I want to create a client with remote access to the server, my main focus has been spent on learning how to set up user registration, authentication, and authorization.

### API Endpoints
Since this project is in the early phases of development, I am using the default Spring URL `http://localhost:8080` with the following endpoints:
1. POST `/register` - register a username and password and store it in the `users` entity of the database
2. POST `/login` - log in to the API with a user from the `users` entity of the database

## Testing
The application is currently using JUnit 4 for all unit tests and integration tests.

### Integration Testing
In order to properly follow the DAO pattern, DAO interfaces have been implemented. This allows for ease of testing and helps reduce errors when refactoring. The tests for all DAO classes can be found in the `dao` package.

The class `TestingDatabaseConfig` utilizes environment variables for security purposes, but I have included Postgres's default values as well. This class creates the `MagicItems_test` mock database and uses the `test-schema.sql` and the `test-data.sql` found within the `resources` directory. When testing has completed, the database will be dropped.

The `BaseDaoTest` is an abstract class which brings in the configuration set up in the `TestingDatabaseConfig` class. This allows for the code to be polymorphic as the class can be extended to any DAO testing class.
