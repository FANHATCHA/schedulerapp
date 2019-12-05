# **Scheduler App V.1.0**

Scheduler Web app built using Java EE + MySQL + Tomcat + JSTL 

![alt text](testing_screenshots/logout.png)

## ABOUT SCHEDULER APP
Problem Description: Coping with many coursework deliverables is a stressful part of every student’s life.SchedulerApp is a web-based which aims to help students cope with multiple coursework assignments.  
The app allows the student to perform the following operations:
### 1. Register to the app
![alt text](testing_screenshots/register_page.PNG)

### 2. Log In / Log Out
![alt text](testing_screenshots/login.PNG)

### 3. Coursework projects (Create - Update - Edit - Delete)
![alt text](testing_screenshots/logout.png)

![alt text](testing_screenshots/create_new_coursework.PNG)
![alt text](testing_screenshots/edit_coursework.PNG)

### 4. Add a list of milestones to the coursework projects
![alt text](testing_screenshots/added_milestones.PNG)

### 5. mark a milestone a completed or not
![alt text](testing_screenshots/markAsCompleted.PNG)

## INSTALLATION PROCESS
> #### 1 - Prerequisites
##### A - Have one of the following IDEA: Intellij IDEA, Eclipse, Netbeans etc
##### B - Have XAMPP or phpMyAdmin installed (Not required). You can other DBMS however make sure you have the right driver.

![alt text](testing_screenshots/xampp.PNG)

![alt text](testing_screenshots/phpmyadmin.PNG)

##### C -  Have Tomcat server running with your Intellij IDEA

![alt text](testing_screenshots/tomcat.PNG)

![alt text](testing_screenshots/tomcat_deployment.PNG)

> #### 2 - Clone the repo using the following command
```cmd
git clone https://github.com/FANHATCHA/schedulerapp.git
```
 > #### 3 - Import database in phpMyAdmin or other DBMS installed in your system
 ##### A - Make sur your have the **db.sql** file in the directory you have just clone
 
 ![alt text](testing_screenshots/db_location.PNG)
 
 ##### B - Open your phpMyAdmin and follow the step highlighted in red to create a new database
 ![alt text](testing_screenshots/create_db_name.PNG)
 
 ##### C - Import the **db.sql** file into your newly created database
 
![alt text](testing_screenshots/browse_sql.PNG)

##### D - If you have followed the same steps as me, you will open the following result

![alt text](testing_screenshots/result_after_importing.PNG)
 