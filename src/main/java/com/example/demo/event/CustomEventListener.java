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

    private final Consumer<CustomerDto> customerConsumer = customer -> log.debug("Retrieved successfully customer {}", customer.getId());
    private final Consumer<Throwable> errorConsumer = error -> log.error("An error occurred:", error);

    private final CustomerService customerService;

    public CustomEventListener(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        log.debug("Application Ready Event received.");

        customerService.getAll().subscribe(customerConsumer, errorConsumer);

        customerService.getOne(1L).subscribe(customerConsumer, errorConsumer);
    }
}
