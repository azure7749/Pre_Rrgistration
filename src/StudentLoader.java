import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Loads in student objects from a CSV
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class StudentLoader extends DataLoader
{
    private List<Student> students = new ArrayList<>();
    /**
     * Constructor calls the load(file) method in abstract parent class.
     * 
     * @param file: the path to the file.
     */
    public StudentLoader(String file){
        load(file);
    }
    
    /**
     * Parse a single line of CSV in the form:
     * Name, idNum, gradYear, drawNumber
     * Hector Tran,999248624,2023,1
     * <p>
     * Method should create a new Student Object and add it to 
     * the students instance variable.
     * 
     * @param data: a single line from the csv file.
     * reference: "https://stackoverflow.com/questions/10631715/how-to-split-a-comma-separated-string"
     */
    public void parseAndLoadLine(String data) {
        String Name = null;
        int idNum = 0;
        int gradYear = 0;
        int drawNumber = 0;

        String[] studentData = data.split(",");
        if (studentData.length == 4) {
            Name = studentData[0];
            idNum = Integer.parseInt(studentData[1]);
            gradYear = Integer.parseInt(studentData[2]);
            drawNumber = Integer.parseInt(studentData[3]);
        }
        Student student = new Student(Name,idNum,gradYear,drawNumber);
        if(student.name != null) {
            students.add(student);
        }
    }

    /**
     * Easy getter method to return the completed student roster.
     * @return students: the roster in the form of a List<Student>
     */
    public List<Student> getStudents(){
        Collections.sort(students);
        return students;
    }
    
}
