##Mobile Proto Lab 3

###Learning Objectives

By the end of this lab, you should be able to:

- Developing universal and relevant Android development skills, including:
  - Use device hardware
  - Download and upload media
  - Use a hosted database
- Organize your time effectively over the span of a project
- Work effectively in a group
- Design an effective user interface

###Application Objectives

Students will create a scavenger hunt app that will guide a user on a video scavenger hunt. Using the GPS on their phone they will determine if the user has arrived at the next clue location. Once at the next location the user should use the camera to upload an image to Amazon S3 and then download a video showing the next clue. 

To accomplish these goals you will have to learn/use:

- Switching between fragments
- Saving infromation to a web SQL database
- Make HTTP Requests using Volley
- Understand setup process for third party APIs

###Implementation Tips

Since this lab is longer we will give you some guidance on implementation:

- This lab should contain 1 activity and 2 fragments. One fragment should contain functionality necessary to search for images and add them to your feed and the other should display your feed. It will be easiest to add a button to your screen that switches fragments but doing something like [this](http://developer.android.com/training/animation/screen-slide.html) or [this](https://developer.android.com/training/basics/actionbar/adding-buttons.html) would look much nicer.
- There are two ways to display images in Android, an ImageView and a WebView. WebView is likely easiest.
- To display multiple images the best looking way is likely a ScrollView, but it is fairly difficult to handle some things with a scroll view so we recommend using two buttons to change the currently displayed image. 

####Configuration for Google Custom Search API

Go to the [Google Console](https://console.developers.google.com) and create a new project. Once your project is created go into the project and select the API tab on the left. 
Search for custom search and add the Google Custom Search API to your project.
Under credentials create a new api key. Select server key and copy down this value. This needs to be teh value for your URL parameter "key"


https://cse.google.com/cse/all

That link is where you can add a new custom search engine for your application.
On setup configuration you will want to go to advanced options and add support for the schema ImageObject
After creating your search engine you will want to got ot he control panel and turn on image search.
There are a few parameters you need from your search engine for it to work. The first is the id. You can find that on the control panel with the tab "Search engine id" this id should be set to the URL parameter "cx"

You will also need to get the api key by following the instructions in this link:
https://developers.google.com/custom-search/json-api/v1/introduction#identify_your_application_to_google_with_api_key

####Saving data

While it is possible to save the necessary data here in SharedPreferences, we want you to set up the local SQL database on your phone. 
This link provides a very good guide: http://developer.android.com/training/basics/data-storage/databases.html.

It is likely you do not want to save the actual image to the database. Find something that can be used to load the image instead. 

It seems like occasionally JSON data returned form this API will not match what you expect, you should figure out how to smoothly handle this without crashing your application.

###Grading:

Functionality 75%

- Searching web for images
- Displaying images on app
- Saving images to database
- Loading images from database 
- Switch between fragments without crashing

Code Quality 25%

- Good MVC practices
- Android best practices
