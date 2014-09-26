AppDirect Integration Challenge
===============================

This application serves as a minimal integration of AppDirect's APIs and includes the following:
* Integration of AppDirect's subscription management and user management events
* Spring-based web application using Spring Boot
* Spring Security - protects restricted URLs, validates OAuth 1.0 and OpenID headers and maintains session
* OpenID authentication
* OAuth 1.0 one-legged authentication of incoming requests from AppDirect
* OAuth 1.0 signing of outbound requests to AppDirect
* H2 in-memory SQL database
* Thymeleaf templates and Bootstrap for minimal web UI

The app is live at http://appdirect-eheiker.herokuapp.com

To use my live version of the app you will have to be assigned as a user of the application by me. If you'd like access, email me and I will include you as a user.

To run this application on your own, you will need to do the following:
0. Create a developer account at https://www.appdirect.com/developers/register, create a new product, and obtain a consumer key and consumer secret by going to Sell->Edit Profile->Edit Integration. Set the environment variables on your sytem as follows: APPDIRECT_CONSUMER_KEY={yourconsumerkey};APPDIRECT_SECRET={yoursecret}.
1. Clone this repository to your local system
2. Run 'mvn clean package'
3. Run 'java $JAVA_OPTS -jar -Dlocal.server.port=8080 target/appdirect-0.1.0.jar'
