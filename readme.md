# API Gateway

This project is a refactored version of the se_car_rental_service project, utilizing the Strangler Fig Pattern to create a Microservices API Gateway. The API Gateway provides a centralized point of control for microservices, simplifying the process of managing multiple microservices individually. The Spring Cloud Gateway framework is used to create a single-entry point for all client requests, providing routing, load balancing, user authentication, and validation.
Tools and Technologies

* Spring Cloud Gateway
* Spring Security
* Reactor Project

As a publicly available service, the API Gateway forwards requests to downstream microservices. HTTP requests starting with /api/v1/bookings or /api/v1/currency are routed to the booking service, while requests like /api/v1/cars are routed to the car service.

As the API Gateway holds all the user information, a custom header is added to the downstream service call to identify which user is requesting a resource. Downstream services can then validate a user's action directly without requesting the actual entity.
Getting Started

To get started with this project, you should first clone this repository to your local machine. You can do this by running the following command in your terminal:


## Getting Started
Clone the project

``git clone https://github.com/[username]/[repository-name].git``

### Installation

After cloning the repository, navigate to the project directory and install the dependencies by running the following command in your terminal:

``mvn clean install``

## Usage

To start the server, run the following command in your terminal:

``mvn spring-boot:run``

By default, the server will be running at http://localhost:8080.
