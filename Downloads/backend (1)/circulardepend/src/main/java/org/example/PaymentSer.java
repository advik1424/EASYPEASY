package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PaymentSer {



@Autowired
    private orderSer orr;
//
//    public PaymentSer(orderSer orr){
//
//        this.orr=orr;
//
//    }

    public void payment(){
        System.out.println("Payment Completed");

        orr.getorder();




    }
}
