### Back End / API

* API has at least four endpoints
* It is a Gradle project written in Java
* The project uses Lambda Functions with following parts
  * Activities
  * Activity Requests with Builders
  * Activity Results (aka Activity Responses) with Builders
  * DynamoDBMapper Annotated Models
  * “POJO” Domain Models with Builders
  * Model Converters
  * Data Access Objects (DAOs)
  * A Dagger Service Component
  * One or more Dagger Modules
* Unit Tests (unit tests must be written for each class except Lambda Functions, the Dagger Service Component and Dagger Modules)
*  “CRUD” on at least one entity
   *  Create a new record
   * Read one or more records
   * Update an existing record
   * Delete a record (you may choose to perform a “soft delete”)
* API endpoints use the appropriate HTTP method
* The project is deployed to AWS using SAM
* The project supports multiple users using Cognito for user authentication


### Front End / UI
* The project has a web a front end demonstrating the functionality of every endpoint

### Database
* The project uses a DynamoDB database
* The project has at least two tables
* The project has at least one Global Secondary Index (GSI)
*  The project has at least one table or one GSI with both a partition key and a sort key
* Any initial data population is automated

