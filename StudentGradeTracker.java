import java.util.*;

public class StudentGradeTracker {
    private static List<String> studentNames = new ArrayList<>();
    private static List<List<Double>> studentGrades = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        while (running) {
            System.out.println("\n--- Student Grade Tracker ---");
            System.out.println("1. Add Student and Grades");
            System.out.println("2. View All Students and Grades");
            System.out.println("3. Calculate Average Grade");
            System.out.println("4. Find Highest and Lowest Grades");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    displayAllStudents();
                    break;
                case 3:
                    calculateAverage();
                    break;
                case 4:
                    findHighestAndLowest();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting... Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        studentNames.add(name);

        List<Double> grades = new ArrayList<>();
        System.out.print("Enter number of grades: ");
        int numGrades = scanner.nextInt();

        for (int i = 0; i < numGrades; i++) {
            System.out.print("Enter grade " + (i + 1) + ": ");
            double grade = scanner.nextDouble();
            grades.add(grade);
        }
        studentGrades.add(grades);
        System.out.println("Student and grades added successfully!");
    }

    private static void displayAllStudents() {
        if (studentNames.isEmpty()) {
            System.out.println("No students added yet.");
            return;
        }

        System.out.println("\n--- Student Grades Report ---");
        for (int i = 0; i < studentNames.size(); i++) {
            System.out.print(studentNames.get(i) + ": ");
            System.out.println(studentGrades.get(i));
        }
    }

    private static void calculateAverage() {
        if (studentNames.isEmpty()) {
            System.out.println("No students added yet.");
            return;
        }

        System.out.println("\n--- Average Grades ---");
        for (int i = 0; i < studentNames.size(); i++) {
            List<Double> grades = studentGrades.get(i);
            double sum = 0;
            for (double grade : grades) {
                sum += grade;
            }
            double average = sum / grades.size();
            System.out.printf("%s: %.2f\n", studentNames.get(i), average);
        }
    }

    private static void findHighestAndLowest() {
        if (studentNames.isEmpty()) {
            System.out.println("No students added yet.");
            return;
        }

        System.out.println("\n--- Highest & Lowest Grades ---");
        for (int i = 0; i < studentNames.size(); i++) {
            List<Double> grades = studentGrades.get(i);
            double highest = Collections.max(grades);
            double lowest = Collections.min(grades);
            System.out.printf("%s - Highest: %.2f, Lowest: %.2f\n", 
                studentNames.get(i), highest, lowest);
        }
    }
}