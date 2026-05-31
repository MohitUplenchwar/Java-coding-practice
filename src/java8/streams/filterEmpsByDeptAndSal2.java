/*
 * Find second highest salary employee in each department.
 */

package java8.streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class filterEmpsByDeptAndSal2 {

    public static void main(String[] args) {

        EmployeeDataProvider emp = new EmployeeDataProvider();
        List<Employee> employees = emp.getEmployees();

        Map<String, Employee> result = employees.stream().collect(Collectors.groupingBy(Employee::getDeptName))
                .entrySet().stream().collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry ->  entry.getValue().stream()
                                .sorted((e1,e2) -> Long.compare(e2.getSalary(), e1.getSalary())).distinct().skip(1).findFirst().orElse(null)
                ));
        System.out.println(result);
        System.out.println("--------------------------------------------------------------------------------------------------------------------------");

        /*
         * If any two employees from same department contains same salaries then..
         */
        Map<String, Employee> result1 =
                employees.stream()
                        .collect(Collectors.groupingBy(Employee::getDeptName))
                        .entrySet()
                        .stream()
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                entry -> entry.getValue().stream()
                                        .map(Employee::getSalary)       // get salaries
                                        .distinct()                     // remove duplicates
                                        .sorted((a, b) -> Long.compare(b, a)) // descending
                                        .skip(1)                        // second highest
                                        .findFirst()
                                        .flatMap(secondSalary ->
                                                entry.getValue().stream()
                                                        .filter(e -> e.getSalary() == secondSalary)
                                                        .findFirst()
                                        )
                                        .orElse(null)
                        ));
        System.out.println(result1);

    }
}
