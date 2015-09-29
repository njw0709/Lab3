##Mobile Proto Lab 3

###Learning Objectives

By the end of this lab, you should be able to:

- Developing universal and relevant Android development skills, including:
  - Improve abilities to create code that uses third party libraries
  - Demonstrate ability to use asynchronous sensors on cell phone
  - Improve ability to work with SQL relational database
  - Become closer to creating Android code that would be considered professional
- Organize your time effectively over the span of a project
- Work effectively with a partner
- Design an intuitive user interface

###Application Objectives

Students will create a scavenger hunt app that will guide a user on a video scavenger hunt. Using the GPS on their phone they will determine if the user has arrived at the next clue location. Once at the next location the user should use the camera to upload an image to Amazon S3 and then download a video showing the next clue. 

To accomplish these goals you will have to learn, use or improve:

- Switching between fragments
- Saving infromation to a web SQL database
- Make HTTP Requests using Volley
- Understand setup process for third party APIs
- Reading device sensor data
- Upload, download, and display media including photos and videos

###App functionalities
- Correctly view GPS data and trigger event when within specific location range
- Upload/download to/from a class SQL database
- Display video from S3 bucket
- Take picture, upload image to S3

###Weekly benchmark goals

You need to meet the following goals by the following dates:
- **8/8** - Your interface should be designed
- **8/15** - Two of the above functionalities should be implemented

###Implementation Tips

Since this lab is longer we will give you some guidance on implementation:

- We expect you to separate the functionalities logically into separate classes. 
- You should use a VideoView to view the video. See documentation here: http://developer.android.com/reference/android/widget/VideoView.html
- Information about sensor data can be found here: https://developer.android.com/guide/topics/sensors/index.html
- Information about the camera can be found here: http://developer.android.com/guide/topics/media/camera.html
- CHRIS WRITE THINGS HERE ABOUT WEB+SQL

*Note: we're intentionally giving you less information. You should be adept at googling issues and finding documentation for classes on your own.*

####Saving data

While it is possible to save the necessary data here in SharedPreferences, we want you to set up the local SQL database on your phone. 
This link provides a very good guide: http://developer.android.com/training/basics/data-storage/databases.html.

It is likely you do not want to save the actual image to the database. Find something that can be used to load the image instead. 

It seems like occasionally JSON data returned form this API will not match what you expect, you should figure out how to smoothly handle this without crashing your application.

###Grading:

1. Functionality(60%):
  - Correctly view GPS data and trigger event when within specific location range (15%)
  - Upload/download to a class SQL database (15%)
  - Display video from S3 (15%)
  - Successfully take picture, upload image to S3 (15%)
2. Code Quality(20%):
  - Android guidelines (10%)
  - Java best practices/ general MVC guidelines/ comments (10%)
3. Division of workload (10%)
4. Effective and intuitive user interface (10%)

