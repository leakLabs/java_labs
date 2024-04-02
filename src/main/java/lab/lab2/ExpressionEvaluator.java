package lab.lab2;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Класс для вычисления арифметических выражений.
 * Преобразует инфиксное выражение в постфиксное (обратную польскую нотацию) и вычисляет его.
 */
public class ExpressionEvaluator {
    private final VariableResolver variableResolver;
    private final FunctionEvaluator functionEvaluator;

    private static final Map<String, Integer> OPERATOR_PRECEDENCE = Map.of(
            "+", 1,
            "-", 1,
            "*", 2,
            "/", 2
    );

    /**
     * Конструктор класса.
     *
     * @param variableResolver объект для разрешения переменных.
     * @param functionEvaluator объект для вычисления функций.
     */
    public ExpressionEvaluator(VariableResolver variableResolver, FunctionEvaluator functionEvaluator) {
        this.variableResolver = variableResolver;
        this.functionEvaluator = functionEvaluator;
    }

    /**
     * Вычисляет значение арифметического выражения.
     *
     * @param expression строка, содержащая выражение для вычисления.
     * @return результат вычисления выражения.
     * @throws IllegalArgumentException если выражение некорректно.
     */
    public double evaluate(String expression) {
        List<String> postfix = infixToPostfix(expression);
        return evaluatePostfix(postfix);
    }

    private List<String> infixToPostfix(String expression) {
        List<String> result = new ArrayList<>();
        Stack<String> stack = new Stack<>();
        String[] tokens = expression.split("\\s+");
        for (String token : tokens) {
            if (isNumber(token) || isVariable(token)) {
                result.add(token);
            } else if (isFunction(token)) {
                stack.push(token);
            } else if (token.equals("(")) {
                stack.push(token);
            } else if (token.equals(")")) {
                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    result.add(stack.pop());
                }
                if (!stack.isEmpty() && stack.peek().equals("(")) {
                    stack.pop();
                } else {
                    throw new IllegalArgumentException("Неправильное выражение: скобки не согласованы.");
                }
                if (!stack.isEmpty() && isFunction(stack.peek())) {
                    result.add(stack.pop());
                }
            } else if (isOperator(token)) {
                while (!stack.isEmpty() && isOperator(stack.peek()) &&
                        OPERATOR_PRECEDENCE.get(token) <= OPERATOR_PRECEDENCE.get(stack.peek())) {
                    result.add(stack.pop());
                }
                stack.push(token);
            }
        }
        while (!stack.isEmpty()) {
            if (stack.peek().equals("(")) {
                throw new IllegalArgumentException("Неправильное выражение: скобки не согласованы.");
            }
            result.add(stack.pop());
        }
        return result;
    }

    private double evaluatePostfix(List<String> postfix) {
        Stack<Double> stack = new Stack<>();
        for (String token : postfix) {
            if (isNumber(token)) {
                stack.push(Double.parseDouble(token));
            } else if (isVariable(token)) {
                stack.push(variableResolver.resolve(token));
            } else if (isFunction(token)) {
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Неправильное выражение: недостаточно аргументов для функции.");
                }
                double arg = stack.pop();
                stack.push(functionEvaluator.evaluate(token, arg));
            } else if (isOperator(token)) {
                if (stack.size() < 2) {
                    throw new IllegalArgumentException("Неправильное выражение: недостаточно аргументов для операции.");
                }
                double b = stack.pop();
                double a = stack.pop();
                switch (token) {
                    case "+":
                        stack.push(a + b);
                        break;
                    case "-":
                        stack.push(a - b);
                        break;
                    case "*":
                        stack.push(a * b);
                        break;
                    case "/":
                        if (b == 0) throw new ArithmeticException("Деление на ноль.");
                        stack.push(a / b);
                        break;
                    default:
                        throw new IllegalArgumentException("Неизвестный оператор: " + token);
                }
            }
        }
        if (stack.size() != 1) {
            throw new IllegalArgumentException("Неправильное выражение: количество операторов и операндов не согласовано.");
        }
        return stack.pop();
    }

    private boolean isNumber(String token) {
        try {
            Double.parseDouble(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isOperator(String token) {
        return OPERATOR_PRECEDENCE.containsKey(token);
    }

    private boolean isFunction(String token) {
        return functionEvaluator.getFunctions().containsKey(token);
    }

    private boolean isVariable(String token) {
        return !isNumber(token) && !isOperator(token) && !isFunction(token) && !token.equals("(") && !token.equals(")");
    }
}
