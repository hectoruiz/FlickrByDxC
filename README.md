# Flickr

## Project Description
Simple app with a searcher to look for any photo in Flickr. The app show a grid list with the photos found according the keyword introduced at searcher. Each item shows its thumbnail, title and author. Clicking on an item the apps navigates to its detail where the app shows a better image resolution, its title, author and posted photo date and its description.

## Libraries used
- Dagger Hilt: For dependency injection.
- Coroutines: To retrieve data from API rest outside the UI Thread.
- LiveData: According observer pattern used to get noticed changes from view models on the views.
- Picasso: Retrieve the images from an URL.
- Retrofit: Rest Client for HTTP requests.
- OkHttp3: Used to create a OkHttpClient with some custom properties.
- Moshi: JSON serialization/deserialization
- Architecture Components:
  - Navigation: Used single activity with two fragments. Flow between fragments through navigation.
  - SafeArgs: For passing parameters through fragments using navigation.
  - ViewBinding: Linking the layouts with the views.
  - ViewModels: At the presentation layer.
- JUnit: On the unit tests execution.
- Mockito: To mock instances on unit test processing.
- Used clean architecture with a modularized project.

## How to add Flickr API Key
This repository does not include the API Key to use the Flickr API Rest. So it's necessary add this key on the project for its correct work.

Set the following line on the *local.properties* file in your local project once you have downloaded this project:
- FLICKR_API_KEY=**Your Flickr API key**
