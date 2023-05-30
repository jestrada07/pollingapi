HTTP Methods:

GET: Retrieves data from a server.
POST: Submits data to be processed by a server.
PUT: Updates existing data on a server.
DELETE: Removes data from a server.
OPTIONS: Retrieves the communication options available for a specific resource.
HEAD: Retrieves the headers of a specific resource without retrieving the actual content.
CONNECT: Converts the connection to a transparent TCP/IP tunnel.
TRACE: Performs a message loop-back test along the path to the target resource.
Exception Handling:
When creating an application, it's important to handle exceptions to prevent the application from crashing. Exception handling allows you to catch and handle errors or exceptional conditions that may occur during the execution of your code. By using try-catch blocks, you can gracefully handle exceptions, display error messages, and take appropriate actions to ensure the stability of your application.

Service Class and Controller:
In the context of application development, it is often recommended to separate the business logic into service classes and handle the interaction with the user interface in the controller classes. This separation of concerns promotes modularity and maintainability. The service classes encapsulate the specific functionality and operations related to the business logic, while the controller classes handle the incoming requests from the user interface and invoke the appropriate methods in the service classes to perform the necessary actions.

Database Relationships:

Many-to-Many: This relationship exists between two entities where multiple instances of one entity can be associated with multiple instances of another entity. For example, in a database for a music store, an artist can have multiple albums, and an album can have multiple artists.

One-to-Many: This relationship exists between two entities where a single instance of one entity can be associated with multiple instances of another entity. For example, in a database for a blog, a single user can have multiple blog posts.

One-to-One: This relationship exists between two entities where a single instance of one entity can be associated with only one instance of another entity, and vice versa. 

