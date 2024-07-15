package jpa.mapping_bidirectonal.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jpa.mapping_bidirectonal.entity.Course;
import jpa.mapping_bidirectonal.entity.Instructor;
import jpa.mapping_bidirectonal.entity.InstructorDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AppDAOImpl implements AppDAO{

    // define field for entity manager
    private EntityManager entityManager;

    // inject entity manager using constructor
    @Autowired
    public AppDAOImpl(EntityManager theEntityManager){
        this.entityManager = theEntityManager;
    }

    @Override
    @Transactional
    public void save(Instructor theInstructor) {

        entityManager.persist(theInstructor);

    }

    @Override
    public Instructor findInstructorById(Integer theId) {

       return entityManager.find(Instructor.class, theId);

    }

    @Override
    @Transactional
    public void deleteInstructorById(Integer theId) {

        // retrieve the object
        Instructor theInstructor = entityManager.find(Instructor.class, theId);

        // get the courses
        List<Course> courseList = theInstructor.getCourses();

        // break association of all courses for the instructor => Remove the instructor from the courses
        for (Course tempCourse : courseList){
            tempCourse.setInstructor(null);
        }

        // delete the object
        entityManager.remove(theInstructor);
    }

    @Override
    public InstructorDetail findInstructorDetailById(int theId) {

        return entityManager.find(InstructorDetail.class, theId);

    }

    @Override
    @Transactional
    public void deleteInstructorDetailById(Integer theId) {

        // retrieve instructor detail and transform the data to entity type
        InstructorDetail theDetail = entityManager.find(InstructorDetail.class, theId);

        // remove the associated object reference
        // break bi-directional link

        theDetail.getInstructor().setInstructorDetail(null);

        // remove the Entity
        entityManager.remove(theDetail);

    }

    @Override
    public List<Course> findCoursesByInstructorId(Integer theId) {
        // create query
        TypedQuery<Course> query = entityManager.createQuery(
                "FROM Course WHERE instructor.id = :data", Course.class);

        query.setParameter("data", theId);

        // execute query
        List<Course> courseList = query.getResultList();

        return courseList;

    }

    /* JOIN FETCH i.courses:

    Instructor 엔티티와 그와 연관된 Course 엔티티들을 함께 조회합니다.
    JOIN FETCH는 Eager Fetching을 강제하여 Instructor와 연관된 Course 엔티티들을 한 번의 쿼리로 가져옵니다. 이를 통해 N+1 문제를 피할 수 있습니다. */

    @Override
    public Instructor findInstructorByIdJoinFetch(int theId) {

       TypedQuery<Instructor> query = entityManager.createQuery(
                                                    "SELECT i FROM Instructor i"
                                                       + " JOIN FETCH i.courses"
                                                       + " JOIN FETCH i.instructorDetail"
                                                       + " WHERE i.id =:data", Instructor.class);

       /* " JOIN FETCH i.courses "
        join
            course c1_0
                on i1_0.id=c1_0.instructor_id
       */

       query.setParameter("data", theId);

        // execute query
        Instructor theInstructor = query.getSingleResult();

        return theInstructor;

        /* getSingleResult()는 쿼리 결과가 하나의 Instructor 엔티티임을 의미하며,
        이 Instructor 엔티티에 여러 개의 연관된 Course 엔티티들이 포함되어 있습니다.
        쿼리는 Instructor 엔티티를 단일 결과로 반환하지만, JOIN FETCH를 통해 연관된 Course 엔티티들을 함께 로드합니다.
        이는 데이터베이스에서 단일 Instructor 를 조회하면서 그와 연관된 모든 Course 엔티티들을 한 번에 가져오는 방식으로 동작합니다.
         */
    }

    @Override
    @Transactional
    public void update(Instructor tempInstructor) {

        entityManager.merge(tempInstructor);

    }

    @Override
    @Transactional
    public void updateCourse(Course theCourse) {

        entityManager.merge(theCourse);

    }

    @Override
    public Course findCourseById(int theId) {

       return entityManager.find(Course.class, theId);

    }

    @Override
    @Transactional
    public void deleteCourseById(int theId) {

        Course deletingCourse = entityManager.find(Course.class, theId);

        entityManager.remove(deletingCourse);

    }

}
