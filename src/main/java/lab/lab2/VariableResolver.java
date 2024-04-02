package lab.lab2;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class VariableResolver {
    private final Scanner scanner;
    private final Map<String, Double> variables = new HashMap<>();

    public VariableResolver(Scanner scanner) {
        this.scanner = scanner;
    }

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
