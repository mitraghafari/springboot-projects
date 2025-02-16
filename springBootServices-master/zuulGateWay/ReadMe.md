
to be a zuul api proxy:

- add zuul dep
- @EnableZuulProxy
-in properties: (check file)


-Pros:
Provides easier interface to clients
Can be used to prevent exposing the internal microservices structure to clients
Allows to refactor microservices without forcing the clients to refactor consuming logic
Can centralize cross-cutting concerns like security, monitoring, rate limiting etc
-Cons:
It could become a single point of failure if proper measures are not taken to make it highly available
Knowledge of various microservice API may creep into API Gateway