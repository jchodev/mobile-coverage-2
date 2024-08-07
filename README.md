# mobile-coverage
This project  = Clean Architecture + MVI(MVVM+) + Jetpack compose  

Data from: https://api.ofcom.org.uk/

### Description
Retrieve mobile coverage data from the Ofcom API (https://api.ofcom.org.uk/) and display it on the user interface (UI). Enable users to search for coverage information by entering their postcode.

## In this project, was implementation with

### Clean Architecture with MVI 

  
| Layer | Description |
|----- | ------ |
| Presentation Layer | view, view model (MVI)  |
| Domain Layer | Entities, usecase, Repository Interface |
| Data Layer | datamapping, Repository Impl.  |

### UI Demo


https://github.com/jchodev/mobile-coverage-2/assets/100594737/7a04b45e-f9c7-4c80-a72e-bf00efb79e86




### Unit test

Apply with jUnit5, mockk


### UI test

Jetpack compose UI test


https://github.com/jchodev/mobile-coverage/assets/100594737/96346886-a7d2-4eb8-9aaf-026a81b96bc6



### Library

| Library           | Used For                  | Remark |
|-------------------|---------------------------| ------ |
| jetpack           | Jetpack compose           | ---- |
| hilt              | Dependency injection `di` | ------ |
| jacoco            | Test report               | ----|
| turbine           | For testing flow          | ----|
| timber            | Logging                   | ----|
| retrofit2, okhttp | network                   | ----|
| moshi             | parse json                | ----|
| LeakCanery        | detect memory Leak        | ----| 
| Room              | local DB     (for cache)             | ----| 



### build app command
./gradlew assembleDebug


### jacocoReport command
./gradlew jacocoReport
![image](https://github.com/jchodev/mobile-coverage-2/assets/100594737/5c02732e-2d64-49ca-b993-c3ce9c5efe7c)



## TODO
1. cover jacoco report with 100% ![image](https://github.com/jchodev/mobile-coverage-2/assets/100594737/c7056163-040f-4a7e-aa01-35b96da614bc)

2. Full flow of UI test
3. Support landscape
4. Multi-lang
5. Support switch theme
6. ...
