import com.sun.net.httpserver.Request;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegistrarTest {

    @Test
    public void StudentLoaderTest(){

        String data = "./data/shortRoster.csv";
        StudentLoader studentLoader = new StudentLoader(data);
        studentLoader.parseAndLoadLine(data);
        assertEquals(studentLoader.getStudents().get(0).name,"Hector Tran");
        assertEquals(studentLoader.getStudents().get(3).idNum,999249374);
        assertThrows(IndexOutOfBoundsException.class,()-> studentLoader.getStudents().get(-1));

    }
    @Test
    public void CourseLoaderTest(){

        String data = "./data/course_shortRequests.csv";
        CourseLoader courseLoader = new CourseLoader(data);
        courseLoader.parseAndLoadLine(data);
        assertEquals(courseLoader.getCourses().get(0).title,"Visual Art and Storytelling");
        assertEquals(courseLoader.getCourses().get(9).timeString,"1200PM-1250PM");
        assertThrows(IndexOutOfBoundsException.class,()-> courseLoader.getCourses().get(-1));

    }

    @Test
    public void RequestLoaderTest(){
        String studentData = "./data/shortRoster.csv";
        String courseData = "./data/course_shortRequests.csv";
        String requestData = "./data/shortRequests.csv";
        StudentLoader studentLoader = new StudentLoader(studentData);
        CourseLoader courseLoader = new CourseLoader(courseData);
        studentLoader.parseAndLoadLine(studentData);
        courseLoader.parseAndLoadLine(courseData);

        List<Student> students = studentLoader.getStudents();
        List<Course> courses = courseLoader.getCourses();

        RequestLoader requestLoader = new RequestLoader(requestData,students,courses);
        requestLoader.parseAndLoadLine(requestData);

        assertEquals(requestLoader.mapStudentRequests().get(2).requests.get(3).title,"Resource Extraction");
    }

    @Test
    public void StudentCompareToTest(){

        String studentData = "./data/shortRoster.csv";
        String courseData = "./data/course_shortRequests.csv";
        String requestData = "./data/shortRequests.csv";
        StudentLoader studentLoader = new StudentLoader(studentData);
        CourseLoader courseLoader = new CourseLoader(courseData);
        studentLoader.parseAndLoadLine(studentData);
        courseLoader.parseAndLoadLine(courseData);
        List<Student> students = studentLoader.getStudents();
        List<Course> courses = courseLoader.getCourses();

        RequestLoader requestLoader = new RequestLoader(requestData,students,courses);
        requestLoader.parseAndLoadLine(requestData);

        List<Student> unsorted = requestLoader.mapStudentRequests();
        Collections.sort(unsorted);
        List<Student> sorted = unsorted;

        assertEquals("Hector Tran", sorted.get(0).name);
        assertEquals("Mark Peters", sorted.get(sorted.size() - 1).name);

    }
    @Test
    public void CourseCompareToTest() {
        String data = "./data/course_shortRequests.csv";
        CourseLoader courseLoader = new CourseLoader(data);
        courseLoader.parseAndLoadLine(data);

        Course course1 = courseLoader.getCourses().get(8);
        // ART,106,56,Intro to Hist of Art/Architect,1,15,MWF,720,770,50,1200PM-1250PM,T 102,Culp Caroline
        Course course2 = courseLoader.getCourses().get(9);
        // ART,106,58,Intro to Hist of Art/Architect,1,15,MWF,720,770,50,1200PM-1250PM,T 102,Lu Haohao
        Course course3 = courseLoader.getCourses().get(8);

        assertEquals(course1.compareTo(course2),-1);
        assertEquals(course2.compareTo(course1),1);
        assertEquals(course1.compareTo(course3),0);
    }
    @Test
    public void CourseEqualsTest() {
        String data = "./data/course_shortRequests.csv";
        CourseLoader courseLoader = new CourseLoader(data);
        courseLoader.parseAndLoadLine(data);
        List<Course> courses = new ArrayList<Course>();

        courses.add(0,courseLoader.getCourses().get(1));
        courses.add(1,courseLoader.getCourses().get(10));
        courses.add(2,courseLoader.getCourses().get(1));

        assertTrue(courses.get(0).equals(courses.get(2)));
        assertFalse(courses.get(0).equals(courses.get(1)));

    }
    @Test
    public void CourseConflictsWithTest() {
        String data = "./data/course_shortRequests.csv";
        CourseLoader courseLoader = new CourseLoader(data);
        courseLoader.parseAndLoadLine(data);

        Course course1 = courseLoader.getCourses().get(8);
        //ART,106,56,Intro to Hist of Art/Architect,1,15,MWF,720,770,50,1200PM-1250PM,T 102,Culp Caroline
        Course course2 = courseLoader.getCourses().get(9);
        //ART,106,58,Intro to Hist of Art/Architect,1,15,MWF,720,770,50,1200PM-1250PM,T 102,Lu Haohao
        Course course3 = courseLoader.getCourses().get(10);
        //ART,108,51,Color,1,14,TR,585,705,120,0945AM-1145AM,EH STD2,Rajendran Padma

        assertTrue(course1.conflictsWith(course2));
        assertFalse(course1.conflictsWith(course3));

    }




}
