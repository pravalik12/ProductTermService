package com.paymenttermsvc.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class PaymentTermsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentTermsServiceApplication.class, args);
	}

}
