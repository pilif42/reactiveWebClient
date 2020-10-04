package com.example.demo.event;

import com.example.demo.dto.CustomerDto;
import com.example.demo.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.function.Consumer;

@Slf4j
@Component
public class CustomEventListener implements ApplicationListener<ApplicationReadyEvent> {

    /**
     * 2 customer consumers to be able to identify in the logs which one outputs first as we do a GET All call followed
     * by a Get One call without blocking. Most of the time, the Get One output is last but at times, it happens first.
     */
    private final Consumer<CustomerDto> customerConsumer = customer -> log.debug("Retrieved successfully customer {}", customer.getId());
    private final Consumer<CustomerDto> uniqueCustomerConsumer = customer -> log.debug("Retrieved successfully unique customer {}", customer.getId());
    private final Consumer<Throwable> errorConsumer = error -> log.error("An error occurred:", error);

    private final CustomerService customerService;

    public CustomEventListener(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        log.debug("Application Ready Event received.");

        customerService.getAll().subscribe(customerConsumer, errorConsumer);

        customerService.getOne(1L).subscribe(uniqueCustomerConsumer, errorConsumer);
    }
}
