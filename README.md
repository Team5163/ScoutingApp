ScoutingApp
===========
To use  or install ScoutingApp, please follow the following instructions:

1. Install some form of MySQL (XAMPP recommended) and modify the code in META-INF/context.xml to
reflect your database credentials.

2. Import sql/scoutdb.sql into your SQL server with the database name scoutdb (all lower case).

3. Clone this repository from GitHub by clicking on the Team menu in NetBeans, mousing over Git,
clicking clone, and entering the following URL: https://github.com/Team5163/ScoutingApp.git
You may leave the user name and password fields blank.

4. Click the green play button in NetBeans. A browser window with the ScoutingApp view page
should open. This serves as a fully functional version of the app. To deploy the app to a 
tomcat server, simply click on the "Clean and Build" button next to the green play button
and place the generated .war file in your webapps directory. With default tomcat settings,
you should be able to access the app by going to localhost:8080/ScoutingApp/
