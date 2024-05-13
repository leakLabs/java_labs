package lab.lab5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Lab5Test {
    @TempDir
    Path tmpDir;
    Path tmpFile;

    @BeforeEach
    public void before() throws IOException {
        tmpFile = tmpDir.resolve(String.valueOf(LocalTime.now().getSecond()) + System.currentTimeMillis() + System.nanoTime());
        Files.createFile(tmpFile);
    }

    @Test
    public void normalTest1() throws IOException {
        String propStr = """
                lab.lab5.classes.SomeInterface=lab.lab5.classes.SomeImpl
                lab.lab5.classes.SomeOtherInterface=lab.lab5.classes.SODoer
                """;
        Files.writeString(tmpFile, propStr);
        Injector injector = new Injector(tmpFile);
        TestCl testCl = new TestCl();

        injector.inject(testCl);

        assertEquals("SomeImplSODoer", testCl.printFields());
    }

    @Test
    public void normalTest2() throws IOException {
        String propStr = """
                lab.lab5.classes.SomeInterface=lab.lab5.classes.OtherImpl
                lab.lab5.classes.SomeOtherInterface=lab.lab5.classes.SODoer
                """;
        Files.writeString(tmpFile, propStr);
        Injector injector = new Injector(tmpFile);
        TestCl testCl = new TestCl();

        injector.inject(testCl);

        assertEquals("otherImplSODoer", testCl.printFields());
    }

    @Test
    public void withNullTest() throws IOException {
        String propStr = """
                lab.lab5.classes.SomeInterface=lab.lab5.classes.OtherImpl
                """;
        Files.writeString(tmpFile, propStr);
        Injector injector = new Injector(tmpFile);
        TestCl testCl = new TestCl();

        injector.inject(testCl);

        assertEquals("otherImplnull", testCl.printFields());
    }
}
