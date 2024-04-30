package lab.lab4;

public class Department {
    private static long nextId = 1;
    private long id;
    private String name;

    public Department(String name) {
        this.id = nextId++;
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

