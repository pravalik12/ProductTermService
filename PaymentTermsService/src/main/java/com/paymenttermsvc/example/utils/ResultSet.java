package com.paymenttermsvc.example.utils;

public class ResultSet<T> {

	
	private int errorCount;
	private String errorMessage;
	private int successCount;
	private int exceptionCount;
	private Object data;
	private String exceptionMessage;
	private String successMessage;
	private int statusCode;
	
	public int getErrorCount() {
		return errorCount;
	}
	public void setErrorCount(int errorCount) {
		this.errorCount = errorCount;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	public String getSuccessMessage() {
		return successMessage;
	}
	public void setSuccessMessage(String successMessage) {
		this.successMessage = successMessage;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public int getSuccessCount() {
		return successCount;
	}
	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}
	public int getExceptionCount() {
		return exceptionCount;
	}
	public void setExceptionCount(int exceptionCount) {
		this.exceptionCount = exceptionCount;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	



}
