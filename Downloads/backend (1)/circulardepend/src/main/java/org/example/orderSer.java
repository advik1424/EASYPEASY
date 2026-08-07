package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class orderSer {
@Autowired
    private  PaymentSer pays;


//    public orderSer(PaymentSer pays){
//
//        this.pays=pays;
//
//
//
//    }
    public void placed(){

        pays.payment();

        System.out.println("Your Order is placed");
    }

    public void getorder(){

        System.out.println("Order detail is sending now");
    }


}
