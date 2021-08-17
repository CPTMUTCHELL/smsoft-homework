package com.smsoft.repository;

import io.spring.guides.gs_producing_web_service.Department;
import io.spring.guides.gs_producing_web_service.Employee;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class DepartmentRepo {
    private static final Map<String, Department> departments = new HashMap<>();

    @PostConstruct
    public void initData() {
        Department dep1 = new Department();
        Department dep2 = new Department();
        Employee e1 =new Employee();
        Employee e2 =new Employee();
        Employee e3 =new Employee();
        Employee e4 =new Employee();
        e1.setName("emp1");
        e1.setAge(11);
        e2.setName("emp2");
        e2.setAge(21);
        e3.setName("emp3");
        e3.setAge(31);
        e4.setName("emp4");
        e4.setAge(41);
        dep1.setName("Department1");
        dep2.setName("Department2");
        dep1.getEmployee().add(e1);
        dep2.getEmployee().add(e2);
        dep2.getEmployee().add(e3);
        dep2.getEmployee().add(e4);
        departments.put(dep1.getName(), dep1);
        departments.put(dep2.getName(), dep2);


    }

    public Department findDepartment(String name) {
        Assert.notNull(name, "The department's name must not be null");
        return departments.get(name);
    }
    public Employee findOldestInDepartment(String name) {
        Assert.notNull(name, "The department's name must not be null");
        return departments.get(name).getEmployee().stream()
                .sorted((o1, o2) -> o2.getAge()- o1.getAge()).findFirst().get();
    }
}
