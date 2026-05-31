/*
 * Employees whose salary > average salary of their department.
 */

package java8.streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class filterEmpsByDeptAndSal3 {

    public static void main(String[] args) {

        List<Employee> lst = EmployeeDataProvider.getEmployees();
        findEmployeesBySalary(lst);

    }

    public static void findEmployeesBySalary(List<Employee> lst){


        //Step 1 : List down all employees by there department.
        Map<String, List<Employee>> empByDept = lst.stream().collect(Collectors.groupingBy(Employee :: getDeptName));
        System.out.println(empByDept);

        System.out.println(lst.stream().collect(Collectors.averagingDouble(Employee :: getSalary)));

        //Step 2 : Find average salary of each department
        Map<String, Double> empBySalAndDept = empByDept.entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> entry.getValue().stream().collect(Collectors.averagingDouble(Employee :: getSalary))
                ));

        System.out.println(empBySalAndDept);

        //Step 3 : Filter out employees by their salary
        lst.stream()
                .filter(emp -> emp.getSalary() > empBySalAndDept.get(emp.getDeptName()))
                .forEach(emp -> System.out.println("Name : " + emp.getName() + ", Salary : " + emp.getSalary() + ", Department : " + emp.getDeptName()));

    }
}
