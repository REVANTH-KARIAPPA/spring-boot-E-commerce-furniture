package com.furniture.controller;

import com.furniture.exception.PaymentNotFoundException;
import com.furniture.model.Payment;
import com.furniture.service.EmailSenderService;
import com.furniture.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("payment")
@CrossOrigin(origins = "*", maxAge = 3006)
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private EmailSenderService senderService;

    @PutMapping("/{pid}/order/{orderId}")
    public Payment addPayment(@Valid @RequestBody Payment payment, @PathVariable int pid,@PathVariable int orderId) throws MessagingException{
        senderService.sendSimpleEmail(payment.getEmail(), "your payment at WOODO details are below \n"+payment.toString());
        return this.paymentService.addPayment(payment,pid,orderId);

    }

    @DeleteMapping("delete/{paymentId}")
    public boolean deletePaymentById(@PathVariable("paymentId") Integer paymentId) throws PaymentNotFoundException {
        return this.paymentService.deletePaymentById(paymentId);
    }

    @GetMapping("getby/{id}")
    public Payment getpaymentById(@PathVariable("id") Integer paymentId) throws PaymentNotFoundException {
        return this.paymentService.getPaymentById(paymentId);
    }

    @GetMapping("showAll")
    public List<Payment> getAllPaymentDetails() {
        return this.paymentService.getAllPaymentDetails();
    }

}
