package com.paymenttermsvc.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paymenttermsvc.example.service.InvoiceService;

@RestController
public class InvoiceServiceController {

	@Autowired
	InvoiceService invoiceService;

	/* This method is used to manually trigger the Scheduler Job */

	@GetMapping("/remindCustomers")
	public String sendReminder() {
		try {
			invoiceService.runScheduledjob();

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return "Reminder job has been triggered.";
	}

}
