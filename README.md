## Kalah Game
The game provides a Kalah board and a number of seeds or counters. 
The board has 6 small pits, called houses, on each side; and a big pit, called an end zone, at each end. 
The objective of the game is to capture more seeds than one's opponent.

## Test Kalah
In this POC I used simple components to demonstrate how the kalah game works with java restful, 
no database was used. The intention is to demonstrate the game running in a POC format.
;)

## Stack
* Java 8 
* Spring Boot/Stater
* Maven
* Lombok
* JUnit Test
* swagger (NOT used the API first concept)

## improvements
* Persistence in DB
* Security (Authentication)
* Partner Errors message

## Run the app
**The swagger on http://localhost:8080/swagger-ui.html**

**To use the application, create a game and then make a move**
* create a game: **http://localhost:8080/games/**
* make a move: **http://localhost:8080/games/1/pits/1**

## CommandLine
* Create a game: **curl -H "Content-Type:application/json" -X POST http://localhost:8080/games
* make a move: ** curl -H "Content-Type:application/json" -X PUT http://localhost:8080/games/1/pits/1

## install
* mvn clean install