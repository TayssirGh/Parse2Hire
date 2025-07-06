![Java](https://img.shields.io/badge/language-Java-blue.svg)
# Parse2Hire

## Overview
The **Candidate Response Module** is a core component of an interview platform, designed to streamline communication with candidates. This module leverages advanced syntax parsing via JavaCC to generate responses and integrates with multiple databases to support scalable and efficient data handling.

## Architecture
The project follows a **Pyramid Architecture**:
- **DAL (Data Access Layer)**: Handles all interactions with the databases.
- **Service Layer**: Implements business logic and processes data.
- **WS REST Layer**: Exposes RESTful web services for external communication.

## Features
- **Dynamic Syntax Parsing**: Utilizes JavaCC to interpret and process candidate responses based on predefined syntax rules.
- **Database Integration**: Supports both **MongoDB** and **PostgreSQL** for versatile data storage.
- **RESTful APIs**: Provides endpoints for seamless integration with other modules in the interview platform.

## Technologies
The project is built using:
- **Spring Boot**: For creating a robust and scalable backend.
- **JavaCC**: To parse and process custom syntax.
- **Maven**: For dependency management and build automation.
- **JPA**: For ORM (Object Relational Mapping) with PostgreSQL.
- **MongoDB**: For document-based data storage.
- **PostgreSQL**: For relational data storage.

## Usage
This module is a part of the broader **Interview Platform** ecosystem. It is designed to handle candidate responses with specific syntax, enabling automated and structured communication.

The entry point for the platform is available [here](https://github.com/your-entrypoint-project](https://github.com/TayssirGh/interview-app)).
The frontend application to this platform is available [here](https://github.com/TayssirGh/interview-app-frontend)

## Getting Started
### Prerequisites
- **Java 17** or later
- **Maven** 3.8+ installed
- **MongoDB** and **PostgreSQL** instances running





