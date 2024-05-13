package lab.lab5;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;

public class Injector {
    private final Properties properties;

    public Injector(Path propFile) throws IOException {
        Objects.requireNonNull(propFile);
        properties = new Properties();
        try (FileReader fileReader = new FileReader(propFile.toFile())) {
            properties.load(fileReader);
        }
    }

    public Injector(String resourceFileName) throws URISyntaxException, IOException {
        this(Paths.get(ClassLoader.getSystemResource(resourceFileName).toURI()));
    }

    public void inject(Object object) {
        Objects.requireNonNull(object);
        Class<?> clazz = object.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            if (field.isAnnotationPresent(AutoInjectable.class)) {
                tryInjectField(object, field);
            }
        }
    }

    private void tryInjectField(Object target, Field field) {
        String injectingClassName = properties.getProperty(field.getType().getName());
        if (injectingClassName != null) {
            try {
                Class<?> clazz = Class.forName(injectingClassName);
                field.set(target, clazz.getDeclaredConstructor().newInstance());
            } catch (ClassNotFoundException | InvocationTargetException | IllegalAccessException |
                     InstantiationException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
