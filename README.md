# My Rest Kotlin API

This project is a RESTful API developed using Kotlin and Spring Boot. It's designed to manage travel requests for a transportation service.

## Technologies Used

- Kotlin: The main programming language used in this project.
- Spring Boot: The framework used to create the RESTful API.
- Gradle: The build tool used for managing dependencies and building the project.
- JPA/Hibernate: Used for object-relational mapping and data persistence.
- H2 Database: An in-memory database used for development and testing.

## Project Structure

The project is divided into several packages:

- `com.viniciussantos.SpringRestAPI.domain`: Contains the domain entities and services.
- `com.viniciussantos.SpringRestAPI.interfaces`: Contains the API interfaces and DTOs.
- `com.viniciussantos.SpringRestAPI.interfaces.mapping`: Contains mappers for converting between domain entities and DTOs.

## Entities

- `Driver`: Represents a driver in the system.
- `Passenger`: Represents a passenger in the system.
- `TravelRequest`: Represents a travel request made by a passenger.

## API Endpoints

The API provides several endpoints for managing drivers, passengers, and travel requests. For detailed information about the endpoints, please refer to the API documentation.

## Setup and Running

To run this project locally, you need to have Kotlin and Gradle installed on your machine.

1. Clone the repository.
2. Navigate to the project directory.
3. Run `gradle bootRun` to start the application.

The application will start and run on `localhost:8080`.

## Testing

Unit tests and integration tests are included in the project. To run the tests, use the command `gradle test`.

## Contributing

Contributions are welcome. Please fork the repository and create a pull request with your changes.

## License

This project is licensed under the MIT License.