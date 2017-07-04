package cn.eleven.oneToMany;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 2017/7/4.
 */
public class Department {
    private int id;
    private String name;
    private Set<Employee> employees = new HashSet<Employee>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
