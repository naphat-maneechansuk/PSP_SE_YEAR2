package Home2_1;

import java.util.ArrayList;
public class Store {

    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Category> categories = new ArrayList<>();

    public void addProduct(Product product) {
        products.add(product);
    }
//    public void removeProduct(int index) {
//        products.remove(index);
//    }
//    public void updateProduct(int index, PRODUCT product) {
//        products.set(index, product);
//    }
//    public PRODUCT getProduct(int index) {
//        return products.get(index);
//    }
//    public int getSizeProduct() {
//        return products.size();
//    }
    public void addCategory(Category category) {
        categories.add(category);
    }
    public int getSizeCategory() {
        return categories.size();
    }
    public Category getCategory(int index) {
            return categories.get(index - 1);
    }
    public void showAllCategory(){
        for (int i = 0; i < categories.size(); i++) {
            System.out.println(i + 1 + categories.get(i).getName());
        }
    }
    public void showProduct(int index) {
            int number = 1;
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getType().equals(categories.get(index - 1).getId())) {
                    System.out.print(number);
                    products.get(i).toStringProduct();
                    number++;
                }
        }
    }
}
