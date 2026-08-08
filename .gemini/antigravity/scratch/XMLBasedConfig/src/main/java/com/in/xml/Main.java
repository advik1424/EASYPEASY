package com.in.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.xml.namespace.QName;

public class Main {

    public static void main(String[] args){

        ApplicationContext context=new ClassPathXmlApplicationContext("beans.xml");

        Orde order= (Orde) context.getBean("order");

        order.placeorder();







    }
}
