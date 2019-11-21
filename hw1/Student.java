// Student.java: Print the first name, last name, and an ID in special sorted formats: (1-unsorted, 2-sorted alphabetically by first name, 3-and sorted by id number).

//import edu.princeton.cs.algs4.*;

import java.util.Arrays;
import java.util.Comparator;

public class Student implements Comparable<Student> {
    private String name;
    private String lastName;
    private final int id;

    public Student(String lastName, String name, int id) {
        this.name = name;
        this.lastName = lastName;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String toString() {
        //FORMAT SHOULD BE FIRST_NAME LAST_NAME ID
        String answer = name + " " + lastName + " " + id;
        return answer;
    }

    public int compareTo(Student st) {
        //COMPARE BY FIRST NAME
        String name1 = this.getName();
        String name2 = st.getName();
        for (int i = 0; i < Math.min(name1.length(), name2.length()); i++) {
            if (name1.charAt(i) - name2.charAt(i) != 0) {
                return (int) Math.signum(name1.charAt(i) - name2.charAt(i));
            } else {
                continue;
            }
        }
        return 0;
    }

    public static class lastNameOrder implements Comparator<Student> {
        public int compare(Student thisStudent, Student otherStudent) {
            //SORT BY ID
            return (int) Math.signum(thisStudent.id - otherStudent.id);
        }
    }

    public static void main(String arg[]) {
        Student[] student = new Student[6];
        student[0] = new Student("Tolaymat", "Samy", 2);
        student[1] = new Student("Liu", "Linxin", 6);
        student[2] = new Student("Kim", "Jung S.", 9);
        student[3] = new Student("Le", "Kimberly N.", 1);
        student[4] = new Student("Gonzalez", "Robert A.", 8);
        student[5] = new Student("Roscoe", "Sarah R.", 4);

        //CHANGE FOR LOOP TO ENHANCED LOOP
        StdOut.println("");
        for (int i = 0; i < student.length; i++)
            System.out.println("Unsorted: " + student[i]);

        StdOut.println("");
        Arrays.sort(student);
        for (int i = 0; i < student.length; i++)
            System.out.println("Sorted by fisrt name: " + student[i]);

        StdOut.println("");
        Arrays.sort(student, new Student.lastNameOrder());
        for (int i = 0; i < student.length; i++)
            System.out.println("Sorted by id: " + student[i]);
    }
}
