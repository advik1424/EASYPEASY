package com.in.xml;

import com.in.xml.payments.PaymentService;

public class Orde {

    private PaymentService pays;


    public  void placeorder(){
        pays.payss();

        System.out.println("Place order");
    }

    public Orde(){

        System.out.println("Order Service is created");
    }

    public Orde(PaymentService pays){

        this.pays=pays;

    }

//    public void setPays(PaymentService pays) {
//        this.pays = pays;
//    }
}
