package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.dao.StudentDAOImpl;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;


/* 순서
1. DAO 클래스에 메소드 정의 Parameter 는 Entity.
2. DAO Impl 클래스에 entity Manager 로 데이터 처리
3. Main app 에 처리할 데이터 내용 정의 */

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

/* StudentDAO를 CommandLineRunner 메서드의 매개변수로 받는 이유는,
이 DAO 객체를 사용하여 애플리케이션 시작 시점에 데이터베이스 관련 작업을 수행하기 위함 */

/* @Bean 메서드 - commandLineRunner:
@Bean 애너테이션이 붙은 메서드는 스프링 컨테이너에 빈을 등록하는 역할을 합니다.
commandLineRunner 메서드는 StudentDAO를 파라미터로 받아 CommandLineRunner 객체를 반환합니다.
CommandLineRunner는 함수형 인터페이스로서, run 메서드를 구현해야 합니다. 이 메서드는 애플리케이션이 실행될 때 호출됩니다.

람다 표현식 (Lambda Expression):
runner -> { } 부분은 람다 표현식으로, CommandLineRunner의 run 메서드를 구현하는 부분입니다.
여기에 원하는 초기화 작업을 추가할 수 있습니다. 예를 들어, 애플리케이션 시작 시 데이터를 초기화하거나 특정 작업을 수행하는 코드를 작성할 수 있습니다.*/

    @Bean
    public CommandLineRunner commandLineRunner(StudentDAO studentDAO){

        return runner -> {
            // createStudent(studentDAO);

            createMultipleStudents(studentDAO);

//            readStudent(studentDAO);

//          queryForStudent(studentDAO);

//            queryForStudentByLastName(studentDAO);

//            updateStudent(studentDAO);

//            deleteStudent(studentDAO);

//            deleteAllStudent(studentDAO);

        };
    }

    private void deleteAllStudent(StudentDAO studentDAO) {

        System.out.println("Deleting all students");

        int numRowsDeleted = studentDAO.deleteAll();

        System.out.println("Deleted row count :" + numRowsDeleted);
    }

    private void deleteStudent(StudentDAO studentDAO) {

        int deleteId = 4;
        System.out.println("Deleting student id : " + deleteId);

        // delete the student based on the ID
        studentDAO.delete(deleteId);

    }

    private void updateStudent(StudentDAO studentDAO) {

        // retrieve student based on the id : primary key
        int studentId = 1;
        System.out.println("Getting student with id : " + studentId);


        /*
            1. find the Data by ID using the existing method for findById
            2. transforming the found data to the Entity Type
        */
        Student myStudent = studentDAO.findById(studentId);
        System.out.println("myStudent : " + myStudent);

        // using setter to update the data
        System.out.println("Updating student");
        myStudent.setFirstName("John");

        // create method on DAO interface to update the data
        studentDAO.update(myStudent);

        // display the updated student
        System.out.println("Updated student : " + myStudent);
    }

    private void queryForStudentByLastName(StudentDAO studentDAO) {

        // get a list of students
        List<Student> theStudents = studentDAO.findByLastName("Doe");

        // display of students
        for (Student tempStudent : theStudents){
            System.out.println(tempStudent);
        }
    }

    private void queryForStudent(StudentDAO studentDAO) {

        // get a list of students
        List<Student> theStudents = studentDAO.findAll();

        // display of students
        for (Student tempStudent : theStudents){
            System.out.println(tempStudent);
        }
    }


    private void readStudent(StudentDAO studentDAO) {

        // create the student object
        System.out.println("Creating new student object !");
        Student tempStudent = new Student("daffy", "Duck", "daffy@luv.com");

        // save the student
        System.out.println("Saving the student..");
        studentDAO.save(tempStudent);

        // display id of the saved student
        int theId = tempStudent.getId();
        System.out.println("Saved student. Generated id : " + theId);

        // retrieve student based on the id : primary key
        System.out.println("Retrieving student with id : " + theId);
        Student myStudent = studentDAO.findById(theId);

        // display student
        System.out.println("Found the student : " + myStudent);

    }

    public void createMultipleStudents(StudentDAO studentDAO) {

        // create multiple students
        System.out.println("Creating 3 student objects !!");
        Student tempStudent1 = new Student("John", "Doe", "paul@luv.com");
        Student tempStudent2 = new Student("Mary", "Public", "maryl@luv.com");
        Student tempStudent3 = new Student("Bonita", "Applebum", "bonita@luv.com");

        // save the student objects
        System.out.println("Saving the student objects!");
        studentDAO.save(tempStudent1);
        studentDAO.save(tempStudent2);
        studentDAO.save(tempStudent3);
    }

    private void createStudent(StudentDAO studentDAO) {

        // create the student object
        System.out.println("Creating new student object !!");
        Student tempStudent = new Student("Paul", "Doe", "paul@luv.com");

        // save the student object
        System.out.println("Saving the student");
        studentDAO.save(tempStudent);

        // display id of the saved student
        System.out.println("Saved student. Generated id: " + tempStudent.getId());
    }

}
