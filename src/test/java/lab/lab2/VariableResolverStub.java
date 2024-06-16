package lab.lab2;

import java.util.Scanner;

public class VariableResolverStub extends VariableResolver {
    private double ret;

    public VariableResolverStub(Scanner scanner) {
        super(scanner);
    }

    public void setRet(double ret) {
        this.ret = ret;
    }

    @Override
    public double resolve(String variableName) {
        return ret;
    }
}
