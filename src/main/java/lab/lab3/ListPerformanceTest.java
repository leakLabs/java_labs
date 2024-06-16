package lab.lab3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Класс для тестирования производительности операций в ArrayList и LinkedList.
 */
public class ListPerformanceTest {
    private final static int OPERATIONS_COUNT = 100000;

    /**
     * Основной метод для запуска тестов производительности.
     */
    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        double arrayListAddTime = testAdd(arrayList);
        double arrayListGetTime = testGet(arrayList);
        double arrayListRemoveTime = testRemove(arrayList);

        List<Integer> linkedList = new LinkedList<>();
        double linkedListAddTime = testAdd(linkedList);
        double linkedListGetTime = testGet(linkedList);
        double linkedListRemoveTime = testRemove(linkedList);

        System.out.println("Results for " + OPERATIONS_COUNT + " operations:");
        System.out.println("Method       ArrayList   LinkedList");
        System.out.printf("Add %16.2fms %10.2fms\n", arrayListAddTime, linkedListAddTime);
        System.out.printf("Get %16.2fms %10.2fms\n", arrayListGetTime, linkedListGetTime);
        System.out.printf("Remove %13.2fms %10.2fms\n", arrayListRemoveTime, linkedListRemoveTime);
    }

    /**
     * Тестирует время добавления элементов в список.
     *
     * @param list Список для добавления элементов
     * @return время в миллисекундах, затраченное на операции
     */
    private static double testAdd(List<Integer> list) {
        long startTime = System.nanoTime();
        for (int i = 0; i < ListPerformanceTest.OPERATIONS_COUNT; i++) {
            list.add(i);
        }
        return (System.nanoTime() - startTime) / 1_000_000d;
    }

    /**
     * Тестирует время доступа к элементам списка.
     *
     * @param list Список, из которого происходит чтение
     * @return время в миллисекундах, затраченное на операции
     */
    private static double testGet(List<Integer> list) {
        long startTime = System.nanoTime();
        for (int i = 0; i < ListPerformanceTest.OPERATIONS_COUNT; i++) {
            list.get(i % list.size());
        }
        return (System.nanoTime() - startTime) / 1_000_000d;
    }

    /**
     * Тестирует время удаления элементов из списка.
     *
     * @param list Список, из которого удаляются элементы
     * @return время в миллисекундах, затраченное на операции
     */
    private static double testRemove(List<Integer> list) {
        long startTime = System.nanoTime();
        for (int i = ListPerformanceTest.OPERATIONS_COUNT - 1; i >= 0; i--) {
            list.remove(i % list.size());
        }
        return (System.nanoTime() - startTime) / 1_000_000d;
    }
}
