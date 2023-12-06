import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class AlgorithmTest {
    @Test
    public void studentOrderTest(){
        HashMap<Course,Integer> enrollment = new HashMap<>();

        StudentLoader sl = new StudentLoader("data/fullRoster.csv");
        List<Student> students = sl.getStudents();
        CourseLoader cl = new CourseLoader("data/course_fullRequests.csv");
        List<Course> courses = cl.getCourses();

        RequestLoader rl = new RequestLoader("data/fullRequests.csv", students, courses);
        students = rl.mapStudentRequests();

        for(Course c : courses){
            enrollment.put(c,0);
        }
        Algorithm a = new Algorithm(students,enrollment);
        a.run();


        for(int i = 0; i < a.mapStudents.size(); i ++) {
            assertEquals(a.mapStudents.get(i), students.get(i));
        }

    }
    @Test
    public void studentTotalCreditUnderLimitTest(){
        HashMap<Course,Integer> enrollment = new HashMap<>();

        StudentLoader sl = new StudentLoader("data/fullRoster.csv");
        List<Student> students = sl.getStudents();
        CourseLoader cl = new CourseLoader("data/course_fullRequests.csv");
        List<Course> courses = cl.getCourses();

        RequestLoader rl = new RequestLoader("data/fullRequests.csv", students, courses);
        students = rl.mapStudentRequests();

        for(Course c : courses){
            enrollment.put(c,0);
        }
        Algorithm a = new Algorithm(students,enrollment);
        a.run();


        for(int i = 0; i < a.mapStudents.size(); i ++) {
            assertTrue(a.mapStudents.get(i).totalRegisteredCredits() <= 4.5);
        }

    }
    @Test
    public void noDuplicatedCourseTest(){
        HashMap<Course,Integer> enrollment = new HashMap<>();

        StudentLoader sl = new StudentLoader("data/fullRoster.csv");
        List<Student> students = sl.getStudents();
        CourseLoader cl = new CourseLoader("data/course_fullRequests.csv");
        List<Course> courses = cl.getCourses();

        RequestLoader rl = new RequestLoader("data/fullRequests.csv", students, courses);
        students = rl.mapStudentRequests();

        for(Course c : courses){
            enrollment.put(c,0);
        }
        Algorithm a = new Algorithm(students,enrollment);
        a.run();

        for(Student s: a.mapStudents){
            for(int i = 0; i < s.schedule.size(); i ++) {
                for (int j = i + 1; j < s.schedule.size(); j++) {
                    assertFalse(s.schedule.get(i).conflictsWith(s.schedule.get(j)));
                }
            }
        }

    }
    @Test
    public void classNotOverEnrolledTest(){
        HashMap<Course,Integer> enrollment = new HashMap<>();

        StudentLoader sl = new StudentLoader("data/fullRoster.csv");
        List<Student> students = sl.getStudents();
        CourseLoader cl = new CourseLoader("data/course_fullRequests.csv");
        List<Course> courses = cl.getCourses();

        RequestLoader rl = new RequestLoader("data/fullRequests.csv", students, courses);
        students = rl.mapStudentRequests();

        for(Course c : courses){
            enrollment.put(c,0);
        }
        Algorithm a = new Algorithm(students,enrollment);
        a.run();

        for (Course c : a.enrollment.keySet()) {
            Integer enrollmentCount = a.enrollment.get(c);
            assertNotNull(enrollmentCount);
            assertTrue(enrollmentCount <= c.maxEnrollment);
//            System.out.println(c);
//            System.out.println("enrollment: " + enrollmentCount);
//            System.out.println("max: " + c.maxEnrollment);
        }


    }

    @Test
    public void scheduleNotEmptyTest(){
        HashMap<Course,Integer> enrollment = new HashMap<>();

        StudentLoader sl = new StudentLoader("data/fullRoster.csv");
        List<Student> students = sl.getStudents();
        CourseLoader cl = new CourseLoader("data/course_fullRequests.csv");
        List<Course> courses = cl.getCourses();

        RequestLoader rl = new RequestLoader("data/fullRequests.csv", students, courses);
        students = rl.mapStudentRequests();

        for(Course c : courses){
            enrollment.put(c,0);
        }
        Algorithm a = new Algorithm(students,enrollment);
        a.run();

        for (Student s : a.mapStudents) {
            if(s.schedule.isEmpty()) {
                System.out.println(s);
            }
            assertFalse(s.schedule.isEmpty());
        }


    }















    }
