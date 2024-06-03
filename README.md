# Spring Boot Maven Project
This project demonstrates a simple Spring Boot application that interacts with a PostgreSQL database and external APIs. It provides RESTful endpoints to fetch author details and their works.

## Dependencies
The project uses the following dependencies:

* Spring Web
* Spring Data JPA
* PostgreSQL Driver
* Lombok

## Configuration
Since the project interacts with the database in application.properties, configure the PostgreSQL database connection.

## Entity
Create two entity classes Author and AuthorWork

## Controller
Create a Controller class to handle requests. getAuthorByName receives the name of the author and returns an Author object in the following way: {"id":5,"name":"Marjorie Rowling","openLibraryId":"OL1817979A"}. getAuthorWorks takes as a parameter an OpenLibarayId of an author and returns an AuthorWithWorks object: {"author": Author,  "works": list of works}

## Service
Сreate Service class - an intermediary between Controller class and Repository interface. Add logic to findAuthorByName and findWorksByAuthorId methods.

## Repository
Create Repository interfaces to communicate with the database and send the values to the Service class.

## Extrnal API Classes
Create OpenLibraryApi, OpenLibraryApiAuthorDetails, and OpenLibraryApiWorksResponse classes to use them in talking to external API.

## App Configuration
Create a configuration file AppConfig to inject RestTemplate to use it in the Service class

