import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
class StudentPerformanceTracker {
    private static List<Student> students = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    public static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        System.out.print("Enter problems solved: ");
        int problemsSolved = scanner.nextInt();
        System.out.print("Enter accuracy rate (in %): ");
        double accuracyRate = scanner.nextDouble();
        System.out.print("Enter ranking: ");
        int ranking = scanner.nextInt();
        scanner.nextLine();
        Student student = new Student(name, problemsSolved, accuracyRate, ranking);
        students.add(student);
        System.out.println("Student added successfully!");
    }
    public static void displayStudents() {
        System.out.println("Student Performance Data:");
        students.forEach(System.out::println);
    }
    public static double calculateAverageProblemsSolved() {
        return students.stream()
                .mapToInt(Student::getProblemsSolved)
                .average()
                .orElse(0);
    }
    public static double calculateAverageAccuracyRate() {
        return students.stream()
                .mapToDouble(Student::getAccuracyRate)
                .average()
                .orElse(0);
    }
    public static void findStudentByName() {
        System.out.print("Enter the student's name to search: ");
        String name = scanner.nextLine();
        Optional<Student> student = students.stream()
                .filter(s -> s.getName().equalsIgnoreCase(name))
                .findFirst();
        student.ifPresentOrElse(
                System.out::println,
                () -> System.out.println("Student not found."));
    }
    public static void displayStatistics() {
        System.out.println("Average Problems Solved: " + calculateAverageProblemsSolved());
        System.out.println("Average Accuracy Rate: " + calculateAverageAccuracyRate());
    }
    public static void main(String[] args) {
        while (true) {
            System.out.println("\nStudent Performance Tracker");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Search Student by Name");
            System.out.println("4. Display Statistics");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayStudents();
                    break;
                case 3:
                    findStudentByName();
                    break;
                case 4:
                    displayStatistics();
                    break;
                case 5:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
class Student {
    private String name;
    private int problemsSolved;
    private double accuracyRate;
    private int ranking;
    public Student(String name, int problemsSolved, double accuracyRate, int ranking) {
        this.name = name;
        this.problemsSolved = problemsSolved;
        this.accuracyRate = accuracyRate;
        this.ranking = ranking;
    }
    public String getName() {
        return name;
    }
    public int getProblemsSolved() {
        return problemsSolved;
    }
    public double getAccuracyRate() {
        return accuracyRate;
    }
    public int getRanking() {
        return ranking;
    }
    public String toString() {
        return "Name: " + name +
                ", Problems Solved: " + problemsSolved +
                ", Accuracy Rate: " + accuracyRate +
                ", Ranking: " + ranking;
    }
}