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
    add 메소드는 두 개의 객체, 예를 들어 Instructor(강사)와 Course(강의) 사이의 관계를 더 쉽게 관리하기 위해 만들어졌습니다.

    왜 이 메소드를 추가했는가?

    자동으로 연결:
    이 메소드는 강사와 강의를 자동으로 연결해 줍니다.
    예를 들어, 한 강사가 새로운 강의를 추가할 때 이 메소드를 사용하면, 그 강의가 자동으로 강사에 속하게 됩니다.
    instructor.add(course) 이렇게 호출하면 됩니다.

    편리함:
    강의 목록이 비어 있을 때 자동으로 목록을 만들어줍니다.
    강의 리스트가 비어 있는 경우에도 강의를 추가할 수 있게 해줍니다.
    따라서 수동으로 리스트를 만들 필요가 없습니다.

    일관성 유지:
    강사와 강의의 연결이 항상 정확하게 맞아떨어지게 해줍니다.
    강사에 강의를 추가하면 그 강의에도 자동으로 이 강사가 설정됩니다.
    이렇게 하면 실수를 줄일 수 있습니다.

    예를 들어:

    강사가 있고, 강의가 있을 때:
    Instructor instructor = new Instructor();
    Course course = new Course();

    강사가 강의를 추가할 때:
    instructor.add(course);

    이렇게 하면, instructor의 강의 목록에 course가 추가되고, 동시에 course의 강사도 instructor로 설정됩니다.
    이 메소드는 강사와 강의 사이의 관계를 쉽게 만들고 관리할 수 있게 해주는 도우미 역할을 합니다.
*/
