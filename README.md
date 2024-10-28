# ListFon

ListFon is a Spring Boot-based server application that provides a REST API for managing user lists. The application supports basic CRUD operations to interact with lists associated with users.
 [Google Document with screenshots](https://docs.google.com/document/d/1saEg7sWWi3IHI5QoGPtQ1rnHodyFMJlA6VycMucmZ_A/edit?tab=t.0).


## Technology Stack

- **Java 20**: Main programming language
- **Spring Boot**: Framework for creating RESTful API
- **JPA/Hibernate**: For database interaction
- **PostgreSQL**: Embedded database for development and testing
- **JUnit and Mockito**: For unit testing
- **Gradle**: For dependency management and project build

## Getting Started

### Prerequisites

- Java 20 should be installed on your system.
- Gradle should be installed (or use the included Gradle Wrapper).

## REST API

The server provides the following API endpoints for managing user lists:

- **GET /api/lists**: Retrieve all lists for a given user (`userId`).
- **GET /api/lists/{id}**: Retrieve a specific list by its `id` for a given user (`userId`).
- **POST /api/lists**: Create a new list for a user with the specified `userId`.
- **PUT /api/lists/{id}**: Update an existing list by `id` for `userId`.
- **DELETE /api/lists/{id}**: Delete a list by `id` for the specified user (`userId`).


## Testing

The project includes unit tests, which can be run using Gradle:

## License

MIT License

Copyright (c) [2024] [HSE]

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
