package Home5;

public class Category {
    private String id;
    private String name;
    public Category(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }

    public String toString() {
        return "\t" + this.name;
    }
}
