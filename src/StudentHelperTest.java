import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class StudentHelperTest {

    @Test
    public void isRegisteredForTest() {
        Student student = new Student("Test", 999000000, 2026, 1);

        Course c1 = new Course("MATH", 221, 52, "", 1, 50, "MWF", 600, 650, 50, "1000AM-1100PM", "", "");
        Course c2 = new Course("MATH", 221, 51, "", 1, 50, "MWF", 660, 710, 50, "1100AM-1150PM", "", "");
        Course c3 = new Course("CMPU", 102, 51, "", 1, 17, "TRF", 630, 705, 75, "1030AM-1145AM", "", "");

        student.schedule.add(c1);


        assertTrue(student.isRegisteredFor(c1));
        assertTrue(student.isRegisteredFor(c2));
        assertFalse(student.isRegisteredFor(c3));


    }
    @Test
    public void totalRegisteredCreditsTest() {
        Student student = new Student("Test", 999000000, 2026, 1);

        Course c1 = new Course("CMPU", 102, 51, "", 1, 17, "TRF", 630, 705, 75, "1030AM-1145AM", "", "");
        Course c2 = new Course("MATH", 221, 52, "", 1, 50, "MWF", 600, 650, 50, "1000AM-1100PM", "", "");
        Course c3 = new Course("MATH", 221, 51, "", 1, 50, "MWF", 660, 710, 50, "1100AM-1150PM", "", "");
        Course c4 = new Course("CMPU", 241, 51, "", 1, 17, "MW", 630, 705, 75, "1030AM-1145AM", "", "");

        student.schedule.add(c1);
        student.schedule.add(c2);
        student.schedule.add(c3);
        student.schedule.add(c4);

        assertEquals(4.0, student.totalRegisteredCredits());


    }
    @Test
    public void hasAConflictTest() {
        Student student = new Student("Test", 999000000, 2026, 1);

        Course c1 = new Course("CMPU", 102, 51, "", 1, 17, "TRF", 630, 705, 75, "1030AM-1145AM", "", "");
        Course c2 = new Course("MATH", 221, 52, "", 1, 50, "MWF", 600, 650, 50, "1000AM-1100PM", "", "");
        Course c3 = new Course("MATH", 221, 51, "", 1, 50, "MWF", 660, 710, 50, "1100AM-1150PM", "", "");
        Course c4 = new Course("CMPU", 241, 51, "", 1, 17, "MW", 705, 780, 75, "1145AM-0100PM", "", "");

        student.schedule.add(c1);
        student.schedule.add(c2);
        student.schedule.add(c3);


        assertTrue(student.hasAConflict(c1));
        assertTrue(student.hasAConflict(c2));
        assertTrue(student.hasAConflict(c3));
        assertFalse(student.hasAConflict(c4));


    }
}
























