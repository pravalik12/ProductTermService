package com.paymenttermsvc.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymenttermsvc.example.entity.Invoice;

public interface InvoiceRepository extends JpaRepository<Invoice, String>{

}
