 --------------Eureka client--------------------------
read red notebook page 9 aban
Eureka is a REST (Representational State Transfer) based service that is primarily used in the AWS cloud for locating
 services for the purpose of load balancing and failover of middle-tier servers. We call this service, the Eureka Server.
  Eureka also comes with a Java-based client component, the Eureka Client, which makes interactions with the service 
  much easier. The client also has a built-in load balancer that does basic round-robin load balancing. 
  A Eureka client application is referred to as an instance

for being eureka client:
- just dependency+set name for app is enough
- if we want to set sth but default, use
@EnableDiscoveryClient
application.properties
note: eureka server just shows registered services, when we stop the registered service, it is not removed from service lists until 
we restart eureka server. 

** when we run eureka servers on containers do not use localhost in client
Since its running in docker, don't use localhost. Docker compose lets you refer to container names.
eureka:
  client:
    serviceUrl:
      defaultZone: http://registration:1111/eureka

----------------spring boot test----------------------
The spring-boot-starter-test “Starter” (in the test scope) contains the following provided libraries:

JUnit 4: The de-facto standard for unit testing Java applications.
Spring Test & Spring Boot Test: Utilities and integration test support for Spring Boot applications.
AssertJ: A fluent assertion library.
Hamcrest: A library of matcher objects (also known as constraints or predicates).
Mockito: A Java mocking framework.
JSONassert: An assertion library for JSON.
JsonPath: XPath for JSON.


@SpringBootTest : (first of class test to auto config spring) By default, @SpringBootTest does not start the server. this annotation loads
all beans in spring context. it is good for controller integration test
- if we need to just test web layer, it is no need to inject all beans in context. then we use :
@RunWith(SpringRunner.class) : (first of class test)
@AutoConfigureMockMvc or @AutoConfigureWebTestClient  :If you have web endpoints that you want to test 
or 
@RunWith(SpringRunner.class) 
@WebMvcTest(UserController.class)
Using this annotation will disable full auto-configuration and instead apply only configuration relevant to MVC tests 
(i.e. @Controller, @ControllerAdvice, @JsonComponent, Converter/GenericConverter, Filter, WebMvcConfigurer and HandlerMethodArgumentResolver beans but not @Component, @Service or @Repository beans).

@MockBean : can be used to define a Mockito mock for a bean **inside** your ApplicationContext (we define results for that bean by when())
@InjectMocks : the Above @MockBean s inject in the bean under this annotation. it should has the same field names
it can help to test

-to test service layer (unit test), there is no need to complete spring context(do not use @SpringBootTest). use:
@Runwith(MockitoJunitRunner.class)
and to mock repo layer use @Mock instead of @Mockbean, because there is no spring context to get bean from!
and to have an object from service layer, we can not use @Autowired, instead define and initialize an object explicitly

-to test repo layer, there is no need to complete spring context(do not use @SpringBootTest). use:
 @DataMongoTest 
 @DataJpaTest (read *** section)
 with @Runwith(Springrunner.class)
The annotation disables full auto-configuration and applies only configuration relevant to data tests. 

***:
By default @DataJpaTest uses embeded h2 databaze and ignores the connection string declared in application.properties. 
Annotation @AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) disables this behavior, 
and db configured in application.properties will be used by @DataJpaTest test.

** The context is always cached between tests if it is the same configuration. 
Configuration is determined on loaded configuration files, @MockBean (and friends), @TestPropertySource and some other things. 
If that is what you use then you will get a new context each time.  
