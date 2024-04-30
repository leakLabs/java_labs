package lab.lab4;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Класс представляет собой сотрудника в организации. Каждый сотрудник имеет идентификатор, имя, пол,
 * отдел, зарплату и дату рождения.
 */
public class Employee {
    /**
     * Идентификатор сотрудника.
     */
    private long id;

    /**
     * Имя сотрудника.
     */
    private String name;

    /**
     * Пол сотрудника.
     */
    private String gender;

    /**
     * Отдел, в котором работает сотрудник.
     */
    private Department department;

    /**
     * Зарплата сотрудника.
     */
    private long salary;

    /**
     * Дата рождения сотрудника.
     */
    private LocalDate birthDate;

    /**
     * Конструктор для создания нового сотрудника.
     * Все параметры должны быть не null.
     *
     * @param id         Идентификатор сотрудника.
     * @param name       Имя сотрудника. Не может быть null.
     * @param gender     Пол сотрудника. Не может быть null.
     * @param department Отдел, в котором работает сотрудник. Не может быть null.
     * @param salary     Зарплата сотрудника.
     * @param birthDate  Дата рождения сотрудника. Не может быть null.
     */
    public Employee(long id, String name, String gender, Department department, long salary, LocalDate birthDate) {
        Objects.requireNonNull(name);
        Objects.requireNonNull(gender);
        Objects.requireNonNull(department);
        Objects.requireNonNull(birthDate);
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.department = department;
        this.salary = salary;
        this.birthDate = birthDate;
    }

    /**
     * Устанавливает идентификатор сотрудника.
     *
     * @param id Новый идентификатор для этого сотрудника.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Возвращает идентификатор сотрудника.
     *
     * @return Идентификатор сотрудника.
     */
    public long getId() {
        return id;
    }

    /**
     * Возвращает имя сотрудника.
     *
     * @return Имя сотрудника.
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает новое имя сотрудника.
     *
     * @param name Новое имя сотрудника. Не может быть null.
     */
    public void setName(String name) {
        Objects.requireNonNull(name);
        this.name = name;
    }

    /**
     * Возвращает пол сотрудника.
     *
     * @return Пол сотрудника.
     */
    public String getGender() {
        return gender;
    }

    /**
     * Устанавливает пол сотрудника.
     *
     * @param gender Новый пол сотрудника. Не может быть null.
     */
    public void setGender(String gender) {
        Objects.requireNonNull(gender);
        this.gender = gender;
    }

    /**
     * Возвращает отдел, в котором работает сотрудник.
     *
     * @return Отдел сотрудника.
     */
    public Department getDepartment() {
        return department;
    }

    /**
     * Устанавливает новый отдел для сотрудника.
     *
     * @param department Новый отдел для сотрудника. Не может быть null.
     */
    public void setDepartment(Department department) {
        Objects.requireNonNull(department);
        this.department = department;
    }

    /**
     * Возвращает зарплату сотрудника.
     *
     * @return Зарплата сотрудника.
     */
    public long getSalary() {
        return salary;
    }

    /**
     * Устанавливает новую зарплату для сотрудника.
     *
     * @param salary Новая зарплата сотрудника.
     */
    public void setSalary(long salary) {
        this.salary = salary;
    }

    /**
     * Возвращает дату рождения сотрудника.
     *
     * @return Дата рождения сотрудника.
     */
    public LocalDate getBirthDate() {
        return birthDate;
    }

    /**
     * Устанавливает новую дату рождения для сотрудника.
     *
     * @param birthDate Новая дата рождения сотрудника. Не может быть null.
     */
    public void setBirthDate(LocalDate birthDate) {
        Objects.requireNonNull(birthDate);
        this.birthDate = birthDate;
    }

    /**
     * Возвращает строковое представление сотрудника, включая все его атрибуты.
     *
     * @return Строковое представление сотрудника.
     */
    @Override
    public String toString() {
        return "id: " + id + "\n name: " + name + "\n gender: " + gender
                + "\n department: " + department.getId() + ' ' + department.getName()
                + "\n salary: " + salary + "\n birth date: " + birthDate + '\n';
    }

    /**
     * Проверяет равенство данного объекта с указанным объектом.
     *
     * @param obj объект, с которым сравнивается данный объект.
     * @return {@code true} если указанный объект также является
     * объектом класса Employee и все его поля равны полям
     * данного объекта, в противном случае {@code false}.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return id == employee.id &&
                salary == employee.salary &&
                Objects.equals(name, employee.name) &&
                Objects.equals(gender, employee.gender) &&
                Objects.equals(department, employee.department) &&
                Objects.equals(birthDate, employee.birthDate);
    }

    /**
     * Возвращает хэш-код данного объекта.
     *
     * @return хэш-код данного объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, department, salary, birthDate);
    }
}
