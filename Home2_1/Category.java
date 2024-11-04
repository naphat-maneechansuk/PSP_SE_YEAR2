package Home2_1;

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
//    public void setId(String id) {
//        this.id = id;
//    }
//    public void setName(String name) {
//        this.name = name;
//    }
    public String toString() {
        return "\t" + this.name;
    }
}
