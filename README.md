Order-Service

A simple console application – an order processing system.
The main business logic will be concentrated in the OrderService class, which depends on the OrderRepository interface.
The goal of the project is to implement the business logic for order processing (for example, saving an order and calculating the total cost) and cover this logic with a set of unit tests, using:
JUnit for writing tests (annotations, assertions, test method structure).
Mockito for mocking the dependency on OrderRepository.
Principles of good testing (one scenario — one test, simplicity and readability of the test code).