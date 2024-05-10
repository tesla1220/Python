package com.ohgiraffers.crud.configuration;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.ohgiraffers.crud", annotationClass = Mapper.class)
public class ReviewSpringCrudApplication {


	public static void main(String[] args) {
		SpringApplication.run(ReviewSpringCrudApplication.class, args);
	}

}
