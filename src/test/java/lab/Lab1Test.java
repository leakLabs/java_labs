package lab;

import lab.lab1.MVGContainer;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.*;

public class Lab1Test {
    private final MVGContainer container = new MVGContainer();

    @Test
    public void AddAndGetTest() {
        container.add(10);
        container.add(20);

        assertEquals(10, container.get(0));
        assertEquals(20, container.get(1));
    }

    @Test
    public void RemoveAtTest() {
        container.add(10);
        container.add(20);
        container.add(30);

        container.removeAt(1);

        assertEquals(2, container.size());
        assertEquals(30, container.get(1));
    }

    @Test
    public void removeTest() {
        container.add(10);
        container.add(20);
        container.add(30);

        assertTrue(container.remove(20));
        assertEquals(2, container.size());
        assertFalse(container.remove(40));
        assertEquals(2, container.size());
    }

    @Test
    public void getOutOfBoundsTest() {
        container.add(10);

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> container.get(2));
    }

    @Test
    public void removeOutOfBoundsTest() {
        container.add(10);

        assertThatExceptionOfType(IndexOutOfBoundsException.class)
                .isThrownBy(() -> container.removeAt(2));
    }

    @Test
    public void constructorIllegalArgumentTest() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> new MVGContainer(-5));
    }
}
