# To build:
mvn clean install


# To run:
- Run config in IntelliJ:
       - pointing to ReactiveClientApplication
       - VM Options = -Dspring.profiles.active=dev
- Or: mvn clean package spring-boot:run -DskipTests=true -Dspring-boot.run.profiles=dev


# TODOs
- what about if the creation has failed: see create in CustomerService.
        - need to check for the status code as currently we assume a 201.
        - retry mechanism if diff than 201?
- use subscribe on the GET results: examples at https://projectreactor.io/docs/core/release/reference/index.html#_subscribe_method_examples


# TODOs server-side project
- POST body validation: we are now missing the LOcation header. Why?
- play with the backpressure: https://projectreactor.io/docs/core/release/reference/index.html#_on_backpressure_and_ways_to_reshape_requests
- do we need classic endpoints as with ProfileRestController at https://developer.okta.com/blog/2018/09/24/reactive-apis-with-spring-webflux
        - test endpoints: see AbstractBaseProfileEndpoints, etc. at https://developer.okta.com/blog/2018/09/24/reactive-apis-with-spring-webflux
- Retry & asynch endpoint: how to implement. For instance if the createCustomer in CustomerHandler fails, how to handle it?
- Read:
        - https://spring.io/blog/2016/06/13/notes-on-reactive-programming-part-ii-writing-some-code
        - https://netflixtechblog.com/reactive-programming-in-the-netflix-api-with-rxjava-7811c3a1496a
        - https://docs.spring.io/spring-framework/docs/5.0.0.BUILD-SNAPSHOT/spring-framework-reference/html/web-reactive.html
        - Apply back pressure and test it.    
- Asynch endpoint to stream data from Azure or similar (Blob: see email). 
