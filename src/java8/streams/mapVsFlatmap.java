/*
 * Diff betn map and flatMap
 */

package java8.streams;

import java.util.Arrays;
import java.util.List;

public class mapVsFlatmap {

    public static void main(String[] args) {
        EmployeeDataProvider edp1 = new EmployeeDataProvider();
        List<Employee> employees = edp1.getEmployees();

//        List<Employee> mapList = employees.stream().map(emp -> emp.getSalary() * 1.3).collect();

        List<String> chars = employees.stream()
                .flatMap(e -> e.getName().chars()
                        .mapToObj(c -> String.valueOf((char) c)))
                .toList();

        List<String> list = Arrays.asList("Mohit", "Rohan", "Sumit", "Rajat", "Shrirang");

//        List<String> chList = list.stream()
//                .flatMap(nm -> Arrays.stream(nm.split(""))
//                        .toList();
    }



}
