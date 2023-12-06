/**
 * A course object.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Course implements Comparable<Course> {
    String dept;
    int courseNum;
    int section;
    String title;
    double credits;
    int maxEnrollment;
    String daysOfTheWeek;
    int startTime;
    int endTime;
    int duration;
    String timeString;
    String loc;
    String instructor;

    /**
     * Constructor takes in all values from the CSV.
     */
    public Course(String dept, int courseNum, int section, String title, double credits, int maxEnrollment, String daysOfTheWeek, int startTime,int endTime,int duration,String timeString, String loc, String instructor){
        //AFRS,100,51,Intro to Africana Studies,1,20,TR,810,885,75,0130PM-0245PM,BH 305,"Harriford, Diane"
        this.dept = dept;
        this.courseNum = courseNum;
        this.section = section;
        this.title = title;
        this.credits = credits;
        this.maxEnrollment = maxEnrollment;
        this.daysOfTheWeek = daysOfTheWeek;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.timeString = timeString;
        this.loc = loc;
        this.instructor = instructor;
    }

    /**
     * Key is a string representation of department-courseNumber-section.
     * Example: CMPU-102-51
     */
    public String getKey(){
        return dept + "-" + courseNum + "-" + section;
    }


    /**
     * Returns true if key is the same.  (Department, Course Number, and Section).
     * 
     */

    public boolean equals(Object o){
       if (o instanceof Course) {
           return this.getKey().equals(((Course) o).getKey());
       } return false;
    }

    /**
     * String representation as it might appear on askBanner.
     */
    public String toString(){
        return getKey() + " " + title + "\t" + credits + "\t" + daysOfTheWeek + " " + timeString;
    }

    /**
     * Should sort classes by department, then course number, then section. (just like
     * askBanner)
     */
    public int compareTo(Course o) {
        if (this.dept.compareTo(o.dept) != 0) {
            return this.dept.compareTo(o.dept);
        } else {
            if (this.courseNum > o.courseNum) {
                return 1;
            } else if (this.courseNum < o.courseNum) {
                return -1;
            } else {
                if (this.section > o.section) {
                    return 1;
                } else if (this.section < o.section) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }

    }

    /**
     * a method that checks times and days to determine whether they overlap.
     */
    public boolean conflictsWith(Course c){
        for(char day1: daysOfTheWeek.toCharArray()){
            for(char day2: c.daysOfTheWeek.toCharArray()){
                if (day1 == day2){
                    if(c.startTime <= startTime && startTime <= c.endTime){
                        return true;
                    }
                    if(startTime <= c.startTime && c.startTime <= endTime){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
