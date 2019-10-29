# scrgt-bdso-cc-ms-celebrity
OC microservices for bdso celebrity API

# Application startup steps

* To Startup the application 
----
	$ ./gradlew bootRun
----

* To pass env variables during startup (for higher env) use the following approach
----
	$ ./gradlew bootRun -Pvarname=value
----

* List of env variables for controlling the Database info for running the app as well as tests in higher envs through gradle
----
    - databaseHost
    - databasePort
    - databaseName
    - databaseAppRole
    - databaseAppPassword
----

* List of env variables to be set up in OCP for the microservice
----
	- DB_HOST
	- DB_PORT
	- DB_NAME
	- DB_USERNAME
	- DB_PASSWORD
	- SECURITY_ENABLED - to be set to true in higher envs
----
