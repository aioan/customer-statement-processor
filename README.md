# Statement management and processing app

## Assumptions

- Transaction IDs need to be globally unique, not just in the file being processed
- Files can be large (limited to 15mb for this PoC), I avoided loading them in memory to make sure the processing will scale
- Reports are stored for a period of time and contain the total number of processed entries and some performance metrics
- Errors in file are reported on individually (e.g. a duplicated transaction will appear in the error report for each duplication)
- Full transaction data is not stored in the database for privacy/security reasons (e.g. valid entries are sent downstream for additional processing)
- File processing is not transactional, we load any correct entries and do not roll back on failure
- More file types could be added in the future
- Only 1 file can be uploaded per API call

## Approach

Files are processed record by record and records are flushed to the database in batches (configured by the JDBC batch size), to reduce the number of DB round trips and scale with larger files. Each valid record has its transaction IT inserted in the database, each invalid record has the transaction id and description inserted in a separate table, linked to the request id.

At the end of processing, a report is generated and saved in the database. Each request is assigned a UUID that is also used as the report id and as a key to retrieve the error list.

Current processing speed is about 1000 records per second when records are unique, although performance will gradually decrease as the database gets larger due to H2 limitations (insert performance decreases significantly with larger table sizes). Processing speed of files with many duplicates is better, as duplicates are filtered out also in memory inside one batch (e.g. 100k duplicates are processed at 2kps, 5k duplicates are processed at 6.5kps).

## Improvements / future plans

- Performance can be improved by extending the batching mechanism to cover validation as well, reducing the number of DB roundtrips
- Reporting on partially processed files could be added, showing the state of the processing until the parsing failed
- Work with `File` or just `InputStream` instead of `MultiPartFile` to cover future requirements in which more file sources will be added (e.g. load from FTP)
- UI tests (not added to save some time to focus on the back-end)
- Authentication and user management with report partitioning per user
- Async processing, report could be flushed to the database from time to time and UI could immediately redirect to the report overview.
- Get all reports API call should use a query that doesn't join in all the error statements, to improve performance. Only eagerly fetch on the report details page 

## Tech stack

The application consists of a back-end API using Spring Boot and an Angular front-end. The maven build will build both projects (including installing node if not present) and the back-end API will also serve the static client files, for ease of use when running locally.

Storage is in memory using an H2 database. Due to this, application restarts will clear the database.

## Running

Maven and Java 8 or higher are required to build and run this app. The Maven build will install node / npm and UI dependencies if they are not present.

Run `mvn spring-boot:run` in the project root to start the server locally. Visit `http://localhost:8080` to access it.