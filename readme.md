# Car Rental Service


### How to start Car Rental Service

It is important to generate the sources first (for the SOAP Client Stub) before starting the project. To do that, do the following things:

- Start Currency Converter Service (via Docker)
- If necessary, change the URL to the Currency Converter Service im **application.yml** and **pom.xml** file 
- Run the following command: ``mvn clean generate-sources install``
- Now start the Application

To build the application locally, add the following environment variable should be set in your environment:
- `CURRENCY_CONVERTER_URL=http://169.51.206.49:32767/?wsdl`
