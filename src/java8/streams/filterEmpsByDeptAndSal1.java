/*
 * Find highest salary employee in each department.
 */

package java8.streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class filterEmpsByDeptAndSal1 {

    public static void main(String[] args) {

        EmployeeDataProvider emp = new EmployeeDataProvider();
        List<Employee> employees = emp.getEmployees();

        Map<String, Employee> result = employees.stream().collect(Collectors.groupingBy(Employee::getDeptName))
                .entrySet().stream().collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry ->  entry.getValue().stream()
                                .sorted((e1,e2) -> Long.compare(e2.getSalary(), e1.getSalary())).findFirst().orElse(null)
                ));
        System.out.println(result);
    }
}
