package lab.lab3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListPerformanceTest {
    private final static int OPERATIONS_COUNT = 100000;

    public static void main(String[] args) {
        List<Integer> arrayList = new ArrayList<>();
        long arrayListAddTime = testAdd(arrayList);
        long arrayListGetTime = testGet(arrayList);
        long arrayListRemoveTime = testRemove(arrayList);

        List<Integer> linkedList = new LinkedList<>();
        long linkedListAddTime = testAdd(linkedList);
        long linkedListGetTime = testGet(linkedList);
        long linkedListRemoveTime = testRemove(linkedList);

        System.out.println("Results for " + OPERATIONS_COUNT + " operations:");
        System.out.println("Method       ArrayList   LinkedList");
        System.out.printf("Add %16dms %10dms\n", arrayListAddTime, linkedListAddTime);
        System.out.printf("Get %16dms %10dms\n", arrayListGetTime, linkedListGetTime);
        System.out.printf("Remove %13dms %10dms\n", arrayListRemoveTime, linkedListRemoveTime);
    }

    private static long testAdd(List<Integer> list) {
        long startTime = System.nanoTime();
        for (int i = 0; i < ListPerformanceTest.OPERATIONS_COUNT; i++) {
            list.add(i);
        }
        return (System.nanoTime() - startTime) / 1_000_000;
    }

    private static long testGet(List<Integer> list) {
        long startTime = System.nanoTime();
        for (int i = 0; i < ListPerformanceTest.OPERATIONS_COUNT; i++) {
            list.get(i % list.size());
        }
        return (System.nanoTime() - startTime) / 1_000_000;
    }

    private static long testRemove(List<Integer> list) {
        long startTime = System.nanoTime();
        for (int i = ListPerformanceTest.OPERATIONS_COUNT - 1; i >= 0; i--) {
            list.remove(i % list.size());
        }
        return (System.nanoTime() - startTime) / 1_000_000;
    }
}
