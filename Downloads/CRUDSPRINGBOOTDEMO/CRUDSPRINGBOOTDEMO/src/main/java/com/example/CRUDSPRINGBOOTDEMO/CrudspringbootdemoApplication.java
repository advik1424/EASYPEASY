package com.example.CRUDSPRINGBOOTDEMO;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.http.converter.json.GsonBuilderUtils;

@SpringBootApplication
public class CrudspringbootdemoApplication {

	public static void main(String[] args) {

		SpringApplication.run(CrudspringbootdemoApplication.class, args);


	}
}
/* SQl query-->method */