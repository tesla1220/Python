package com.luv2code.cruddemo.dao;

import com.luv2code.cruddemo.entity.Student;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class StudentDAOImpl implements StudentDAO {

    // define field for entity manager
    private EntityManager entityManager;

    // constructor injection for using Entity Manager
    @Autowired
    public StudentDAOImpl(EntityManager entityManager){
        this.entityManager = entityManager;
    }

    // implement save method
    @Override
    @Transactional
    public void save(Student theStudent) {
        entityManager.persist(theStudent);
    }

    @Override
    public Student findById(Integer id) {
        return entityManager.find(Student.class, id); //  find(Entity Class, primary key)
    }

    @Override
    public List<Student> findAll() {

        // create query
        TypedQuery<Student> theQuery = entityManager.createQuery("FROM Student", Student.class);  // "From Student" = Student Entity Class

        // return query results
        return theQuery.getResultList();

    }

    @Override
    public List<Student> findByLastName(String theLastName) {
        // create query
        TypedQuery<Student> theQuery = entityManager.createQuery(
                                        "FROM Student WHERE lastName=:theData", Student.class);

        // set query parameters
        theQuery.setParameter("theData", theLastName);  // theData: Named parameter / theLastName :actual value

        // return query results
        return theQuery.getResultList();

    }

    @Override
    @Transactional
    public void update(Student theStudent) {
        entityManager.merge(theStudent);
    }

    @Override
    @Transactional
    public void delete(Integer theId) {

        // retrieve the student => 데이터 타입 엔티티로 바꿔줘야함
        Student deleteStudent = entityManager.find(Student.class, theId);

        // delete the student
        entityManager.remove(deleteStudent);

    }

    @Override
    @Transactional
    public int deleteAll() {

        int numRowsDeleted = entityManager.createQuery("DELETE FROM Student ").executeUpdate();

        // Method name "Update" is a generic term. We are "modifying" the database.

        return numRowsDeleted;
    }


}

/* Class Name vs. Table Name:

The HQL (Hibernate Query Language) or JPQL (Java Persistence Query Language) uses the class name, not the table name.
The query language is object-oriented, meaning it deals with Java objects rather than database tables directly.
"FROM Student" refers to the Student entity class, not the student table in the database.

*/

/* void
자바에서 메서드는 항상 반환 타입을 가져야 하며, 반환 타입이 없는 경우에는 void 를 사용하여 명시적으로 표시해야 합니다.
반환 타입을 지정하지 않으면 컴파일 오류가 발생합니다. 따라서 saveStudent 메서드가 값을 반환하지 않는다면 void 를 반환 타입으로 선언해야 하며,
값을 반환해야 한다면 적절한 반환 타입을 지정해야 합니다.
*/
