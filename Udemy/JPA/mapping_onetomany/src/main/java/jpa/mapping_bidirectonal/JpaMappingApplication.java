package jpa.mapping_bidirectonal;

import jpa.mapping_bidirectonal.dao.AppDAO;
import jpa.mapping_bidirectonal.entity.Course;
import jpa.mapping_bidirectonal.entity.Instructor;
import jpa.mapping_bidirectonal.entity.InstructorDetail;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class JpaMappingApplication {

    public static void main(String[] args) {
        SpringApplication.run(JpaMappingApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AppDAO appDAO){

        return runner -> {
//            createInstructor(appDAO);
//            findById(appDAO);
//            deleteById(appDAO);
//            findInstructorDetail(appDAO);
//            deleteInstructorDetail(appDAO);

//            createInstructorWithCourses(appDAO);

//            createInstructorWithCourses2(appDAO);

//            findInstructorWithCourses(appDAO);

            findCoursesForInstructor(appDAO);

//            findInstructorWithCoursesJoinFetch(appDAO);

//            updateInstructor(appDAO);

//            updateCourse(appDAO);

//            deleteInstructor(appDAO);

//            deleteCourse(appDAO);
        };
    }

    private void deleteCourse(AppDAO appDAO) {
        int deletingCourseId = 11;

        appDAO.deleteCourseById(deletingCourseId);

    }

    private void deleteInstructor(AppDAO appDAO) {

        int theId = 1;

        appDAO.deleteInstructorById(theId);
    }

    private void updateCourse(AppDAO appDAO) {

        int courseId = 10;

        System.out.println("Finding the course id : " + courseId);
        Course tempCourse = appDAO.findCourseById(courseId);

        /*
        * Hibernate:
    select
        c1_0.id,
        i1_0.id,
        i1_0.email,
        i1_0.first_name,
        id1_0.id,
        id1_0.hobby,
        id1_0.youtube_channel,
        i1_0.last_name,
        c1_0.title
    from
        course c1_0
    left join
        instructor i1_0
            on i1_0.id=c1_0.instructor_id
    left join
        instructor_detail id1_0
            on id1_0.id=i1_0.instructor_detail_id
    where
        c1_0.id=? */

        System.out.println("Updating the course id : " + courseId);
        tempCourse.setTitle("Explore the world!");

        appDAO.updateCourse(tempCourse);

    }

    private void updateInstructor(AppDAO appDAO) {

        int theId = 1;

        System.out.println("theId : " + theId);

        Instructor updatingInstructor = appDAO.findInstructorById(theId);
        System.out.println("appDAO.findInstructorById(theId)" + appDAO.findInstructorById(theId));

        // update the instructor
        System.out.println("Updating instructor id : " + theId);
        updatingInstructor.setLastName("TESTER");


        appDAO.update(updatingInstructor);
        System.out.println("updatingInstructor : " + updatingInstructor);

        System.out.println("DONE ! ");
    }

    private void findInstructorWithCoursesJoinFetch(AppDAO appDAO) {

        int instructorId = 1;

        // find the instructor
        System.out.println("Finding instructor id : " + instructorId );

        Instructor theInstructor = appDAO.findInstructorByIdJoinFetch(instructorId);

        System.out.println("theInstructor : " + theInstructor);
        System.out.println("theInstructor.getCourses() : " + theInstructor.getCourses());

        System.out.println("DONE!");

    }

    private void findCoursesForInstructor(AppDAO appDAO) {

        int instructorId = 8;
        System.out.println("Finding instructor id : " + instructorId);

        Instructor tempInstructor = appDAO.findInstructorById(instructorId);

        System.out.println("theInstructor : " + tempInstructor);

        // find courses for instructor
        System.out.println("Finding courses for instructor id : " +instructorId);

        List<Course> courses = appDAO.findCoursesByInstructorId(instructorId);


        // associate the objects
        tempInstructor.setCourses(courses);

        System.out.println("the associated courses : " + tempInstructor.getCourses());
        System.out.println("DONE");


    }

    private void findInstructorWithCourses(AppDAO appDAO) {

        int theId = 1;
        System.out.println("Finding instructor id : " + theId);

        Instructor tempInstructor = appDAO.findInstructorById(theId);

        System.out.println("tempInstructor : " + tempInstructor);
        System.out.println("the Associated Courses : " + tempInstructor.getCourses());

        System.out.println("Done!");
    }

    private void createInstructorWithCourses2(AppDAO appDAO) {

        // create the instructor
        Instructor tempInstructor = new Instructor("Jin", "Lee","jina@luv.com");

        // create the instructor detail
        InstructorDetail tempInstructorDetail
                = new InstructorDetail("http://jina.com/youtude","Glory");


        // associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // create some courses
        Course tempCourse1 = new Course("Programming - The Ultimate Version");
        Course tempCourse2 = new Course("Dance - my Dance");

        // add courses to instructor
        tempInstructor.add(tempCourse1);
        tempInstructor.add(tempCourse2);

        // save the instructor
        // NOTE : this will ALSO save the courses
        // because of CascadeType.Persist

        System.out.println("Saving the instructor : " + tempInstructor);
        System.out.println("THe courses : " + tempInstructor.getCourses());
        appDAO.save(tempInstructor);

        System.out.println("DONE!!");

    }


    private void createInstructorWithCourses(AppDAO appDAO) {

        // 새로운 Instructor 객체 생성
        Instructor tempInstructor = new Instructor("Susan", "Public","susan@luv.com");

        // 새로운 InstructorDetail 객체 생성 및 설정
        InstructorDetail tempInstructorDetail
                = new InstructorDetail("http://susan.com/youtude","Pubg");


        // associate the objects
        tempInstructor.setInstructorDetail(tempInstructorDetail);

        // 새로운 Course 객체 생성
        Course tempCourse1 = new Course("Air Guitar - The Ultimate Guid");
        Course tempCourse2 = new Course("Dance - The Master's Dance");

        // add courses to instructor => Course 객체를 Instructor 객체에 추가
        tempInstructor.add(tempCourse1);
        tempInstructor.add(tempCourse2);

        // save the instructor
        // NOTE : this will ALSO save the courses
        // because of CascadeType.Persist

        System.out.println("Saving the instructor : " + tempInstructor);
        System.out.println("THe courses : " + tempInstructor.getCourses());
        appDAO.save(tempInstructor);

        System.out.println("DONE!!");

    }

    private void deleteInstructorDetail(AppDAO appDAO) {

        int theId = 8;
        System.out.println("Deleting instructor detail id : " + theId);

        appDAO.deleteInstructorDetailById(theId);

        System.out.println("done!");
    }

    private void findInstructorDetail(AppDAO appDAO) {

        // get the instructor detail object
        int theId = 5;
        InstructorDetail detail = appDAO.findInstructorDetailById(theId);

        // print the instructor detail
        System.out.println("detail : " + detail);

        // print the associated instructor
        System.out.println("the associated instructor : " + detail.getInstructor());

        System.out.println("DONE ! ");

    }

    private void deleteById(AppDAO appDAO) {

        int theId = 1;
        System.out.println("Deleting object id : " + theId);

        appDAO.deleteInstructorById(theId);
        System.out.println("Done");
    }

    private void findById(AppDAO appDAO) {

        int theId = 2;
        System.out.println("Finding instructor id : " + theId);

        Instructor theInstructor = appDAO.findInstructorById(theId);

        System.out.println("theInstructor : " + theInstructor);
        System.out.println(" the associate instructorDetail only : " + theInstructor.getInstructorDetail());
    }

    private void createInstructor(AppDAO appDAO) {

//        // 새로운 instructor 객체 생성
//        Instructor newInstructor = new Instructor("Chad", "Darby","darby@luv.com");
//
//        // 새로운 InstructorDetail 객체 생성 및 설정
//        InstructorDetail newInstructorDetail
//                = new InstructorDetail("http://blah.com/youtude","luv 2 code!!");

        // create the instructor
        Instructor newInstructor = new Instructor("Madhu", "Patel","madhu@luv.com");

        // create the instructor detail
        InstructorDetail newInstructorDetail
                = new InstructorDetail("http://blahblah.com/youtude","guitar");


        // associate the objects
        newInstructor.setInstructorDetail(newInstructorDetail);

        // save the instructor
        // NOTE : this will ALSO save the details object
        // because of CascadeType.ALL
        System.out.println("Saving instructor: " + newInstructor);
        appDAO.save(newInstructor);

    }

}
