import java.util.*;

/**
 * This will hold all the logic for our preregistration algorithm.  To be done in Part 2.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Algorithm {
    List<Student> mapStudents;
    HashMap<Course, Integer> enrollment;


    public Algorithm(List<Student> mapStudents, HashMap<Course, Integer> enrollment) {
        this.mapStudents = mapStudents;
        this.enrollment = enrollment;
    }


    public void run() {
        int round = 1;
        HashMap<Integer, PriorityQueue<Student>> priorityQueueHashMap = new HashMap<>();
        HashMap<Integer, Stack<Student>> stackHashMap = new HashMap<>();
        Collections.sort(mapStudents);

        while (round < 8) {
            if (round % 2 != 0) {
                if (round == 1) {
                    for (Student student : mapStudents) {
                        int gradYear = student.gradYear;
                        priorityQueueHashMap.computeIfAbsent(gradYear, k -> new PriorityQueue<>()).offer(student);
                    }
                } else {
                    for (Integer gradYear : stackHashMap.keySet()) {
                        priorityQueueHashMap.put(gradYear, new PriorityQueue<>());
                        for (Student s : stackHashMap.get(gradYear)) {
                            priorityQueueHashMap.get(gradYear).offer(s);
                        }
                        stackHashMap.get(gradYear).clear();
                    }
                }

                for (Integer gradYear : priorityQueueHashMap.keySet()) {
                    PriorityQueue<Student> studentsOfAClass = priorityQueueHashMap.get(gradYear);
                    for (Student student : studentsOfAClass) {
                        processRequest(student);
                        stackHashMap.computeIfAbsent(gradYear, k -> new Stack<>()).push(student);
                    }
                }
            } else {
                for (Integer gradYear : priorityQueueHashMap.keySet()) {
                    stackHashMap.put(gradYear, new Stack<>());
                    for (Student s : priorityQueueHashMap.get(gradYear)) {
                        stackHashMap.get(gradYear).push(s);
                    }
                    priorityQueueHashMap.get(gradYear).clear();
                }

                for (Integer gradYear : stackHashMap.keySet()) {
                    Stack<Student> students = stackHashMap.get(gradYear);
                    for (Student student : students) {
                        processRequest(student);
                        priorityQueueHashMap.computeIfAbsent(gradYear, k -> new PriorityQueue<>()).offer(student);
                    }
                }
            }
            round++;
        }
    }
    public void processRequest(Student s) {
        Iterator<Course> requestIterator = s.requests.iterator();
        while (requestIterator.hasNext()) {
            Course request = requestIterator.next();
            int i = enrollment.get(request);
            if (addChecker(s, request, i )) {
                requestIterator.remove();
                s.schedule.add(request);
                enrollment.put(request, i + 1);
            }
        }
    }
    public void printEnrollment() {
        Collections.sort(mapStudents);
        for (Student student : mapStudents) {
            System.out.println("\n" + student.name + " " + student.gradYear + " " + student.drawNumber);
            for (Course c : student.schedule) {
                System.out.println(c);
            }
            System.out.println("--------------------");

        }
    }
    public boolean addChecker(Student s, Course c, Integer i) {
        for (Course existingC : s.schedule) {
            if (existingC.conflictsWith(c)) {
                return false;
            }
        }
        return (!s.isRegisteredFor(c)
                && s.totalRegisteredCredits() + c.credits <= 4.5
                && (i + 1) <= c.maxEnrollment);
    }


}