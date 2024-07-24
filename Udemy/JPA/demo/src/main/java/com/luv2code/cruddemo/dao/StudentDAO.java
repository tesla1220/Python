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

/* 1. 매개변수가 다른 이유
메소드의 매개변수는 메소드가 수행할 작업에 필요한 데이터를 전달하기 위한 것입니다. 따라서 각 메소드는 수행하려는 작업에 따라 필요한 매개변수를 가집니다.

void saveNew(StudentEntity newEntity);
이 메소드는 새로운 StudentEntity 객체를 저장합니다. 저장할 데이터를 전달해야 하므로 StudentEntity 타입의 매개변수를 받습니다.

StudentEntity findId(int theId);
이 메소드는 특정 ID에 해당하는 StudentEntity 객체를 찾습니다. 찾기 위해서는 해당 ID가 필요하므로 int 타입의 ID 매개변수를 받습니다.

List<StudentEntity> findAll();
이 메소드는 모든 StudentEntity 객체를 조회하여 리스트로 반환합니다. 이 메소드는 모든 데이터를 반환하기 때문에 특별한 매개변수가 필요하지 않습니다.

void modifyData(StudentEntity theEntity);
이 메소드는 기존의 StudentEntity 객체를 수정합니다. 수정할 데이터를 전달해야 하므로 StudentEntity 타입의 매개변수를 받습니다.

2. 반환 타입의 차이
메소드의 반환 타입은 메소드가 작업을 수행한 후 결과로 반환하는 데이터의 타입을 의미합니다.

void saveNew(StudentEntity newEntity);
이 메소드는 데이터를 저장하는 작업만 수행하고, 특별한 반환값이 없으므로 void 타입입니다.

StudentEntity findId(int theId);
이 메소드는 특정 ID에 해당하는 StudentEntity 객체를 찾아서 반환합니다. 따라서 반환 타입은 StudentEntity입니다.

List<StudentEntity> findAll();
이 메소드는 모든 StudentEntity 객체를 리스트로 반환합니다. 따라서 반환 타입은 List<StudentEntity>입니다.

void modifyData(StudentEntity theEntity);
이 메소드는 데이터를 수정하는 작업만 수행하고, 특별한 반환값이 없으므로 void 타입입니다.


요약
매개변수는 메소드가 작업을 수행하는 데 필요한 데이터를 전달하기 위한 것이며, 메소드의 목적에 따라 다를 수 있습니다.
반환 타입은 메소드가 작업을 수행한 후 결과로 반환하는 데이터의 타입을 의미하며, 메소드의 결과에 따라 다를 수 있습니다.
따라서 각 메소드의 매개변수와 반환 타입은 그 메소드가 수행하는 작업의 성격에 따라 다르게 정의됩니다.
*/
