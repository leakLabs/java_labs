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

    /**
     * Конструктор создаёт экземпляр {@code Injector}, загружая свойства из указанного файла.
     *
     * @param propFile путь к файлу свойств.
     * @throws IOException          если происходит ошибка ввода/вывода при работе с файлом.
     * @throws NullPointerException если {@code propFile} равен {@code null}.
     */
    public Injector(Path propFile) throws IOException {
        Objects.requireNonNull(propFile);
        properties = new Properties();
        try (FileReader fileReader = new FileReader(propFile.toFile())) {
            properties.load(fileReader);
        }
    }

    /**
     * Конструктор создаёт экземпляр {@code Injector}, загружая свойства из файла ресурсов.
     *
     * @param resourceFileName имя файла ресурсов в classpath.
     * @throws URISyntaxException если синтаксис URI некорректен.
     * @throws IOException        если происходит ошибка ввода/вывода при работе с файлом.
     */
    public Injector(String resourceFileName) throws URISyntaxException, IOException {
        this(Paths.get(ClassLoader.getSystemResource(resourceFileName).toURI()));
    }

    /**
     * Метод производит инъекцию зависимостей в переданный объект.
     * Поля объекта, аннотированные как {@link AutoInjectable}, будут инициализированы
     * объектами классов, указанных в свойствах.
     *
     * @param object объект, в который необходимо внедрить зависимости.
     * @throws NullPointerException если {@code object} равен {@code null}.
     */
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

    /**
     * Метод пытается внедрить зависимость в поле объекта.
     *
     * @param target объект, поле которого должно быть инициализировано.
     * @param field  поле объекта, в которое должна быть выполнена инъекция.
     * @throws RuntimeException если происходит ошибка при создании экземпляра класса зависимости.
     */
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
