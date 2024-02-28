# RPG Toolkit Overview
In my free time, I love playing tabletop role-playing games with my friends. In the briefest of explanations, this can be thought of as collaborative storytelling that relies on a system of rules everyone at the table agrees to. Most players will craft a singular character and stick with it for weeks.

The role I often play is the person who runs the games. There are a lot of responsibilities that come with running a game which include adjudicating complex rulings, crafting encounters, challenging the players, and an astronomical amount of improvisation. Wearing that many hats gets tricky rather quickly.

This project aims to reduce my workload, increase the fun, and maintain a steady pace throughout each session. 

***
## Application Functionality
This is a server-side REST API which connects to a PostgreSQL database and utilizes the Spring framework. Since I want to create a client with remote access to the server, my main focus has been spent on learning how to set up user registration, authentication, and authorization.

***
### API Endpoints
As I am still in development, I am running off of the default Spring port of `http://localhost:8080` with the following endpoints:
1. POST `/register` - register a username and password and store it in the `users` entity of the database
2. POST `/login` - log in to the API with a user from the `users` entity of the database


