import java.io.*;
import java.util.*;

/* =========================
   MODEL CLASSES
========================= */

class Student implements Serializable {
    private int id;
    private String name;
    private Map<Integer, Integer> marks = new HashMap<>(); // courseId -> marks
    private List<Integer> enrolledCourses = new ArrayList<>();

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public void enrollCourse(int courseId) {
        if (!enrolledCourses.contains(courseId))
            enrolledCourses.add(courseId);
    }

    public List<Integer> getEnrolledCourses() {
        return enrolledCourses;
    }

    public void addMarks(int courseId, int score) {
        marks.put(courseId, score);
    }

    public Map<Integer, Integer> getMarks() {
        return marks;
    }
}

class Faculty implements Serializable {
    private int id;
    private String name;
    private List<Integer> assignedCourses = new ArrayList<>();

    public Faculty(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public void assignCourse(int courseId) {
        assignedCourses.add(courseId);
    }

    public List<Integer> getAssignedCourses() {
        return assignedCourses;
    }
}

class Course implements Serializable {
    private int id;
    private String name;
    private int attendance = 0;

    public Course(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() { return id; }
    public String getName() { return name; }

    public void markAttendance() {
        attendance++;
    }

    public int getAttendance() {
        return attendance;
    }
}

/* =========================
   MAIN SYSTEM CLASS
========================= */

 class CampusManagementSystem {

    private static Map<Integer, Student> students = new HashMap<>();
    private static Map<Integer, Faculty> facultyMembers = new HashMap<>();
    private static Map<Integer, Course> courses = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n===== CAMPUS MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Add Faculty");
            System.out.println("3. Add Course");
            System.out.println("4. Enroll Student in Course");
            System.out.println("5. Assign Course to Faculty");
            System.out.println("6. Add Marks");
            System.out.println("7. View Students");
            System.out.println("8. View Courses");
            System.out.println("9. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> addFaculty();
                case 3 -> addCourse();
                case 4 -> enrollStudent();
                case 5 -> assignCourseToFaculty();
                case 6 -> addMarks();
                case 7 -> viewStudents();
                case 8 -> viewCourses();
                case 9 -> System.out.println("System Closed.");
                default -> System.out.println("Invalid choice!");
            }

        } while (choice != 9);
    }

    /* =========================
       FUNCTION DEFINITIONS
    ========================= */

    private static void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        students.put(id, new Student(id, name));
        System.out.println("Student Added Successfully!");
    }

    private static void addFaculty() {
        System.out.print("Enter Faculty ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Faculty Name: ");
        String name = sc.nextLine();

        facultyMembers.put(id, new Faculty(id, name));
        System.out.println("Faculty Added Successfully!");
    }

    private static void addCourse() {
        System.out.print("Enter Course ID: ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Enter Course Name: ");
        String name = sc.nextLine();

        courses.put(id, new Course(id, name));
        System.out.println("Course Added Successfully!");
    }

    private static void enrollStudent() {
        System.out.print("Enter Student ID: ");
        int studentId = sc.nextInt();
        System.out.print("Enter Course ID: ");
        int courseId = sc.nextInt();

        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Student Not Found!");
            return;
        }

        if (!courses.containsKey(courseId)) {
            System.out.println("Course Not Found!");
            return;
        }

        student.enrollCourse(courseId);
        System.out.println("Enrollment Successful!");
    }

    private static void assignCourseToFaculty() {
        System.out.print("Enter Faculty ID: ");
        int facultyId = sc.nextInt();
        System.out.print("Enter Course ID: ");
        int courseId = sc.nextInt();

        Faculty faculty = facultyMembers.get(facultyId);
        if (faculty == null) {
            System.out.println("Faculty Not Found!");
            return;
        }

        if (!courses.containsKey(courseId)) {
            System.out.println("Course Not Found!");
            return;
        }

        faculty.assignCourse(courseId);
        System.out.println("Course Assigned Successfully!");
    }

    private static void addMarks() {
        System.out.print("Enter Student ID: ");
        int studentId = sc.nextInt();
        System.out.print("Enter Course ID: ");
        int courseId = sc.nextInt();
        System.out.print("Enter Marks: ");
        int marks = sc.nextInt();

        Student student = students.get(studentId);
        if (student == null) {
            System.out.println("Student Not Found!");
            return;
        }

        student.addMarks(courseId, marks);
        System.out.println("Marks Added Successfully!");
    }

    private static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println("No Students Available.");
            return;
        }

        for (Student s : students.values()) {
            System.out.println("\nID: " + s.getId());
            System.out.println("Name: " + s.getName());
            System.out.println("Enrolled Courses: " + s.getEnrolledCourses());
            System.out.println("Marks: " + s.getMarks());
        }
    }

    private static void viewCourses() {
        if (courses.isEmpty()) {
            System.out.println("No Courses Available.");
            return;
        }

        for (Course c : courses.values()) {
            System.out.println("ID: " + c.getId() + " | Name: " + c.getName());
        }
    }
}