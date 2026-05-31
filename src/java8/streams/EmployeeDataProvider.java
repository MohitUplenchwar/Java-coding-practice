package java8.streams;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class EmployeeDataProvider {

    public static List<Employee> getEmployees() {

        String[] departments = {"IT", "HR", "Finance", "Admin"};
        Random random = new Random();

        return IntStream.rangeClosed(1, 100 )
                .mapToObj(i -> new Employee(
                        i,
                        "Emp" + i,
                        30000 + random.nextInt(70000),
                        departments[random.nextInt(departments.length)]
                ))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        EmployeeDataProvider.getEmployees().forEach(System.out::println);
    }
}
