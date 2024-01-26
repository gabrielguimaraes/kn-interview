# Interview Real Challenge

App to simulate ordering when using aggregate functions in SQL.

This Spring Boot application simulates the management of quotations, where each quotation has a list of products and groups. The application requires Java 17, uses a Gradle Wrapper, an H2 database, and Hibernate.

## Prerequisites

- Java 17
- Gradle Wrapper

## Running the Application

To run the application, ensure you have Java 17 installed. If not, download it from [Oracle's Official Website](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html).

Upon setting up Java 17, follow these simple steps:

1. Clone the repository to your computer.
2. Open your terminal and navigate to the project root directory.
3. Run the Spring Boot application using the Gradle Wrapper:

   ```
   ./gradlew bootRun
   ```
   Note: Windows users should use `gradlew.bat` instead of `./gradlew`.

This command starts the application on port 8080 of your localhost.

## API Endpoints

The application has the following endpoint:

`GET /quotations` - Retrieves all quotations, sorted by group and products. The products are sorted in ascending order and separated by commas. Access the endpoint at [http://localhost:8080/quotations](http://localhost:8080/quotations)

## Database

The application comes with an H2 in-memory database. Hibernate, the ORM layer, enables interaction with the H2 database.

## Overview

This application models a real-world scenario where quotations are linked to various products in different groups. The main components are Quotations and Products, with products organized by groups.

The "/quotations" endpoint displays all quotations, sorted by group and the products they contain. Products are listed in ascending orde and separated by commas.
