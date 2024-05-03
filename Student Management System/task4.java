import java.util.*;
import java.io.*;

class Student {
    String id;
    String name;
    String college;
    String branch;
    double cgpa;
    ArrayList<String> courses;

    public Student(String id, String name, String college, String branch, double cgpa) {
        this.id = id;
        this.name = name;
        this.college = college;
        this.branch = branch;
        this.cgpa = cgpa;
        this.courses = new ArrayList<>();
    }
}

class Course {
    String code;
    String title;
    int capacity;
    ArrayList<String> enrolledStudents;

    public Course(String code, String title, int capacity) {
        this.code = code;
        this.title = title;
        this.capacity = capacity;
        this.enrolledStudents=new ArrayList<>();
    }
}

public class task4{
    
    private static ArrayList<Student> students = new ArrayList<>();
    private static ArrayList<Course> courses = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    private static final String STUDENTS_FILE = "students.txt";
    private static final String COURSES_FILE = "courses.txt";

    public static void main(String[] args) {
        // Load data from files when the program starts
        loadStudents();
        loadCourses();

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Register Student");
            System.out.println("2. Add Course");
            System.out.println("3. List Courses");
            System.out.println("4. Enroll Student in Course");
            System.out.println("5. Remove Course");
            System.out.println("6. Check Available Seats");
            System.out.println("7. Exit");

            int choice = scanner.nextInt();

        switch(choice){    
            case 1:
                    // Register Student
                    RegisterStudent();
                    break;

                case 2:
                    // Add Course
                    AddCourse();
                    break;

                case 3:
                    // List Courses
                    ListCourse();
                    break;

                case 4:
                    // Enroll Student in Course
                    EnrollStudent();
                    break;

                case 5:
                    // Remove Course
                    RemoveCourse();
                    break;

                case 6:
                    // Check Available Seats
                    checkAvailableSeats();
                    break;

            case 7:
                // Save data to files before exiting
                saveStudents();
                saveCourses();
                System.out.println("Exiting program.");
                System.exit(0);
            }
        }
    }

    private static void checkAvailableSeats() {
        System.out.print("Enter course code to check available seats:");
        String courseCode = scanner.next();

        Course course = findCourseByCode(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        int availableSeats = course.capacity - course.enrolledStudents.size();
        System.out.println("Available seats in " + course.title + ": " + availableSeats);
    }


    private static void RemoveCourse(){
        System.out.print("Enter course code to remove:");
        String courseCode = scanner.next();

        Course course = findCourseByCode(courseCode);

        if (course == null) {
            System.out.println("Course not found.");
            return;
        }

        // Remove course from the list of courses
        courses.remove(course);
        System.out.println("Course removed successfully.");
        saveCourses();
    }

    private static void EnrollStudent(){
        System.out.print("Enter student ID:");
        String studentId = scanner.next();
        System.out.print("Enter course code:");
        String courseCode = scanner.next();

        Student student = findStudentById(studentId);
        Course course = findCourseByCode(courseCode);

        if (student == null || course == null) {
            System.out.println("Student or course not found.");
            return;
        }

        if (course.enrolledStudents.size() < course.capacity) {
            course.enrolledStudents.add(studentId);
            student.courses.add(courseCode);
            System.out.println("Student enrolled in the course.");
        } else {
            System.out.println("Course is full. Enrollment failed.");
        }
    }

    private static Student findStudentById(String id) {
        for (Student student : students) {
            if (student.id.equals(id)) {
                return student;
            }
        }
        return null;
    }

    private static Course findCourseByCode(String code) {
        for (Course course : courses) {
            if (course.code.equals(code)) {
                return course;
            }
        }
        return null;
    }

    private static void RegisterStudent(){
        System.out.print("Enter student ID:");
        String id = scanner.next();
        System.out.print("Enter student name:");
        String name = scanner.next();
        System.out.print("Enter college:");
        String college = scanner.next();
        System.out.print("Enter branch:");
        String branch = scanner.next();
        System.out.print("Enter CGPA:");
        double cgpa = scanner.nextDouble();

        Student student = new Student(id, name, college, branch, cgpa);
        students.add(student);
        System.out.println("Student registered successfully.");
        saveStudents();
    }

    private static void AddCourse(){
        System.out.print("Enter course code:");
        String code = scanner.next();
        System.out.print("Enter course title:");
        String title = scanner.next();
        System.out.print("Enter course capacity:");
        int capacity = scanner.nextInt();

        Course course = new Course(code, title, capacity);
        courses.add(course);
        System.out.println("Course added successfully.");
        saveCourses();
    }

    private static void ListCourse(){
        System.out.println("List of Courses:");
        for (Course course : courses) {
            System.out.println("Code: " + course.code + "\nTitle: " + course.title + "\nCapacity: " + course.capacity);
        }
    }

    private static void loadStudents() {
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 5) {
                    Student student = new Student(parts[0], parts[1], parts[2], parts[3], Double.parseDouble(parts[4]));
                    students.add(student);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading students: " + e.getMessage());
        }
    }

    private static void loadCourses() {
        try (BufferedReader reader = new BufferedReader(new FileReader(COURSES_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    Course course = new Course(parts[0], parts[1], Integer.parseInt(parts[2]));
                    courses.add(course);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading courses: " + e.getMessage());
        }
    }

    private static void saveStudents() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(STUDENTS_FILE))) {
            for (Student student : students) {
                writer.println(student.id + "," + student.name + "," + student.college + "," + student.branch + "," + student.cgpa);
            }
        } catch (IOException e) {
            System.out.println("Error saving students: " + e.getMessage());
        }
    }

    private static void saveCourses() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(COURSES_FILE))) {
            for (Course course : courses) {
                writer.println(course.code + "," + course.title + "," + course.capacity);
            }
        } catch (IOException e) {
            System.out.println("Error saving courses: " + e.getMessage());
        }
    }
}
