package cn.eleven.manyToMany;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by User on 2017/7/4.
 */
public class Student {

    private Long id;
    private String name;
    private Set<Teacher> teachers = new HashSet<Teacher>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Teacher> teachers) {
        this.teachers = teachers;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
