package com.example.backend;

public class OrderService {


//    private PaymentSystem pays= new PaymentSystem();
//

    private PaymentSystem pays;


    public OrderService(PaymentSystem  pays){

        this.pays=pays;





    }




    public void placeOrder(){

        pays.paymentd();



        System.out.println("Place Order");
    }



}
