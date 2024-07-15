package jpa.mapping_bidirectonal.dao;

import jpa.mapping_bidirectonal.dao.AppDAOImpl;
import jpa.mapping_bidirectonal.entity.Course;
import jpa.mapping_bidirectonal.entity.Instructor;
import jpa.mapping_bidirectonal.entity.InstructorDetail;

import java.util.List;

public interface AppDAO {

    void save(Instructor theInstructor);

    Instructor findInstructorById(Integer theId);

    void deleteInstructorById(Integer theId);

    InstructorDetail findInstructorDetailById(int theId);

    void deleteInstructorDetailById(Integer theId);

    List<Course> findCoursesByInstructorId(Integer theId);

    Instructor findInstructorByIdJoinFetch(int theId);

    void update(Instructor tempInstructor);

    void updateCourse(Course theCourse);

    Course findCourseById(int theId);

    void deleteCourseById(int theId);


}
