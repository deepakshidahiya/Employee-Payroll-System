/*
   Project: Employee Payroll System
   Developed by: Deepakshi Dahiya
   Github: https://github.com/deepakshidahiya
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class Employee {
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    // Abstract method to be implemented by subclasses
    public abstract double calculateSalary();

    public double calculateBonus() {
        return calculateSalary() * 0.10;
    }

    @Override
    public String toString() {
        return "Employee [name=" + name + ", id=" + id + ", salary=" + calculateSalary() + "]";
    }
}

class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}

class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(String name, int id, int hoursWorked, double hourlyRate) {
        super(name, id);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public double calculateSalary() {
        return hoursWorked * hourlyRate;
    }
}

class PayrollSystem {
    private List<Employee> employeeList;

    public PayrollSystem() {
        employeeList = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employeeList.add(employee);
    }

    public void removeEmployee(int id) {
        Employee employeeToRemove = null;
        for (Employee employee : employeeList) {
            if (employee.getId() == id) {
                employeeToRemove = employee;
                break;
            }
        }
        if (employeeToRemove != null) {
            employeeList.remove(employeeToRemove);
        }
    }

    public void displayEmployees() {
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }

    public void calculateTotalPayroll() {
        double total = 0;
        for(Employee emp : employeeList) {
            total += emp.calculateSalary();
        }
        System.out.println("Total Payroll Expense: " + total);
    }

    public void searchEmployeeById(int id) {
        for (Employee emp : employeeList) {
            if (emp.getId() == id) {
                System.out.println("Employee Found: " + emp);
                return;
            }
        }
        System.out.println("Employee not found!");
    }
    public void displayBonus() {
        for (Employee emp : employeeList) {
            System.out.println(emp.getName() + " Bonus: " + emp.calculateBonus());
        }
    }
}

public class Main {
    public static void main(String[] args) {
        PayrollSystem payrollSystem = new PayrollSystem();

        FullTimeEmployee emp1 = new FullTimeEmployee("Deepak Kuhar", 1001, 5000.0);
        FullTimeEmployee emp2 = new FullTimeEmployee("Aarna Kuhar", 1002, 7000.0);
        PartTimeEmployee emp11 = new PartTimeEmployee("Shikha Singh", 1011, 25, 100.0);
        PartTimeEmployee emp12 = new PartTimeEmployee("Shweta Singh", 1012, 37, 100.0);

        payrollSystem.addEmployee(emp1);
        payrollSystem.addEmployee(emp2);
        payrollSystem.addEmployee(emp11);
        payrollSystem.addEmployee(emp12);

        System.out.println("Employee Payroll System - Developed by Deepakshi Dahiya");

        System.out.println("Initial Employee Details:");
        payrollSystem.displayEmployees();

        System.out.println("\nRemoving Employee...");
        payrollSystem.removeEmployee(1011);

        System.out.println("\nRemaining Employee Details:");
        payrollSystem.displayEmployees();

        payrollSystem.calculateTotalPayroll();

        payrollSystem.displayBonus();

        payrollSystem.searchEmployeeById(1012);

        Scanner sc = new Scanner(System.in);
        System.out.println("Enter employee ID to search:");
        int id = sc.nextInt();
        payrollSystem.searchEmployeeById(id);
    }
}
