/*
* Question : Write a Java program to implement an ArrayList that stores employee details 
* (ID, Name, and Salary). Allow users to add, update, remove, and search employees.
* */

import java.util.ArrayList;
import java.util.Scanner;

public class Ex4_1 {
    static final Scanner sc = new Scanner(System.in);
    static int e_id = 1;

    static class employee {
        private final int    id;
        private       String name;
        private       int    salary;

        employee(String name, int salary) {
            this.id     = e_id++;
            this.name   = name;
            this.salary = salary;
        }

        public int get_id() { return id; }
        public String get_name()   { return name;   }
        public int    get_salary() { return salary; }

        public void set_name(String name)  { this.name   = name;   }
        public void set_salary(int salary) { this.salary = salary; }

        @Override
        public String toString() {
            return "ID: " + id + ", Name: " + name + ", Salary: " + salary;
        }
    }

    private static final ArrayList<employee> employees = new ArrayList<>();

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Add Employee\n2. Update Employee\n3. Remove Employee\n4. Search Employee\n5. Display Employees\n6. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            sc.nextLine();

            switch (choice) {
                case 1 -> add_employee();
                case 2 -> update_employee();
                case 3 -> remove_employee();
                case 4 -> search_employee();
                case 5 -> display_employees();
                case 6 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option! Try again.");
            }
        }
    }

    private static void add_employee() {
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter salary: ");
        int salary = sc.nextInt();

        employees.add(new employee(name, salary));
        System.out.println("Employee added successfully.");
    }

    private static void update_employee() {
        System.out.print("Enter Employee ID to update: ");
        int id = sc.nextInt();

        sc.nextLine();

        for (employee emp : employees) {
            if (emp.get_id() == id) {
                System.out.print("Enter new name: ");
                String name = sc.nextLine();

                System.out.print("Enter new salary: ");
                int salary = sc.nextInt();

                emp.set_name(name);
                emp.set_salary(salary);

                System.out.println("Employee updated successfully.");
                return;
            }
        }
        System.out.println("Employee not found!");
    }

    private static void remove_employee() {
        System.out.print("Enter Employee ID to remove: ");
        int id = sc.nextInt();

        employees.removeIf(emp -> emp.get_id() == id);
        System.out.println("Employee removed successfully.");
    }

    private static void search_employee() {
        System.out.print("Enter Employee ID to search: ");
        int id = sc.nextInt();

        for (employee emp : employees) {
            if (emp.get_id() == id) {
                System.out.println(emp);
                return;
            }
        }
        System.out.println("Employee not found!");
    }

    private static void display_employees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
        } else {
            employees.forEach(System.out::println);
        }
    }
}
