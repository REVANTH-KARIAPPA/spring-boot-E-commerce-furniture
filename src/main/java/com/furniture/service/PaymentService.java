package com.furniture.service;


import java.util.List;

import com.furniture.exception.PaymentNotFoundException;
import com.furniture.model.Payment;

public Interface PaymentService {
	public Payment addPayment(Payment payment,int pid,int orderId);
	public boolean deletePaymentById(Integer paymentId);
	public List<Payment> getAllPaymentDetails();
	Payment removePayment(long id) throws PaymentNotFoundException;
	Payment getPaymentById(Integer paymentId) throws PaymentNotFoundException;


}
	
	