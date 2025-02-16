Read Good examples first

with Spring Boot, Spring Security, Spring Data JPA, Hibernate, HSQL, JSP and Bootstrap.
projects deps:
- spring web 
- spring data jpa
- spring security
- tomcat-embed-jasper : for jsp
- oracle driver 

-----------------spring validation form------------------------------------
if I used springframework.validator, then I have to use spring tag libraries in jsp file.
as I prefer using pure jstl tags, I ignored this sections.

--------------@requestBody vs @@ModelAttribute-----------------------------
You can't use form-data or x-www-form-urlencoded with @RequestBody, they are used when the binding is @ModelAttribute.
@ModelAttribute : working in spring mvc with forms
@requestBody : working in spring mvc with rest(json)
@RequestParam :  working in spring mvc with url query 
@pathVariable :  working in spring mvc with url last part
---------------------------@ResponseStatus----------------------------------
in Spring @ResponseStatus marks a method or exception class with the status code and reason message that should be returned.
The status code applied to the HTTP response when the handler method invoked, or whenever the specified exception thrown. 
It overrides status information set by other means, like ResponseEntity or redirect:.
---------------------------ResponseEntity-----------------------------------
in Spring ResponseEntity represents an HTTP response, including headers, body, and status. While @ResponseBody puts the return value
 into the body of the response, ResponseEntity also allows us to add headers and status code.
 in our mvc Controllers, we can return a ResponseEntity that contains every thing(we can set headers, body and status) to return data(not model)
 somebody said do not overuse it, user @responseBody and ResponseStatus instead
----------------------------Exception handling------------------------------
Spring MVC offers no default (fall-back) error page out-of-the-box(in contrary to spring boot)
The most common way to set a default error page has always been the SimpleMappingExceptionResolver
At start-up, Spring Boot tries to find a mapping for /error
If no view-resolver mapping for /error can be found, Spring Boot defines its own fall-back error page 
- the so-called “Whitelabel Error Page” (a minimal page with just the HTTP status information and any error details

three ways of exception handling in spring mvc:
1-  define a method in controller class and use the annotation @ExceptionHandler on it. Spring configuration will detect 
this annotation and register the method as exception handler for argument exception class and its subclasses.
this method signature and its return type can be anything. Add one method handler per exception.
2- Global exception handler: @ExceptionHandler on class  : @ControllerAdvice
Add one method handler per exception.
3- when a request rejected by security framework, it does not reach the MVC layer so @ControllerAdvice is not an option here.
(for example when the user in login page is incorrect )
   There are 3 interfaces in the Spring Security framework that may be of interest here:  
   org.springframework.security.web.authentication.AuthenticationSuccessHandler
   org.springframework.security.web.authentication.AuthenticationFailureHandler
   org.springframework.security.web.access.AccessDeniedHandler
   You can create implementations of each of these Interfaces in order to customize the response sent for various events:
    successful login, failed login, attempt to access protected resource with insufficient permissions.
    (good link:https://stackoverflow.com/questions/41140669/handle-security-exceptions-in-spring-boot-resource-server)
-------------------actuator-----------------------------
Spring Boot provides actuator to monitor and manage an application. Actuator is a tool which has HTTP endpoints. 
when application pushed to production, you can choose to manage and monitor your application using HTTP endpoints.
Actuator endpoints allow us to monitor and interact with our Spring Boot application. 
Spring Boot includes number of built-in endpoints (12 such as health, beans,..) and we can also add custom.
* we can see all of them after running project in endpoints panel in Intellij *
* I set actuator path=/actuator and I added it in security.permitAll to avoid redirecting to /login page
the steps:
- add actuator dep 
the common endpoints such as health will be added by default, we can config others
* for config-server and eureka-server actuator enabled without adding dep explicitly
---------------------------RestTemplate-------------------------------------
to consume restful services :
RestTemplate is a synchronous client to perform HTTP requests. It offers templates for common scenarios for each HTTP method, 
in addition to the generalized exchange(...) and execute(...) methods that support less frequent cases.

postForEntity,getForEntity,getForObject,PostForObject,...
Create a new resource via POST and return the representation from the response.
------
exchange
More generalized, and less opinionated version, of the above methods that provides extra flexibility when needed. 
It accepts RequestEntity, including HTTP method, URL, headers, and body as input, and returns a ResponseEntity.
These methods allow the use of ParameterizedTypeReference instead of Class to specify a response type with generics.
--------
execute
The most generalized way to perform a request, with full control over request preparation and response extraction via callback interfaces.
-------------
RestTemplate methods with more details:
getForObject() : It retrieves an entity using HTTP GET method on the given URL.
exchange() : Executes the HTTP method for the given URI. It returns ResponseEntity. It can communicate using any HTTP method.
headForHeaders() : Retrieves all headers. It uses HTTP HEAD method.
getForEntity() : It retrieves an entity by using HTTP GET method for the given URL. It returns ResponseEntity.
delete() : Deletes the resources at the given URL. It uses HTTP DELETE method.
put(): It creates new resource or update for the given URL using HTTP PUT method.
postForObject(): It creates new resource using HTTP POST method and returns an entity.
postForLocation(): It creates new resource using HTTP POST method and returns the location of created new resource.
postForEntity(): It creates news resource using HTTP POST method to the given URI template. It returns ResponseEntity.
--------------------sprign-jsp form tag library  -----------------------
<form:form action="/callAcontrollerpath"  modelAttribute="car" method="get"... />  
<form:input path="name" ...>
when we want to call this page, we should pass a model with name car
when we submit this page, 
if method=get --> then inputs(path) go to action as request parameter
if method=post --> then inputs(path) go to action as modelattribute
to read more:
https://www.javatpoint.com/spring-mvc-form-tag-library
**note **
normally we have one button of submit type and the action in form specifys the action should be called (that is value in @Getmapping)
another way that we use to have multiple submit button in one form:
- form without action
- each button have a name
- @Getmapping (params="button-name")

------------------------spring-data-redis----------------------------
redis:Support for extremely large datasets 
dependency:
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>

some usages:
1- use as cache :for example on a controller which get some users(@EnableCaching,@cacheAble,@CachePut,@CacheEvict)
2- use for session-management(@EnableRedisHttpSession,JedisConnectionFactory,${sessionScope.hitCounter})
JedisConnectionFactory (using jedis as client to conn to server)  --> it needs spring-session-dependency
3- use as data-store for short-live data (JedisConnectionFactory, RedisTemplate (redis client))
4- use as message broker (publish/subscribe on a topic)
JedisConnectionFactory, RedisTemplate (redis client)

I used spring session (based on redis) in this app : more note on red notebook.
and I used jedis client to test session manager. remember main app should be up to test 
