package com.paymenttermsvc.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.paymenttermsvc.example.entity.PaymentTerm;
import com.paymenttermsvc.example.service.PTService;
import com.paymenttermsvc.example.utils.ResultSet;
import com.paymenttermsvc.example.entity.PaymentTerm;
import com.paymenttermsvc.example.service.PTService;

@RestController
public class PTSController {

	int successCount = 0;
	int errorCount = 0;
	int exceptionCount = 0;

	@Autowired
	PTService paymentservice;

	@PostMapping("/addPaymentTerm")
	public ResultSet<PaymentTerm> addPaymentServiceTerm(@RequestBody PaymentTerm dto) {

		ResultSet<PaymentTerm> responseObject = new ResultSet<>();

		try {
			if (!ObjectUtils.isEmpty(dto)) {
				if (!(dto.getCode() == null) && !(dto.getDays() == 0) && !(dto.getCode() == null)
						&& !(dto.getDescription() == null) && !(dto.getDays() == 0)
						&& !(dto.getRemindBeforeDays() == 0)) {
					// if (true) {
					responseObject = paymentservice.addPaymentTerm(dto);
				} else {
					errorCount++;
					responseObject.setErrorCount(errorCount);
					responseObject.setErrorMessage("Please Provide all details correctly");
				}
			} else {
				errorCount++;
				responseObject.setErrorCount(errorCount);
				responseObject.setErrorMessage("Object is null, Pls provide a valid Object");
			}

		} catch (Exception e) {
			exceptionCount++;
			responseObject.setExceptionCount(exceptionCount);
			responseObject.setExceptionMessage(e.getMessage());
		}

		return responseObject;

	}

	@PutMapping("/updatePaymentTerm")
	public ResultSet<PaymentTerm> updatePaymentTerm(@RequestBody PaymentTerm obj) {

		ResultSet<PaymentTerm> responseObject = new ResultSet<PaymentTerm>();
		try {
			if (!ObjectUtils.isEmpty(obj) && !(obj.getId() == 0)) {
				responseObject = paymentservice.updatePaymentTerm(obj);

			} else {
				errorCount++;
				responseObject.setErrorCount(errorCount);
				responseObject.setErrorMessage("Object is null/id is missing, Pls provide a valid Object");
			}

		} catch (Exception e) {
			exceptionCount++;
			responseObject.setExceptionCount(exceptionCount);
			responseObject.setExceptionMessage(e.getMessage());
		}
		return responseObject;

	}
	
	@GetMapping("/getPaymentTerm/{id}")
	public ResultSet<PaymentTerm> getPaymentTerm(@PathVariable int id) {

		ResultSet<PaymentTerm> responseObject = new ResultSet<PaymentTerm>();
		try {
			if (!(id == 0)) {
				responseObject = paymentservice.getPaymentTerm(id);

			} else {
				errorCount++;
				responseObject.setErrorCount(errorCount);
				responseObject.setErrorMessage("Object is null/id is missing, Pls provide a valid Object");
			}

		} catch (Exception e) {
			exceptionCount++;
			responseObject.setExceptionCount(exceptionCount);
			responseObject.setExceptionMessage(e.getMessage());
		}

		return responseObject;
	}
	
	@GetMapping("/getAllPaymentTerm")
	public ResultSet<List<PaymentTerm>> getAllPaymentTerms() {

		ResultSet<List<PaymentTerm>> responseObject = new ResultSet<List<PaymentTerm>>();
		try {
			responseObject = paymentservice.getAllPaymentTerms();

		} catch (Exception e) {
			exceptionCount++;
			responseObject.setExceptionCount(exceptionCount);
			responseObject.setExceptionMessage(e.getMessage());
		}

		return responseObject;
	}
	
	@DeleteMapping("/deletePaymentTerm/{id}")
	public ResultSet<PaymentTerm> deletePaymentTerm(@PathVariable int id ) {
		
		ResultSet<PaymentTerm> responseObject = new ResultSet<PaymentTerm>();
		try {
			responseObject = paymentservice.deletePaymentTerm(id);
		}catch (Exception e) {
			exceptionCount++;
			responseObject.setExceptionCount(exceptionCount);
			responseObject.setExceptionMessage(e.getMessage());
		}
		return responseObject;
	
	}
	

	@GetMapping("/getMessage")
	public String getMessageFromService() {
		System.out.println("Hello World.");
		return "Connection has been established..!";

	}

}
