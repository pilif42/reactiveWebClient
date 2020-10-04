package com.example.demo.event;

import com.example.demo.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CustomEventListener implements ApplicationListener<ApplicationReadyEvent> {

    private final CustomerService customerService;

    public CustomEventListener(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        log.debug("Application Ready Event received.");

        customerService.getAll().subscribe(
                customer -> log.debug("Retrieved successfully customer {}", customer.getId()),
                error -> log.error("An error occurred:", error));
    }
}
