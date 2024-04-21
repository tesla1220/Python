package com.ohgiraffers.crud.configuration;


import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.ohgiraffers.crud", annotationClass = Mapper.class)
public class Chap07CrudLectureSourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(Chap07CrudLectureSourceApplication.class, args);
	}

}

// @MapperScan " " 해당 주소 하위에 있는 모든 패키지를 탐색하며 Mapper를 찾겠다
// annotationClass=Mapper.class => Mapper라고 하는 어노테이션이 달린 클래스들을 탐색할 수 있음