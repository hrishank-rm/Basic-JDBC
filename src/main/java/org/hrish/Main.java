package org.hrish;

import org.hrish.dao.StudentDAO;
import org.hrish.model.Student;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        boolean check = true;

        StudentDAO dao = new StudentDAO();

        while(check) {
            System.out.println("--------STUDENT DETAIL--------");
            System.out.println("1. Add Student");
            System.out.println("2. Delete Student");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. List Students");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch(choice) {
                case 1:
                    System.out.println("Enter student details");
                    System.out.print("Student Roll Number: ");
                    int roll1 = sc.nextInt();
                    System.out.print("Student Name: ");
                    String name = sc.next();
                    System.out.print("Student Marks: ");
                    float marks = sc.nextFloat();

                    if(dao.insert(new Student(roll1, name, marks))) {
                        System.out.println("Student added");
                    } else {
                        System.out.println("Failed to add the student");
                    }
                    break;

                case 2:
                    System.out.print("Student Roll Number: ");
                    int roll2 = sc.nextInt();
                    if(dao.deleteByRollNumber(roll2)) {
                        System.out.println("Student deleted");
                    } else {
                        System.out.println("Failed to delete the student");
                    }
                    break;

                case 3:
                    System.out.print("Student Roll Number: ");
                    int roll3 = sc.nextInt();
                    Student student = dao.searchByRollNumber(roll3);
                    if(student == null) {
                        System.out.println("Student not found");
                    } else {
                        System.out.println(student);
                    }
                    break;

                case 4:
                    System.out.print("Student Roll Number: ");
                    int roll4 = sc.nextInt();
                    System.out.print("New Student Name: ");
                    String name4 = sc.next();
                    System.out.print("New Marks: ");
                    float marks4 = sc.nextFloat();

                    if(dao.updateStudent(new Student(roll4, name4, marks4))) {
                        System.out.println("Student details updated");
                    } else {
                        System.out.println("Failed to update student details");
                    }
                    break;

                case 5:
                    for(Student std : dao.showAll()) {
                        System.out.println(std);
                    }
                    break;

                case 6:
                    System.out.println("Exiting...");
                    check = false;
                    break;

                default:
                    System.out.println("Invalid input");
            }
        }
    }
}
