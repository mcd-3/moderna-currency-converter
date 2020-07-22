# Directory Layout

If you are viewing the code for the first time or need some quick documentation, read below to see what each directory is for and how the code is organised

### adapters
Adapters for Views (ex: Spinners or RecyclerViews)

### data
Data models and how to retrieve this data
 - entities: Model classes
 - network: Interfaces to fetch data over the network
 - repositories: Contains functions used to grab data, put them in model classes, and return data
 
 ### factories
 Factory classes, mostly to create ViewModels
 
 ### listeners
 Classes that generate listeners for views
 
 ### ui
 Fragments for each for each page in the app
 
 ### utils
 Utility classes to help with a specific purpose, like checking for network connectivity
 
 ### viewmodels
 ViewModels linked to each UI fragment (1 fragment <-> 1 viewmodel)
 
 ### MainActivity.kt
 Not a directory, but the parent holder for all UI fragments. Contains session specific code and things shared between fragments
