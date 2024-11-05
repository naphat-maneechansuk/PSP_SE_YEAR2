package Home5;

public class Product {
    private String id;
    private String name;
    private double price;
    private int quantity;
    private String type;

    // Constructor
    public Product(String id, String name, String price, String quantity, String type) {
        this.id = id;
        this.name = name;
        // ตรวจสอบว่า price มีสัญลักษณ์สกุลเงินหรือไม่
        this.price = Double.valueOf(price.startsWith("$") ? price.substring(1) : price);
        this.quantity = Integer.valueOf(quantity);
        this.type = type;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(String price) {
        this.price = Double.parseDouble(price);
    }
    public void setQuantity(String quantity) {
        this.quantity = Integer.parseInt(quantity);
    }
    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    // Getter methods
    public double getPrice() {
        return this.price;
    }

    public String getType() {
        return this.type;
    }

    // Method คำนวณส่วนลด
    public double discountProduct(String role) {
        switch (role) {
            case "Silver":
                return (getPrice() * 34.00) * 0.05;  // ส่วนลด 5%
            case "Gold":
                return (getPrice() * 34.00) * 0.10;  // ส่วนลด 10%
            default:
                return 0;  // ไม่มีส่วนลด
        }
    }
    // แสดงรายละเอียดสินค้า
    public void toStringProduct(String role) {
        double discountAmount = discountProduct(role); // Calculate discount amount
        System.out.printf("%-16s", this.name);  // Print product name

        if (role.equals("Staff") || role.equals("Regular")) {
            // Display price without discount
            System.out.printf("%-20.2f", this.price * 34.00);  // Full price
            System.out.printf("%-15d\n", this.quantity);  // Quantity
        } else {
            // Display price with discount
            double discountedPrice = (this.price * 34.00) - discountAmount;
            String discount = String.format("%.2f", discountedPrice);
            String fullPrice = String.format("%.2f", this.price * 34.00);
            System.out.printf("%-20s", discount + " (" + fullPrice + ")");  // Discounted price with full price in parentheses
            System.out.printf("%-15d\n", this.quantity);  // Quantity
        }
    }
    public void toStringPrice(String role){
        double discountAmount = discountProduct(role);
        if (role.equals("Staff") || role.equals("Regular")) {
            System.out.printf("%-20.2f\n", this.price * 34.00);
        } else {
            // Display price with discount
            double discountedPrice = (this.price * 34.00) - discountAmount;
            String discount = String.format("%.2f", discountedPrice);
            String fullPrice = String.format("%.2f", this.price * 34.00);
            System.out.printf("%-20s\n", discount + " (" + fullPrice + ")");
        }
    }
}
