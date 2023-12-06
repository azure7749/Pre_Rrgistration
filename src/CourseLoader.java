import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

/**
 * Reads in a csv file from askbanner of courses and turns them into a List of Course objects. *
 * @author (your name)
 * @version (a version number or a date)
 */
public class CourseLoader extends DataLoader
{
    private List<Course> courses = new ArrayList<Course>();

    /**
     * Constructor calls the load(file) method in abstract parent class.
     * 
     * @param file: the path to the file.
     */
    CourseLoader(String file){
        load(file);
    }

    /**
     * Parse a single line of CSV in the form:
     * dept, courseNum, section, name, credits, maxEnrol, days, startTime, EndTime, Duration, String time, room, professor
     * CMPU,145,51,Foundations/Computer Science,1,24,TRF,720,795,75,1200PM-0115PM,SP 309,Gomerschdat Anna
     * <p>
     * Method should create a new Course Object and add it to 
     * the students instance variable.
     * 
     * @param data: a single line from the csv file.
     */
    public void parseAndLoadLine(String data) {
        String dept = null;
        int courseNum = 0;
        int section = 0;
        String title = null;
        double credits = 0;
        int maxEnrollment = 0;
        String daysOfTheWeek = null;
        int startTime = 0;
        int endTime = 0;
        int duration = 0;
        String timeString = null;
        String loc = null;
        String instructor = null;

        String[] studentData = data.split(",");
        if (studentData.length == 13) {
            dept = studentData[0];
            courseNum = Integer.parseInt(studentData[1]);
            section = Integer.parseInt(studentData[2]);
            title = studentData[3];
            credits = Double.parseDouble(studentData[4]);
            maxEnrollment = Integer.parseInt(studentData[5]);
            daysOfTheWeek = studentData[6];
            startTime = Integer.parseInt(studentData[7]);
            endTime =  Integer.parseInt(studentData[8]);
            duration =  Integer.parseInt(studentData[9]);
            timeString = studentData[10];
            loc = studentData[11];
            instructor = studentData[12];

        }
        Course course = new Course(dept,courseNum,section,title,credits,maxEnrollment,daysOfTheWeek,startTime,endTime,duration,timeString,loc,instructor);
        if(course.dept != null) {
            courses.add(course);
        }
    }

    /**
     * Easy getter method to return the completed student roster.
     * @return students: the roster in the form of a List<Course>
     */
    public List<Course> getCourses(){
        Collections.sort(courses);
        return courses;
    }
}
