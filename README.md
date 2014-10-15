# MUBS is a simple blog system that allows users to manage their own blog. #

It is developed using **Grails** - a java based web application developement framework. 
The application uses **MySql** database to persist data.
The IDE (Integrated Development Environment) used is **IntelliJ**.

**Grails** is a full stack framework and attempts to solve as many pieces of the web development puzzle through the core technology and its associated plugins. Included out the box are things like:
*      An easy to use Object Relational Mapping (ORM) layer built on Hibernate
* 	An expressive view technology called Groovy Server Pages (GSP)
* 	A controller layer built on Spring MVC
* 	A command line scripting environment built on the Groovy-powered Gant
* 	An embedded Tomcat container which is configured for on the fly reloading
* 	Dependency injection with the inbuilt Spring container
* 	Support for internationalization (i18n) built on Spring's core MessageSource concept
* 	A transactional service layer built on Spring's transaction abstraction.

The source code of the application can be found from here. 
		https://<username>@bitbucket.org/biniama/mubs.git
It can be checked out using **GIT** distributed version control system.

The public can access the web app from
    http://www.localhost:8080/mubs
	
There are three main models (domain classes) and others which are generated by Spring Security Core Plugin.

**Main models**
	Blog
	BlogEntry
	User			- generated by Spring Security Core Plugin but updated to incorporate basic user data

	*Rules (Cardinality in Database):*
	* One User has one Blog.
	* One Blog has many BlogEntries.
	
	** Detail of the model fields can be found in the source code with inline comment.
**Generated by Spring Security Core Plugin.**
	Role
	UserRole
	Requestmap

### Functionalities ###

	**Anyone can** 
		* See short description of blog entries and list of blog entries with number of visits from the home page
		* See detailed view of blog entries
		* See the number of times a blog entry has been visited
		* Sign up for an account
		* Access any user's blog by specifying the username as follows
				http://www.localhost:8080/mubs/blog/<username>
				Example:
				http://www.localhost:8080/mubs/blog/biniamasnake

	**Users who have account at mubs can:**
        * See short description of blog entries and list of blog entries with number of visits from the home page
		* See detailed view of blog entries
        * See the number of times a blog entry has been visited from home page as well as blog entry short and detail description
        * See the number of times total number of all blog entries has been visited from their profile
        * See the number of times blog entries created by the user from their profile
		* Log in / Log out
		* Change password
		* Add new blog entry
		* Edit a blog entry which they created
		* Delete a blog entry which they created
		* See user profile
		* Edit user profile


# How to run the application #

### Prerequisites: ###

	* MySql Server for the Database Management
	* Apache Tomcat Server to host the application

	**// Database Configuration**
	1. Copy the script from {application's source main directory}\grails-app\db-scripts\init\init.mysql.sql
	2. Run the script in MySql to create a blank database and user

	**// Web Application Configuration**
	1. Copy 'mubs.war' from the 'target' folder of the application's source 
	2. Paste it in your tomcat 'webapps' folder
	3. start tomcat server 
	4. Go to http://localhost:8080/mubs
	
	**Test Data:**
	
	**Test Data 1**
		Username: tim.meyerdierks
		Password: password
		Blog Name: KaufDA
		Blog Description: An international technology company
		
		Has one blog entry titled : '10 Reasons To Work at KaufDA'
	
	**Test Data 2**
		Username: biniamasnake
		Password: password
		Blog Name: Computer Tips and Tricks
		Blog Description:  A technology blog about Grails, Java, Android, MySql and More
	
		Has one blog entry titled : 'Grails - Introduction'

The following plugins are used in the project not to reinvent the wheel.
**1. [Spring Security Core](http://grails.org/plugin/spring-security-core)**
To handle security of logging in and securing URLs.
**2. [Twitter Bootstrap](http://grails.org/plugin/twitter-bootstrap)**
A responsive User Interface design framework.