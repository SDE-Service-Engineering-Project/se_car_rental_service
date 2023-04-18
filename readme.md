# Car Rental Service


### How to start Car Rental Service

It is important to generate the sources first (for the SOAP Client Stub) before starting the project. To do that, do the following things:

- Start Currency Converter Service (via Docker)
- If necessary, change the URL to the Currency Converter Service im **application.yml** and **pom.xml** file 
- Run the following command: ``mvn clean generate-sources install``
- Now start the Application

To build the application locally, add the following environment variable should be set in your environment:
- `CURRENCY_CONVERTER_URL=http://169.51.206.49:32767/?wsdl`

It is also necessary to start a local postgresql instance (best via Docker) and to setup the **application.yml** properly (DB URL).
Name the database **"postgres"** (or update the application.yml Postgres URL).

To recreate the database, run the following commands:
- `ibmcloud login`
- `kubectl delete services/postgres`
- `kubectl delete deployment/postgres`
- `kubectl delete pvc/postgres-pv-claim`
- `kubectl delete pv/postgres-pv`
- `kubectl apply -f ./kubernetes/db`
