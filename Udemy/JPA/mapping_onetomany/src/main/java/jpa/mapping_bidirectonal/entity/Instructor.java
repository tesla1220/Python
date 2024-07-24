package jpa.mapping_bidirectonal.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "instructor")
public class Instructor {

    // annotate the class as an entity and map to db table

    // define the fields

    // annotate the fields with db column names

    // ** set up mapping to InstructorDetail entity
    // create constructors

    // generate getter / setter methods

    // generate toString() method
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "instructor_detail_id")
    private InstructorDetail instructorDetail;


    @OneToMany(mappedBy = "instructor",         // Refers to "instructor" property in "Course" class
            fetch = FetchType.LAZY,     // FetchType for @OneToMany defaults to lazy
            cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.DETACH})
    private List<Course> courses;


    public Instructor() {
    }

    public Instructor(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InstructorDetail getInstructorDetail() {
        return instructorDetail;
    }

    public void setInstructorDetail(InstructorDetail instructorDetail) {
        this.instructorDetail = instructorDetail;
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", instructorDetail=" + instructorDetail +
                '}';
    }


    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }


    // add convenience methods for bi-directional relationship
    public void add(Course tempCourse){

        if (courses == null ){
            courses = new ArrayList<>();
        }

        courses.add(tempCourse);

        tempCourse.setInstructor(this);

    }
}

/*
물론입니다! 주어진 코드를 쉽게 설명해드리겠습니다.
코드 전체 설명

java

public void add(Course tempCourse) {
    if (courses == null) {
        courses = new ArrayList<>();
    }

    courses.add(tempCourse);

    tempCourse.setInstructor(this);
}

이 메서드는 Instructor 클래스의 메서드로, Course 객체를 Instructor 객체의 courses 리스트에 추가하는 기능을 합니다.
각 줄의 설명

    메서드 선언:

    java

public void add(Course tempCourse) {

    public void add(Course tempCourse)는 add라는 이름의 메서드를 선언합니다.
    이 메서드는 Course 타입의 tempCourse 매개변수를 받아들이며, 반환값이 없습니다(void).

리스트 초기화 검사:

java

if (courses == null) {
    courses = new ArrayList<>();
}

    if (courses == null) {}: courses 리스트가 초기화되지 않았는지 확인합니다.
    courses == null: courses 필드가 null인 경우, 즉, 아직 초기화되지 않은 경우를 의미합니다.
    courses = new ArrayList<>();: courses 리스트를 새 ArrayList로 초기화합니다. 이렇게 함으로써 courses 필드가 빈 리스트로 설정됩니다.

강의 추가:

java

courses.add(tempCourse);

    courses.add(tempCourse);: tempCourse 객체를 courses 리스트에 추가합니다.

강의에 강사 설정:

java

    tempCourse.setInstructor(this);

        tempCourse.setInstructor(this);: tempCourse 객체의 instructor 필드를 현재 Instructor 객체(this)로 설정합니다.
        이를 통해 tempCourse 객체가 이 Instructor 객체와 연관되도록 설정됩니다.

전체적인 흐름

    메서드가 호출되면 tempCourse라는 Course 객체를 매개변수로 받습니다.
    courses 리스트가 초기화되지 않았다면(null이라면), 새 ArrayList로 초기화합니다.
    tempCourse 객체를 courses 리스트에 추가합니다.
    tempCourse 객체의 instructor 필드를 현재 Instructor 객체로 설정하여, tempCourse 객체가 이 Instructor 객체와 연관되도록 합니다.
 */
