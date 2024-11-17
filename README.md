# Account API For Existing Customers

---

This project provides an API to create accounts for existing customers.

### Summary
The assessment consists of an API used for opening a new "current account" for already existing customers. Additionally, there is an optional simple frontend.

#### Requirements
- The API will expose an endpoint that accepts user information (`customerID`, `initialCredit`).
- Once the endpoint is called, a new account will be opened and connected to the user whose ID is `customerID`.
- If `initialCredit` is not 0, a transaction will be created and added to the new account.
- Another endpoint will provide user information, showing name, surname, balance, and transactions of the accounts.

---

### Application Structure
This repository contains two main parts:
1. **Backend**: Implemented with Java Spring Boot.
2. **Frontend**: A simple frontend to interact with the backend API.

The **backend** and **frontend** directories are set up separately within the root of this repository.

#### Backend (Spring Boot)
The backend consists of REST APIs implemented using Java Spring Boot and exposes two key services:
- **Account API**: To create new accounts for existing customers.
- **Customer API**: To retrieve customer details, including accounts and transactions.

##### API Endpoints
- **Account API**
  - `POST /v1/accounts` - Creates a new account for an existing customer.
    - Request Body:
      ```json
      {
        "customerId": "string",
        "initialCredit": "number"
      }
      ```
  - **Customer API**
    - `GET /v1/customers/{customerId}` - Retrieves details for a specific customer.
    - `GET /v1/customers` - Retrieves details for all customers.

#### Project Setup and Run Instructions

##### Backend Instructions
1. **Docker Setup**: The backend is containerized using Docker for easy deployment.
   - Ensure you have Docker and Docker Compose installed.
2. **Build and Run**:
   - Navigate to the root directory and run the following command to build and start the backend using Docker Compose:
     ```bash
     docker-compose up --build
     ```
   - The backend runs on **port 8080** by default.
3. **Dependencies**:
   - All dependencies are managed via Maven. A multi-stage Docker build is used to compile the project using Java 21.

##### Frontend Instructions
1. **Docker Setup**: The frontend is also containerized for consistent deployments.
   - Similar to the backend, make sure Docker is installed.
2. **Build and Run**:
   - In the root directory, the following command starts the frontend service:
     ```bash
     docker-compose up frontend
     ```
   - The frontend runs on **port 3000** by default.

### Docker Configuration

The project uses Docker Compose for orchestration:
- **Backend** (Java 21, Spring Boot):
  - Built using a Dockerfile that utilizes `eclipse-temurin:21` for both build and run stages.
  - The backend exposes port **8080**.
- **Frontend**:
  - Built using a multi-stage Dockerfile that builds the React application and serves it via Nginx.
  - The frontend is exposed on port **3000**.
- **Database** (Optional - PostgreSQL):
  - The Compose file includes a PostgreSQL database container for persistence.
  - Configure `POSTGRES_USER`, `POSTGRES_PASSWORD`, and `POSTGRES_DB` in `docker-compose.yml`.

### Usage Instructions
After both services are up and running via Docker Compose, you can use tools like **Postman** or **cURL** to interact with the API.
- **Create an Account**:
  ```bash
  curl -X POST http://localhost:8080/v1/accounts -H "Content-Type: application/json" -d '{"customerID": "1234", "initialCredit": 100.0}'
  ```
- **Retrieve Customer Information**:
  ```bash
  curl http://localhost:8080/v1/customers/1234
  ```

### Notes
- This project uses **Java 21** for the backend to ensure access to the latest features and long-term support.
- Backend services are managed via **GitFlow**, and branches like `develop`, `feature/*`, and `hotfix/*` are used for better team collaboration.

---

### Future Improvements
- **Add Authentication**: Implement OAuth or JWT-based authentication for securing endpoints.
- **Tests**: Expand the unit and integration test coverage.
