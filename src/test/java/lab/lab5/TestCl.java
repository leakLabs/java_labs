package lab.lab5;

import lab.lab5.classes.SomeInterface;
import lab.lab5.classes.SomeOtherInterface;

public class TestCl {
    @AutoInjectable
    private SomeInterface f1;
    @AutoInjectable
    private SomeOtherInterface f2;

    public String printFields() {
        return ((f1 == null) ? "null" : f1.doSomething())
                + ((f2 == null) ? "null" : f2.doSomeOther());
    }
}
