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

####Using the database
- In the table [such-and-such] in the database at the IP address [PUT_IP_HERE], each row should contain columns name [XXandYY] which are the latitude, longitude, and unique identifier of the video that is the clue to that location. 
- WRITE THINGS HERE ABOUT WEB+SQL

####Video download
- You should use a VideoView to view the video. See documentation here: http://developer.android.com/reference/android/widget/VideoView.html

####Image upload
- We're using the Android S3 SDK. A super-relevant code example can be found here: https://github.com/aws/aws-sdk-android.  You can include the SDK in gradle with the line `compile 'com.amazonaws:aws-java-sdk:1.10.0'`

####Device hardware
- Information about sensor data can be found here: https://developer.android.com/guide/topics/sensors/index.html
- Information about the camera can be found here: http://developer.android.com/guide/topics/media/camera.html

*Note: we're intentionally giving you less information. You should be adept at googling issues and finding documentation for classes on your own.*

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

