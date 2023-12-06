import java.util.*;

/**
 * Kicks off our program.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Registrar {
    HashMap<Course,Integer> enrollment = new HashMap<>();

    /**
     * Constructor loads all files, and runs the algorithm for preregistration and 
     * prints the results.
     */
    public Registrar(){
        StudentLoader sl = new StudentLoader("data/fullRoster.csv");
        List<Student> students = sl.getStudents();
        
        CourseLoader cl = new CourseLoader("data/course_fullRequests.csv");
        List<Course> courses = cl.getCourses();
                
        RequestLoader rl = new RequestLoader("data/fullRequests.csv", students, courses);
        students = rl.mapStudentRequests();
        
        for(Course c : courses){
            enrollment.put(c,0); //The number of students currently enrolled in each Course.
        }

        //The fully loaded students, associated with all of their requests, and the list of courses
        //initialized to 0 students enrolled.
        Algorithm a = new Algorithm(students,enrollment);
        a.run();
        a.printEnrollment();
    }

}
