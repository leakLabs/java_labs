package lab.lab2;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class FunctionEvaluator {
    private final Map<String, Function<Double, Double>> functions = new HashMap<>();

    public FunctionEvaluator() {
        functions.put("sin", FunctionEvaluator::degSin);
        functions.put("cos", FunctionEvaluator::degCos);
        functions.put("log", Math::log);
    }

    public Map<String, Function<Double, Double>> getFunctions() {
        return functions;
    }

    public double evaluate(String functionName, double argument) {
        if (!functions.containsKey(functionName)) {
            throw new IllegalArgumentException("Неизвестная функция: " + functionName);
        }
        return functions.get(functionName).apply(argument);
    }

    private static double degSin(double angle) {
        return Math.sin(Math.toRadians(angle));
    }

    private static double degCos(double angle) {
        return Math.cos(Math.toRadians(angle));
    }
}
