/*
    Using Java Stream Get a list of employees from each department whose salary is greater than the average salary of their department
	Employee {
		private int id;
		private String name;
		private long salary;
		private String deptName;
	}
 */

package java8.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class filterEmpsByDept {

    public List<Employee> getEmpsList(){

        return Arrays.asList(
                new Employee(1, "Mohit", 50000, "IT"),
                new Employee(2, "Amit", 60000, "HR"),
                new Employee(3, "Ravi", 70000, "IT"),
                new Employee(4, "Neha", 55000, "Finance"),
                new Employee(5, "Sneha", 80000, "IT"),
                new Employee(6, "Rahul", 45000, "HR"),
                new Employee(7, "Pooja", 90000, "Finance"),
                new Employee(8, "Karan", 75000, "IT"),
                new Employee(9, "Anjali", 65000, "HR"),
                new Employee(10, "Vikas", 85000, "Finance")
        );
    }

    public static void main(String[] args) {

        filterEmpsByDept Infosys = new filterEmpsByDept();
        List<Employee> employees = Infosys.getEmpsList();

        Map<String, List<Employee>> result =
                employees.stream()
                        .collect(Collectors.groupingBy(Employee::getDeptName))
                        .entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                entry -> entry.getKey(),
                                entry -> {
                                    double avgSalary = entry.getValue().stream()
                                            .mapToLong(Employee::getSalary)
                                            .average()
                                            .orElse(0);

                                    return entry.getValue().stream()
                                            .filter(e -> e.getSalary() > avgSalary)
                                            .collect(Collectors.toList());
                                }
                        ));
        System.out.println(result);
    }
}

