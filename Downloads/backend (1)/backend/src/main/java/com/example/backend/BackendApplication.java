package com.example.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendApplication {

	public static void main(String[] args) {


PaymentSystem service=new PaymentSystem();

		OrderService os=new OrderService(service);

		os.placeOrder();



	}
}

