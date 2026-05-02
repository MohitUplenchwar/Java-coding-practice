/*
 * Get employees whose salary is greater than 60,000 grouped by department.
 */

package java8.streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class filterEmpsBySal {

    public static void main(String[] args) {

        filterEmpsByDept A = new filterEmpsByDept();
        List<Employee> employees = A.getEmpsList();
        System.out.println(employees);

        Map<String,List<Employee>> empBySal = employees.stream().filter(emp -> emp.getSalary()>60000).collect(Collectors.groupingBy(Employee :: getDeptName));
        System.out.println(empBySal);
    }
}
