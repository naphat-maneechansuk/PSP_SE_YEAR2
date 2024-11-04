package Home5;

public class CART {
    private String idMember;
    private String idProduct;
    private int quantity;

    public CART(String idMember, String idProduct, String quantity) {
        this.idMember = idMember;
        this.idProduct = idProduct;
        this.quantity = Integer.parseInt(quantity);
    }

    public String getIdMember() {
        return idMember;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
