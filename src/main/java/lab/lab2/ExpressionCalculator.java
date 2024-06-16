package lab.lab2;

import java.util.Scanner;

/**
 * Основной класс калькулятора для вычисления арифметических выражений.
 * Поддерживает числа, операции (+, -, *, /), скобки, переменные и базовые математические функции (sin, cos, log).
 */
public class ExpressionCalculator {
    private ExpressionCalculator() {
    }

    public static void calculate() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение, разделяя пробелами:");
        String expression = scanner.nextLine();

        VariableResolver variableResolver = new VariableResolver(scanner);
        FunctionEvaluator functionEvaluator = new FunctionEvaluator();

        try {
            ExpressionEvaluator evaluator = new ExpressionEvaluator(variableResolver, functionEvaluator);
            double result = evaluator.evaluate(expression);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
