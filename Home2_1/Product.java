
package Home2_1;

public class Product {
    private String id;
    private String name;
    private double price;
    private int quantity;
    private String type;

    public Product(String id, String name, String price, String quantity, String type) {
        this.id = id;
        this.name = name;
        this.price = Double.valueOf(price.substring(1));
        this.quantity = Integer.valueOf(quantity);
        this.type = type;
    }
    //    public String getId() {
//        return this.id;
//    }
//
//    public String getName() {
//        return this.name;
//    }
//
    public double getPrice() {
        return this.price;
    }
//
//    public int getQuantity() {
//        return this.quantity;
//    }

    public String getType() {
        return this.type;
    }

//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
//
//    public void setQuantity(int quantity) {
//        this.quantity = quantity;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }

    public void toStringProduct() {
        System.out.print("\t" +  this.name + "\t\t" );
        System.out.printf("%.2f",this.price*34.00 );
        System.out.println("\t\t" + this.quantity);
    }
}
