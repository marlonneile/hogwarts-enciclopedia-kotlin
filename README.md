# Hogwarts Enciclopedia

## About the project

Hogwarts Enciclopedia made using Hilt, Jetpack (LiveData, Room and ViewModel), Retrofit and Coroutines to assimilate the MVVM architecture. It's currently made consuming [HP-API](https://hp-api.herokuapp.com/). In the feature I may construct my own api to expand the project.

## Roadmap

- [x] dispaly all characters
- [x] display details about them
- [x] choose app color theme among 4 houses
- [ ] display all spells
- [ ] display potions, magical items, etc and menu to navigate between these options
- [ ] add particle effects to make it 'magical'

## Build with

- Kotlin Coroutines: For asynchronous operations.
- Jetpack:
  - LiveData: observable that notify view when data changes.
  - Room: database to store data between sessions.
  - ViewModel: manages data between the repository and the view layer.
  - RecyclerView: display large sets of data while minimizing memory usage.
- Retrofit2: fetch data from the REST API.
- Moshi: JSON library to convert the responses inside Kotlin.
- Hilt: for dependency injection.
- Glide: library to load images.
- MMVM architecture (View - DataBinding - ViewModel - Model) and Repository pattern used:
![MVVM architecture](https://developer.android.com/topic/libraries/architecture/images/final-architecture.png)

## Showcase

