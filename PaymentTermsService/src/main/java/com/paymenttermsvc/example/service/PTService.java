package com.paymenttermsvc.example.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paymenttermsvc.example.entity.PaymentTerm;
import com.paymenttermsvc.example.repository.PTRepository;
import com.paymenttermsvc.example.utils.ResultSet;

@Service
public class PTService {

	@Autowired
	private PTRepository repository;

	int exceptionCount = 0;
	int successCount = 0;
	
	public ResultSet<PaymentTerm> addPaymentTerm(PaymentTerm dto) {

		ResultSet<PaymentTerm> responseObject = new ResultSet<PaymentTerm>();
		try {
			Date date = new Date();
			DateFormat df = new SimpleDateFormat("dd/mm/yyyy");
			System.out.println(df.format(date));
			dto.setCreatedDate(date);
			responseObject.setData(repository.save(dto));
			successCount++;
			responseObject.setSuccessCount(successCount);
		} catch (Exception e) {
			exceptionCount++;
			responseObject.setErrorMessage(e.getMessage());
			responseObject.setExceptionCount(exceptionCount);
		}
		return responseObject;

	}

	public ResultSet<PaymentTerm> updatePaymentTerm(PaymentTerm dto) {

		ResultSet<PaymentTerm> responseObject = new ResultSet<PaymentTerm>();
		try {
			PaymentTerm oldTerm = repository.findById(dto.getId()).orElse(null);
			oldTerm.setCode(dto.getCode());
			oldTerm.setDays(dto.getDays());
			oldTerm.setDescription(dto.getDescription());
			oldTerm.setRemindBeforeDays(dto.getRemindBeforeDays());
			responseObject.setData(repository.save(oldTerm));
			successCount++;
			responseObject.setSuccessCount(successCount);

		} catch (Exception e) {
			exceptionCount++;
			responseObject.setErrorMessage(e.getMessage());
			responseObject.setExceptionCount(exceptionCount);
		}

		return responseObject;
	}

	public ResultSet<PaymentTerm> getPaymentTerm(int id) {

		ResultSet<PaymentTerm> responseObject = new ResultSet<PaymentTerm>();
		try {
			responseObject.setData(repository.findById(id));
			successCount++;
			responseObject.setSuccessCount(successCount);
		} catch (Exception e) {
			exceptionCount++;
			responseObject.setErrorMessage(e.getMessage());
			responseObject.setExceptionCount(exceptionCount);
		}
		return responseObject;
	}

	public ResultSet<List<PaymentTerm>> getAllPaymentTerms() {

		ResultSet<List<PaymentTerm>> responseObject = new ResultSet<List<PaymentTerm>>();
		try {
			responseObject.setData(repository.findAll());
			successCount++;
			responseObject.setSuccessCount(successCount);
		} catch (Exception e) {
			exceptionCount++;
			responseObject.setErrorMessage(e.getMessage());
			responseObject.setExceptionCount(exceptionCount);
		}
		System.out.println(successCount);
		return responseObject;

	}
	
	public ResultSet<PaymentTerm> getPaymentTermByCode(String code) {

		ResultSet<PaymentTerm> responseObject = new ResultSet<PaymentTerm>();
		try {
			responseObject.setData(repository.findByCode(code));
			successCount++;
			responseObject.setSuccessCount(successCount);
		} catch (Exception e) {
			exceptionCount++;
			responseObject.setErrorMessage(e.getMessage());
			responseObject.setExceptionCount(exceptionCount);
		}
		return responseObject;
	}

	public ResultSet<PaymentTerm> deletePaymentTerm(int id) {

		ResultSet<PaymentTerm> responseObject = new ResultSet<PaymentTerm>();
		try {
			repository.deleteById(id);
			responseObject.setData("Item deleted with id: " + id);
			successCount++;
			responseObject.setSuccessCount(successCount);
		} catch (Exception e) {
			exceptionCount++;
			responseObject.setErrorMessage(e.getMessage());
			responseObject.setExceptionCount(exceptionCount);
		}

		return responseObject;
	}

}
