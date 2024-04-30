package lab.lab4;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.exceptions.CsvValidationException;

import java.io.IOException;
import java.io.Reader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyCSVReader {
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    private static final char SEPARATOR = ';';
    private static final int SKIP_LINES = 1;

    private MyCSVReader() {
    }

    public static List<Employee> getAll(String resourceFileName) throws URISyntaxException {
        Path filePath = Paths.get(ClassLoader.getSystemResource(resourceFileName).toURI());
        return getAll(filePath);
    }

    public static List<Employee> getAll(Path filePath) {
        Map<String, Department> divisions = new HashMap<>();
        List<Employee> resultList = new ArrayList<>();

        try (Reader reader = Files.newBufferedReader(filePath)) {
            try (CSVReader csvReader =
                         new CSVReaderBuilder(reader).withCSVParser(getParser()).withSkipLines(SKIP_LINES).build()) {
                String[] line;
                while ((line = csvReader.readNext()) != null) {
                    resultList.add(createEmployeeFromString(line, divisions));
                }
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }

        return resultList;
    }

    private static Employee createEmployeeFromString(String[] strings, Map<String, Department> divisions) {
        long id = Long.parseLong(strings[0]);
        LocalDate birthDate = LocalDate.parse(strings[3], DATE_FORMAT);
        Department department = divisions.computeIfAbsent(strings[4], Department::new);
        long salary = Long.parseLong(strings[5]);

        return new Employee(id, strings[1], strings[2], department, salary, birthDate);
    }

    private static CSVParser getParser() {
        return new CSVParserBuilder().withSeparator(SEPARATOR).build();
    }
}
