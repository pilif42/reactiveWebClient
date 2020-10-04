# To build:
mvn clean install


# To run:
- Run config in IntelliJ:
       - pointing to ReactiveClientApplication
       - VM Options = -Dspring.profiles.active=dev
- Or: mvn clean package spring-boot:run -DskipTests=true -Dspring-boot.run.profiles=dev


# TODOs
- what about if the creation has failed: see create in CustomerService. Retry mechanism?
- use subscribe on the GET results: examples at https://projectreactor.io/docs/core/release/reference/index.html#_subscribe_method_examples
