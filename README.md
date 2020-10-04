# To build:
mvn clean install


# To run:
- Run config in IntelliJ:
       - pointing to ReactiveClientApplication
       - VM Options = -Dspring.profiles.active=dev
- Or: mvn clean package spring-boot:run -DskipTests=true -Dspring-boot.run.profiles=dev


# TODOs
- start at TODO in CustomerService: once Get All done, do a Get one (bodyToMono)
- test the 2 endpoints (POST & GET) with a WebClient
        - maybe use https://www.baeldung.com/spring-5-webclient as a pointer?
        - how do we access the 5 in Location: /customers/5 when a POST is made?
- use subscribe on the GET results: examples at https://projectreactor.io/docs/core/release/reference/index.html#_subscribe_method_examples
