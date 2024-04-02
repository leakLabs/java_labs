package lab.lab2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Lab2Test {
    private final static double DELTA = 0.0001;
    private VariableResolverStub variableResolver;
    private ExpressionEvaluator evaluator;

    @BeforeEach
    void setUp() {
        variableResolver = new VariableResolverStub(null);
        evaluator = new ExpressionEvaluator(variableResolver, new FunctionEvaluator());
    }

    @Test
    void evaluateSimpleExpression() {
        assertEquals(4, evaluator.evaluate("2 + 2"), DELTA);
    }

    @Test
    void evaluateExpression() {
        assertEquals(3, evaluator.evaluate("2 * ( 4 + 3 ) / 14 * 4 - 1"), DELTA);
    }

    @Test
    void evaluateExpressionWithVariables() {
        variableResolver.setRet(5.0);
        assertEquals(10, evaluator.evaluate("x * 2"), DELTA);
    }

    @Test
    void evaluateExpressionWithFunctions() {
        assertEquals(1, evaluator.evaluate("sin 90"), DELTA);
    }

    @Test
    void evaluateComplexExpression() {
        variableResolver.setRet(5.0);
        assertEquals(10, evaluator.evaluate("x * 2 + log 1"), DELTA);
    }

    @Test
    void throwExceptionForInvalidExpression() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> evaluator.evaluate("2 +"));
    }
}
