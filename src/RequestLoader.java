import com.sun.net.httpserver.Request;

import java.util.*;

/**
 * Write a description of class RequestLoader here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class RequestLoader extends DataLoader
{   
    HashMap<Integer,Student> mapStudents = new HashMap<Integer,Student>();
    HashMap<String,Course> mapCourses = new HashMap<String,Course>();

    /**
     * Constructor creates hashMaps for students and courses.  
     * mapStudents: idNum is the key.
     * mapCourses: courseKey (string) is the key, 
     * <p>
     * calls the load(file) method in abstract parent class.
     * 
     * @param file: the path to the file.
     * @param students: List of students successfully loaded from file
     * @param courses:  List of courses successfully loaded from file
     */
    public RequestLoader(String file, List<Student> students, List<Course> courses){
        for(Student s : students){
            mapStudents.put(s.idNum, s);
        }

        for(Course c : courses){
            mapCourses.put(c.getKey(),c);
        }

        load(file);
    }

    /**
     * Parse a single line of CSV in the form:
     * studentId, Course 1, course 2, course 3, course 4, course 5, course 6, course 7
     * 999248624,CMPU-145-51,CMPU-145-52,EDUC-361-51,ECON-235-51,PHED-105-51,ECON-235-51,COGS-311-51
     * <p>
     * Method should 
     * 1) extract the studentId and find the associated Student in mapStudents.
     * 2) extract the courseKey and find the associated Course in mapCourses
     * 3) add the course to the student requests.
     * 
     * @param data: a single line from the csv file.
     * parse
     */
    public void parseAndLoadLine(String data) {
        int studentID = 0;
        String course1key = null;
        String course2key = null;
        String course3key = null;
        String course4key = null;
        String course5key = null;
        String course6key = null;
        String course7key = null;

        String[] studentData = data.split(",");
        if (studentData.length == 8) {
            studentID = Integer.parseInt(studentData[0]);

        }
        Student s = mapStudents.get(studentID);

        for(int i = 1; i <studentData.length; i++) {
            Course c = mapCourses.get(studentData[i]);
            s.requests.add(c);
        }
        if (s != null) {
            mapStudents.put(studentID, s);
        }



    }
    /**
     * Method takes finished hashmap of students with all of their requests and turns
     * it back into a List, sorts it, and returns it.
     * 
     * @return students: list of all students and their requests sorted by drawNumber and Class
     * (as specified in the Student compareTo method task).
     */
    public List<Student> mapStudentRequests(){
        List<Student> students = new ArrayList<>();
        students.addAll(mapStudents.values());
        Collections.sort(students);
        return students;
    }
}
