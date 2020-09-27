# NotesApp
This note application is build with Google's latest recommeded jetpack libraries and MVVM architecture.

This is my personal project to demostrate the desing of MVVM architecture and using with various recommended google's jetpack library.
Whole application is written in kotlin.
Note: Data base is designed as separate android lib module to achieve the business logic separation.
      Database is standalone library module here.
      
The libraries and components used in this project are 
1. Navigational Component: Dictates the navigation of user actions while using the application.
    a. Helps to reduce the extra overload of boiler plate code for navigation in app and maintain the navigation stack .
    b. Improves data transations in between fragment with type-safety.
    c. Reduces the manual handling for fragment transaction.
    
2. ViewModel with Live Data: Data handler 
    a. Conserve application's data while surviving the configuration changes.
    
3. Room DataBase: In house database
    a. Abstraction over the SQlite database.
    b. No need to write all the query code by self, annotated functions in Room DAO interface achieves the same.
    c. Improves efficiency and avoid writing error prone code.
    d. No need to deal with cursor object anymore. Room library will do it for you

4. RecyclerView: UI component for view rendering optimisation to avoid runtime memory wastage.

5. Coroutines: Kotlin coroutines for multiThreaded scenarios mostly for database operations.

    
