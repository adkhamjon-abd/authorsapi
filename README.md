Create a Maven project  with the help of Spring Initializer with the following dependencies: Spring Web, Spring Data JPA, PostgreSQL Driver, Lombok
Since the project interacts with the database in application.properties, configure the PostgreSQL database connection.
Create two entity classes Author and AuthorWork
Create a Controller class to handle requests. getAuthorByName receives the name of the author and returns an Author object in the following way: {"id":5,"name":"Marjorie Rowling","openLibraryId":"OL1817979A"}. getAuthorWorks takes as a parameter an OpenLibarayId of an author and returns an AuthorWithWorks object: {"author": Author,  "works": list of works}
Сreate Service class - an intermediary between Controller class and Repository interface. Add logic to findAuthorByName and findWorksByAuthorId methods.
Create Repository interfaces to communicate with the database and send the values to the Service class.
Create OpenLibraryApi, OpenLibraryApiAuthorDetails, and OpenLibraryApiWorksResponse classes to use them in talking to external API.
Create a configuration file AppConfig to inject RestTemplate to use it in the Service class

