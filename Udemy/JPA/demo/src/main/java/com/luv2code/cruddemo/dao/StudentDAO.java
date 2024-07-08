package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;

import java.util.List;

public interface StudentDAO {


    void save(Student theStudent);

    Student findById(Integer id);

    List<Student> findAll();

    List<Student> findByLastName(String theLastName);

    void update(Student theStudent);

    void delete(Integer id);

    /*  Integer : Identifier Type -> The id parameter is a unique identifier (primary key) for the Student entity.
    Primary keys are typically simple data types such as Integer, Long, String, etc., rather than complex entity objects.
    Using Integer makes it clear that the method expects a primary key value, not an entire entity.*/


    int deleteAll();
}

/* 인터페이스를 사용이유
인터페이스: 무엇을 할 것인지 정의 (계약서)
구현 클래스 (여기선 DAOImpl) : 실제로 어떻게 할 것인지 구현
유연성, 유지보수성, 테스트 용이성: 인터페이스 사용 시 구현 방법을 쉽게 변경하고 테스트할 수 있으며, 코드가 더 깔끔해집니다.
*/
