package lab.lab4;

import java.util.Objects;

/**
 * Класс представляет собой отдел в организации. Каждый отдел имеет уникальный идентификатор и имя.
 */
public class Department {
    /**
     * Статическая переменная для генерации уникальных идентификаторов для новых объектов отдела.
     */
    private static long nextId = 1;

    /**
     * Уникальный идентификатор для отдела.
     */
    private long id;

    /**
     * Название отдела.
     */
    private String name;

    public Department(String name, long id) {
        Objects.requireNonNull(name);
        this.id = id;
        this.name = name;
    }

    /**
     * Конструктор для создания нового отдела с заданным именем.
     * Идентификатор автоматически присваивается на основе статической переменной nextId.
     *
     * @param name Название отдела. Не может быть null.
     */
    public Department(String name) {
        Objects.requireNonNull(name);
        this.id = nextId++;
        this.name = name;
    }

    /**
     * Устанавливает идентификатор отдела.
     *
     * @param id Новый идентификатор для этого отдела.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Возвращает идентификатор отдела.
     *
     * @return Идентификатор отдела.
     */
    public long getId() {
        return id;
    }

    /**
     * Возвращает название отдела.
     *
     * @return Название отдела.
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает новое название отдела.
     *
     * @param name Новое название отдела. Не может быть null.
     */
    public void setName(String name) {
        Objects.requireNonNull(name);
        this.name = name;
    }

    /**
     * Проверяет равенство данного объекта с указанным объектом.
     *
     * @param obj объект, с которым сравнивается данный объект.
     * @return {@code true} если указанный объект также является
     * объектом класса Department и все его поля равны полям
     * данного объекта, в противном случае {@code false}.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Department department = (Department) obj;
        return id == department.id && Objects.equals(name, department.name);
    }

    /**
     * Возвращает хэш-код данного объекта.
     *
     * @return хэш-код данного объекта.
     */
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}

