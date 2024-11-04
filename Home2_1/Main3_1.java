package Home2_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main3_1 {
    public static void main(String[] args) throws FileNotFoundException {
        for (;;){
            File fileCategoly = new File("C:\\Users\\naphat\\Downloads\\CATEGORY.txt");
            File fileProduct = new File("C:\\Users\\naphat\\Downloads\\PRODUCT (1).txt");
            Scanner keyboard = new Scanner(System.in);
            //display #1
            System.out.println("===== SE Store =====");
            System.out.println("1. Show Category");
            System.out.println("2. Exit");
            System.out.println("====================");
            System.out.print("Select (1-2) : ");
            String input_select = keyboard.next();
            //เลือก1
            if (input_select.equals("1")){
                Store store = new Store();
                Scanner file = new Scanner(fileCategoly);
                //อ่านไฟล์  category เเล้วเก็บไว้ใน store
                while (file.hasNextLine()){
                    String idCategory = file.next();
                    String nameCategory = file.nextLine();
                    store.addCategory(new Category(idCategory, nameCategory));
                }
                // อ่านไฟล์ product เเล้วเก็บไว้ใน store
                file = new Scanner(fileProduct);
                while (file.hasNextLine()){
                    String idProduct = file.next();
                    String nameProduct = file.next();
                    String priceProduct = file.next();
                    String quantityProduct = file.next();
                    String typeProduct = file.next();
                    store.addProduct(new Product(idProduct, nameProduct, priceProduct, quantityProduct, typeProduct));
                }
                for (;;){
                    try {
                        //display #2
                        System.out.println("===== SE Store's PRODUCT Categories =====\t");
                        System.out.println("#\tCategory\t");
                        store.showAllCategory();
                        System.out.println("=========================================");
                        System.out.print("Select Category to Show PRODUCT (1-");
                        System.out.print(store.getSizeCategory());
                        System.out.println(") or Q for exit");
                        System.out.print("Select : ");
                        input_select = keyboard.next();
                        if (input_select.equals("Q") || input_select.equals("q")) {
                            break;
                        } else if (Integer.valueOf(input_select) < 1 || Integer.valueOf(input_select) > store.getSizeCategory()) {
                            System.out.println("Invalid Input");
                            continue;
                        } else {
                            //display #3
                            System.out.print("============ ");
                            System.out.print(store.getCategory(Integer.valueOf(input_select)).getName());
                            System.out.println(" ============");
                            System.out.println("#\tName\t\tPrice (฿)\t\tQuantity");
                            store.showProduct(Integer.valueOf(input_select));
                            System.out.println("================================");
                            for (; ; ) {
                                System.out.println("Press Q to Exit");
                                input_select = keyboard.next();
                                if (input_select.equals("Q") || input_select.equals("q")) {
                                    break;
                                }
                            }
                        }
                    }catch (Exception e){
                        continue;
                    }
                }
            }else if (input_select.equals("2")) {
                //display #exit
                System.out.println("===== SE Store =====\t\t\t\n" + //
                        "Thank you for using our service :3");
                break;
            }
        }
    }
}
