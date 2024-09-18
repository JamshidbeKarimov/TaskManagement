# Task Management System

## Overview

This is a Task Management System built with Spring Boot. It provides a RESTful API for managing tasks, including creating, updating, retrieving, and deleting tasks.

## Features

- Create a new task
- Retrieve all tasks with pagination
- Retrieve a task by ID
- Update a task
- Delete a task

## Technologies Used

- Java 17
- Spring Boot 3.3.3
- Spring Data JPA
- H2 Database
- SLF4J and Logback for logging
- Springdoc OpenAPI for API documentation

## Getting Started

### Prerequisites

- Java 17 or higher
- Gradle

### Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/your-username/task-management-system.git
    cd task-management-system
    ```

2. Build the project:
    ```sh
    ./gradlew build
    ```

3. Run the application:
    ```sh
    ./gradlew bootRun
    ```

### Running Tests

To run unit tests:
```sh
./gradlew test
```

### API Documentation
API documentation is available at `/swagger` once the application is running.