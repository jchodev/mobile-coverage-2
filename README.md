# mobile-coverage
This project  = Clean Architecture + MVI(MVVM+) + Jetpack compose  

Get data from https://api.ofcom.org.uk/

## In this project, was implementation with

### Clean Architecture with MVI 

  
| Layer | Description |
|----- | ------ |
| Presentation Layer | view, view model (MVI)  |
| Domain Layer | Entities, usecase, Repository Interface |
| Data Layer | datamapping, Repository Impl.  |


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
![jacocoReport](https://github.com/jchodev/mobile-coverage/assets/100594737/66d15a43-8fd1-4467-8973-e1ebc7b44908)

### android modularization
We can modularization (https://developer.android.com/topic/modularization) in this project like this 
![module_screen](https://github.com/jchodev/mobile-coverage/assets/100594737/a425b729-49c4-4063-9c9c-e3fd34cb8c25)


## TODO
1. cover jacoco report with 100%
2. Full flow of UI test
3. Support landscape
4. Multi-lang
5. Support switch theme
6. ...
