package lab.lab2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Класс для разрешения переменных в выражении.
 * Запрашивает у пользователя значения для неизвестных переменных.
 */
public class VariableResolver {
    private final Scanner scanner;
    private final Map<String, Double> variables = new HashMap<>();

    /**
     * Конструктор класса с передачей объекта Scanner для ввода данных пользователем.
     *
     * @param scanner объект Scanner для чтения ввода пользователя.
     */
    public VariableResolver(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Устанавливает значение переменной, запрашивая у пользователя, если оно ещё не было установлено.
     *
     * @param variableName имя переменной.
     * @return значение переменной.
     */
    public double resolve(String variableName) {
        if (!variables.containsKey(variableName)) {
            System.out.println("Введите значение для переменной " + variableName + ":");
            double value = scanner.nextDouble();
            scanner.nextLine();
            variables.put(variableName, value);
        }
        return variables.get(variableName);
    }
}
