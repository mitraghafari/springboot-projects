---------------------------------------@Bean  @Component----------------------
Spring @Bean annotation tells that a method produces a bean to be managed by the Spring container.
 It is a method-level annotation. During Java configuration (@Configuration), the method executed 
 and its return value registered as a bean within a BeanFactory.
 it should be used in a class with @Configuration
 @SpringBootApplication === @Configuration, @EnableAutoConfiguration, and @ComponentScan.
 
 @Component (and @Service and @Repository) are used to auto-detect and auto-configure beans using classpath scanning.
  There's an implicit one-to-one mapping between the annotated class and the bean (i.e. one bean per class).
  Control of wiring is quite limited with this approach, since it's purely declarative.
 @Bean is used to explicitly declare a single bean, rather than letting Spring do it automatically as above.
 
 Sometimes automatic configuration is not an option. When? Let's imagine that you want to wire components from 3rd-party libraries 
 (you don't have the source code so you can't annotate its classes with @Component), so automatic configuration is not possible.
 
 The @Bean annotation returns an object that spring should register as bean in application context. 
 The body of the method bears the logic responsible for creating the instance.
 
-----------------------------------------@Value---------------------------------
 Properties from properties file will be injected by Spring Boot into our configuration beans using the @Value annotation.

------------------------------------------kafka--------------------------------
 to Install on windows:(for test installing kafka and using its builtin zookeeper is enough)
 Install Zookeeper first (I downloaded v3.3.6) zookeeper-3.3.6.tar.gz
 Extract Zookeeper and run this command in powershell/cmd \zookeeper-3.3.6\bin> .\zkServer.cmd Now this should up a Zookeeper instance on localhost:2181
 Download Kafka binary version (I downloaded v0.10.0.1)kafka_2.10-0.10.0.1.tgz
 Extract Kafka, time to modify some configs
 Inside Kafka extraction you can find .\config\server.properties
 In .\config\server.properties replace log.dirs=c:/kafka/kafka-logs
 Note: Make sure to create those folders in relevant paths
 Happy news: Now Kafka ships with windows .bat scripts, You can find these files inside ./bin/windows folder
 Start powershell/cmd and run this command to start Kafka broker  .\bin\windows\kafka-server-start.bat .\config\server.properties
 DONE!, Now you have a running Zookeeper instance and a Kafka broker.

commands(windows folder) --pdf-file
  clear both logs
  zookeeper-server-start.bat  ..\..\config\zookeepr.properties
  kafka-server-start.bat  ..\..\config\server.properties
  kafka-topics.bat --create --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1 --topic test
  kafka-topics.bat --list --bootstrap-server localhost:9092
  kafka-console-producer.bat --broker-list localhost:9092 --topic test
  kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic test --from-beginning

----------------@EmbeddedKafka----------------
this annotation used to test kafka by an embedded kafka
this embedded server get random port, so we set 