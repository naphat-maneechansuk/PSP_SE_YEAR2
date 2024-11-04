package Home2;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main2 {
    public static void main(String[] args) throws IOException {
        for(;;){
        File f = new File("C:\\Users\\naphat\\Downloads\\PRODUCT.txt");
        Scanner keyboard = new Scanner(System.in);
        ArrayList<String> products = new ArrayList<>();
        System.out.println("===== SE Store =====");
        System.out.println("1. Show PRODUCT");
        System.out.println("2. Exit");
        System.out.println("====================");
        char input_select = keyboard.next().charAt(0);

        if(input_select == '1') {
                Scanner file = new Scanner(f);
                System.out.println("=========== SE Store's Products ============");
                System.out.println("#\tName\t\tPrice\tQuantity");
                while (file.hasNextLine()) { 
                    String id = file.next();
                    String name = file.nextLine();
                    String line = name;
                    products.add(line); 
                    }
                for(int i = 0; i < products.size()-1; i++) {
                    System.out.println(i+1 + products.get(i));
                }
            
    }else if(input_select == '2') {
        System.out.println("===== SE Store =====\t\t\t\n" + //
                        "\tThank you for using our service :3");
        break;
    }
}
}
}

