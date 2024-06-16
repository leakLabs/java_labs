package lab;

import lab.lab4.Department;
import lab.lab4.Employee;
import lab.lab4.MyCSVReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class Lab4Test {
    @TempDir
    Path dirPath;
    Path filePath;
    private static final String testData = """
            id;name;gender;BirtDate;Division;Salary
            28281;Aahan;Male;15.05.1970;I;4800
            28282;Aala;Female;07.02.1983;J;2600
            28283;Aaleahya;Female;06.11.1949;F;1000
            28284;Aaleyah;Female;04.02.1944;G;1000""";

    @BeforeEach
    public void createFile() throws IOException {
        filePath = dirPath.resolve("testFile.txt");
        Files.createFile(filePath);
        Files.writeString(filePath, testData);
    }

    @Test
    public void readerTest() {
        List<Employee> expectingResult = List.of(
                new Employee(28281L, "Aahan", "Male", new Department("I", 1), 4800L, LocalDate.of(1970, 5, 15)),
                new Employee(28282L, "Aala", "Female", new Department("J", 2), 2600L, LocalDate.of(1983, 2, 7)),
                new Employee(28283L, "Aaleahya", "Female", new Department("F", 3), 1000L, LocalDate.of(1949, 11, 6)),
                new Employee(28284L, "Aaleyah", "Female", new Department("G", 4), 1000L, LocalDate.of(1944, 2, 4)));

        List<Employee> result = MyCSVReader.getAll(filePath);

        assertThat(result)
                .containsExactlyElementsOf(expectingResult);
    }
}
