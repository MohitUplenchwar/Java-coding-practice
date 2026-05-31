package java8.streams;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class findSecondHighestSalary {

    public static void main(String[] args) {
        List<Employee> lst = EmployeeDataProvider.getEmployees();


    }

    public static Employee secondHighestSalary(List<Employee> lst){
        return lst.stream().sorted(Comparator.comparingLong(Employee :: getSalary)).collect(Collectors.toList()).stream().skip(1).findFirst().orElse(null);
    }
}
