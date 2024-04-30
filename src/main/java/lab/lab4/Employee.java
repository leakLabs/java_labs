package lab.lab4;

import java.time.LocalDate;

public class Employee {
    private long id;
    private String name;
    private String gender;
    private Department department;
    private long salary;
    private LocalDate birthDate;

    public Employee(long id, String name, String gender, Department department, long salary, LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.department = department;
        this.salary = salary;
        this.birthDate = birthDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public long getSalary() {
        return salary;
    }

    public void setSalary(long salary) {
        this.salary = salary;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "id: " + id + "\n name: " + name + "\n gender: " + gender
                + "\n department: " + department.getId() + ' ' + department.getName()
                + "\n salary: " + salary + "\n birth date: " + birthDate + '\n';
    }
}
