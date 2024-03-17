package lab.lab1;

import java.util.Arrays;

/**
 * Класс MVGContainer представляет собой контейнер для хранения целых чисел.
 * Реализован на основе динамического массива, поддерживает операции добавления,
 * извлечения, удаления элемента по индексу и удаления элемента по значению.
 */
public class MVGContainer {
    private final static int DEFAULT_SIZE = 10;
    private int[] data;
    private int size;

    /**
     * Конструктор по умолчанию.
     * Инициализирует контейнер заданным размером массива.
     * @param contSize Размер контейнера
     */
    public MVGContainer(int contSize) {
        if (contSize < 1) {
            throw new IllegalArgumentException("Заданный размер меньше 0");
        }
        data = new int[contSize];
        this.size = 0;
    }

    /**
     * Конструктор по умолчанию.
     * Инициализирует контейнер начальным размером массива.
     */
    public MVGContainer() {
        this(DEFAULT_SIZE);
    }

    /**
     * Добавляет элемент в контейнер.
     * @param value Значение для добавления в контейнер.
     */
    public void add(int value) {
        ensureCapacity();
        data[size++] = value;
    }

    /**
     * Проверка места в массиве, увеличение его размера при необходимости.
     */
    private void ensureCapacity() {
        if (size == data.length) {
            data = Arrays.copyOf(data, size * 2);
        }
    }

    /**
     * Возвращает элемент по указанному индексу.
     * @param index Индекс элемента для возврата.
     * @return Значение элемента на указанной позиции.
     * @throws IndexOutOfBoundsException если индекс находится вне границ диапазона [0, size).
     */
    public int get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(String.format("Index: %d, Size: %d", index, size));
        }
        return data[index];
    }

    /**
     * Удаляет элемент по указанному индексу.
     * @param index Индекс удаляемого элемента.
     * @throws IndexOutOfBoundsException если индекс находится вне границ диапазона [0, size).
     */
    public void removeAt(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(String.format("Index: %d, Size: %d", index, size));
        }
        int numMoved = size - index - 1;
        if (numMoved > 0) {
            System.arraycopy(data, index + 1, data, index, numMoved);
        }
        size--;
    }

    /**
     * Удаляет первое вхождение указанного значения в контейнере.
     * @param value Значение для удаления.
     * @return true если элемент был найден и удален, иначе false.
     */
    public boolean remove(int value) {
        for (int i = 0; i < size; i++) {
            if (data[i] == value) {
                removeAt(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Возвращает количество элементов в контейнере.
     * @return Количество элементов.
     */
    public int size() {
        return size;
    }

    /**
     * Возвращает строковое представление контейнера.
     * @return Строка, содержащая все элементы контейнера, разделенные запятой и пробелом.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i < size - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');
        return sb.toString();
    }
}

