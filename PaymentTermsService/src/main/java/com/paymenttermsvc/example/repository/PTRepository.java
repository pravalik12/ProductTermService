package com.paymenttermsvc.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paymenttermsvc.example.entity.PaymentTerm;

public interface PTRepository extends JpaRepository<PaymentTerm, Integer>{

	PaymentTerm findByCode(String code);

}
