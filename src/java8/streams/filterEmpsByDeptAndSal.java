/*
 * Employees whose salary < average salary of their department.
 */

package java8.streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class filterEmpsByDeptAndSal {

    public static void main(String[] args) {

        filterEmpsByDept A = new filterEmpsByDept();
        List<Employee> employees = A.getEmpsList();
        System.out.println(employees);
        System.out.println("----------------------------------------------------------------------------------------------------------------");

        Map<String, List<Employee>> result = employees.stream().collect(Collectors.groupingBy(Employee::getDeptName))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        entry -> entry.getKey(),
                        entry -> {
                            List<Employee> empList = entry.getValue();

                            double avgSal = empList.stream().mapToLong(Employee::getSalary).average().orElse(0);

                            return empList.stream().filter(emp -> emp.getSalary()<avgSal).collect(Collectors.toList());
                        }

                ));
        System.out.println(result);
        System.out.println("----------------------------------------------------------------------------------------------------------------");
    }

}
