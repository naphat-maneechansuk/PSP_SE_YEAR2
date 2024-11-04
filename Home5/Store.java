package Home5;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Store {

    private ArrayList<Product> products = new ArrayList<>();
    private ArrayList<Category> categories = new ArrayList<>();
    private ArrayList<Member> members = new ArrayList<>();
    private ArrayList<CART> carts = new ArrayList<>();
    public void random(){

    }




    //CART--------------------------------------------------------------------------------------------------------------------------
    //เพิ่มสินค้าในตะกร้า
    public void addCart(CART cart) {
        carts.add(cart);
    }
    //อ่านไฟล์ ตะกร้า เเล้วเก็บไว้ใน store
    public void addAllCart(File fileCart) throws FileNotFoundException {
    Scanner file = new Scanner(fileCart);
    while (file.hasNextLine()){
        String[] data = file.nextLine().split("\t");
        addCart(new CART(data[0], data[1], data[2]));
        }
    }
    //เช็คว่ามีสินค้านี้ในตะกร้าหรือยัง
    public CART checkCart(String idMember, String idProduct) {
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getIdMember().equals(idMember) && carts.get(i).getIdProduct().equals(idProduct)) {
                return carts.get(i);
            }
        }
        return null;
    }
    //จัดการสิ่งต่าง ๆ ตะกร้า
    public void basket(Store store, String email , String indexOfProduct, String quantity) {
        String idMember = store.getMemberEmail(email).getId();
        String idProduct = store.products.get(Integer.parseInt(indexOfProduct) - 1).getId();
        int quantityProduct = Integer.parseInt(quantity);
        CART cart = store.checkCart(idMember, idProduct);
        //เช็คว่าสินค้าในตระกร้ามีมากกว่าในคลังหรือไม่่
        boolean checkOutOfStock = true;
        if (quantityProduct > store.products.get(Integer.parseInt(indexOfProduct) - 1).getQuantity()) {
            checkOutOfStock = false;
        }else if (cart != null && store.checkCart(idMember,idProduct).getQuantity()+quantityProduct > store.products.get(Integer.parseInt(indexOfProduct) - 1).getQuantity()) {
            checkOutOfStock = false;
        }
        //System.out.println(checkOutOfStock);
        if (cart == null){
           // System.out.println("*1");
            if (checkOutOfStock){
                store.addCart(new CART(idMember, idProduct, quantity));
            }else {
                System.out.println("Product is not enough...!");
            }
        }else {
            //System.out.println("*2");
            if (quantity.charAt(0) == '+') {
               // System.out.println("**1");
                if (checkOutOfStock) {
                    store.carts.get(store.carts.indexOf(cart)).setQuantity(store.carts.get(store.carts.indexOf(cart)).getQuantity() + quantityProduct);
                }else {
                    System.out.println("Product is not enough...!");
                }
            }else if (quantity.charAt(0) == '-') {
                //System.out.println("**2");
                store.carts.get(store.carts.indexOf(cart)).setQuantity(store.carts.get(store.carts.indexOf(cart)).getQuantity() + quantityProduct);
            }else {
               // System.out.println("**3");
                if (checkOutOfStock) {
                    store.carts.get(store.carts.indexOf(cart)).setQuantity(quantityProduct);
                }
            }
        }
        //ลบสินค้าที่มีจำนวนน้อยกว่าหรือเท่ากับ 0
        for (int i = 0; i < store.carts.size(); i++) {
            if (store.carts.get(i).getQuantity() <= 0) {
                store.carts.remove(i);
            }
        }
    }
    //End CART--------------------------------------------------------------------------------------------------------------------------
    //Category--------------------------------------------------------------------------------------------------------------------------
    //เพิ่ม category
    public void addCategory(Category category) {
        categories.add(category);
    }

    //อ่านไฟล์  category เเล้วเก็บไว้ใน store
    public void addAllCategory(File fileCategoly) throws IOException {
        Scanner file = new Scanner(fileCategoly);
        while (file.hasNextLine()){
            String[] data = file.nextLine().split("\t");
            addCategory(new Category(data[0], data[1]));
        }
    }

    //คืนค่าจำนวนรายการ category ทั้งหมด
    public int getSizeCategory() {
        return categories.size();
    }

    //คืน data ของ category ด้วยตำเเหน่ง
    public Category getCategory(int index) {
        return categories.get(index - 1);
    }

    // โช category ทั้งหมด
    public void showAllCategory() {
        for (int i = 0; i < categories.size(); i++) {
            System.out.println(i + 1 + "\t"+categories.get(i).getName());
        }
    }
    //End Category--------------------------------------------------------------------------------------------------------------------------

    //PRODUCT--------------------------------------------------------------------------------------------------------------------------
    //เพิ่ม product
    public void addProduct(Product product) {
        products.add(product);
    }

    // อ่านไฟล์ product เเล้วเก็บไว้ใน store
    public void addAllProduct(File fileProduct) throws FileNotFoundException {
        Scanner file = new Scanner(fileProduct);
        while (file.hasNextLine()){
            String[] data = file.nextLine().split("\t");
            addProduct(new Product(data[0], data[1], data[2], data[3], data[4]));
        }
    }

    //โช product ด้วยตำเเหน่ง
    public void showNormalProduct(Store store, int index, String role) {
        // Print headers with proper formatting
        System.out.printf("%-3s %-15s %-19s %-15s\n", "#", "Name", "Price (฿)", "Quantity");
        int number = 1; // Initialize item number
        for (int i = 0; i < store.products.size(); i++) {
            if (store.products.get(i).getType().equals(store.categories.get(index - 1).getId())) {
                System.out.printf("%-4d", number); // Print item number
                store.products.get(i).toStringProduct(role); // Print product details
                number++; // Increment item number
            }
        }
    }

    public void showAllProduct(Store store, String role) {
        // Print table header
        System.out.printf("%-3s %-15s %-19s %-15s\n", "#", "Name", "Price (฿)", "Quantity");
        int number = 1;
        // Iterate through the products
        for (int i = 0; i < store.products.size(); i++) {
            System.out.printf("%-4d", number); // Print product number
            store.products.get(i).toStringProduct(role); // Call method to print product details
            number++; // Increment product number
        }
    }
    public void productDESC(Store store , int index, String role){
        ArrayList<Product> productDESC = new ArrayList<>(store.products);
        productDESC.sort(Comparator.comparing(Product::getName).reversed());
        System.out.printf("%-3s %-15s %-19s %-15s\n", "#", "Name", "Price (฿)", "Quantity");
        int number = 1;
        for (int i = 0; i < productDESC.size(); i++) {
            if (productDESC.get(i).getType().equals(store.categories.get(index - 1).getId())) {
                System.out.printf("%-4d",number);
                productDESC.get(i).toStringProduct(role);
                number++;
            }
        }
    }
    public void  productASC(Store store ,int index,String role){
        ArrayList<Product> productASC = new ArrayList<>(store.products);
        productASC.sort(Comparator.comparing(Product::getQuantity));
        System.out.printf("%-3s %-15s %-19s %-15s\n", "#", "Name", "Price (฿)", "Quantity");
        int number = 1;
        for (int i = 0; i < productASC.size(); i++) {
            if (productASC.get(i).getType().equals(store.categories.get(index - 1).getId())) {
                System.out.printf("%-4d",number);
                productASC.get(i).toStringProduct(role);
                number++;
            }
        }
    }
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    //โชกรณีเเก้ไขสินค้าสำเร็จ
    public void editProduct(Store store,int index,String name,String quantity) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter("D:\\java\\Data_1\\Test\\src\\Home5\\PRODUCT.txt"));
        String oldName = store.products.get(index - 1).getName();
        boolean check = true;
        if (!quantity.equals("-")) {
            if (quantity.charAt(0) == '+' || quantity.charAt(0) == '-') {
                if (isInteger(quantity)) {
                    store.products.get(index - 1).setQuantity(String.valueOf(store.products.get(index - 1).getQuantity() + Integer.parseInt(quantity)));
                } else {
                    check = false;
                }
            }else {
                check = false;
            }
        }if (!name.equals("-")&&check) {
                store.products.get(index - 1).setName(name);
        }
        if (check) {
            displayEditProductrSuccess(oldName, name, quantity);
        } else {
            displayEditProductIncorrect(oldName,name, quantity);
        }
        for (int i = 0; i < store.products.size(); i++) {
            writer.println(store.products.get(i).getId() + "\t" + store.products.get(i).getName() + "\t" + store.products.get(i).getPrice() + "\t" +
                    store.products.get(i).getQuantity() + "\t" + store.products.get(i).getType());
        }
        writer.close();
    }

    //End PRODUCT--------------------------------------------------------------------------------------------------------------------------

    //Member--------------------------------------------------------------------------------------------------------------------------
    //เพิ่ม member
    public void addMember(String id, String firstname, String lastname, String email, String password, String phone, String point) {
        members.add(new Member(id, firstname, lastname, email, password, phone, point));
    }

    // อ่านไฟล์ member เเล้วเก็บไว้ใน store
    public void addAllMember(File fileMember) throws FileNotFoundException {
        Scanner file = new Scanner(fileMember);
        while (file.hasNextLine()){
            String[] data = file.nextLine().split("\t");
            addMember(data[0], data[1], data[2], data[3], data[4], data[5], data[6]);
        }
    }

    //หา member ด้วยอีเมล
    public Member getMemberEmail(String email) {
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getEmail().equals(email)) {
                return members.get(i);
            }
        }
        return null;
    }
    public Member getMemberIndex(int index) {
        return members.get(index);
    }

    //เช็คว่ารหัสเเละอีเมลที่รับมาอยู่ในสถานะไหน
    /*
     * รหัสถูกคืนค่า 2
     * รหัส+ยังไม่หมดอายุ คืนค่า 1
     * รหัสหรืออีเมลผิด คืนค่า 0
     */
    public int checktypeMember(String email, String password) {
        int numcheck = 0;
        String password1 = "";
        //ลูปกรณีที่รับมาเป็นรหัสเต็ม จะกรองเอาเเค่รหัส ุ ตัว
        for (int i = 0; i < password.length(); i++) {
            if (i == 9 || i == 10 || i == 13 || i == 14 || i == 15 || i == 16) {
                password1 += password.charAt(i);
            }
        }
        for (int i = 0; i < members.size(); i++) {
            if (members.get(i).getEmail().equals(email) && (members.get(i).getTruePassword().equals(password1) || members.get(i).getTruePassword().equals(password))) {
                numcheck = 2;
                if (members.get(i).checkStatus()) {
                    numcheck = 1;
                }
            }
        }
        return numcheck;
    }

    //โชเมื่อถูกต้องทั้งหมด
    public void showInfoMember(String email) {
            if (getMemberEmail(email).getEmail().equals(email)) {
                System.out.print("Hello, ");
                System.out.println(getMemberEmail(email).getLastname().charAt(0) + ". " + getMemberEmail(email).getFirstname()+" ("+ getMemberEmail(email).checkRole()+")");
                System.out.print("Email: ");
                System.out.println(getMemberEmail(email).getCensorEmail());
                System.out.print("Phone: ");
                System.out.println(getMemberEmail(email).getFullPhone());
                System.out.print("You have ");
                System.out.printf("%.0f", getMemberEmail(email).getPoint());
                System.out.println(" Point");
                System.out.println("====================");
        }
    }

    public void showAllMember(Store store) {
        System.out.printf("%-3s %-15s %-15s %-25s %-15s %-10s\n", "#", "First Name", "Last Name", "Email", "Phone", "Points");
        for (int i = 0; i < members.size(); i++) {
            System.out.printf("%-4d", i + 1);
            store.members.get(i).toStringMember();
        }
    }
    //edit member
    public void editMember(Store store,int index,String firstname, String lastname, String email, String phone) throws IOException {
        String oldName = store.getMemberIndex(index - 1).getFirstname() + " " + store.getMemberIndex(index - 1).getLastname();
        int checkAll[] = {1,1,1,1};
        if (!firstname.equals("-")){
            if (!(firstname.length() > 2)) {
                checkAll[0] = 0;
            }
        }
        if (!lastname.equals("-")){
            if (!(lastname.length() > 2)) {
                checkAll[1] = 0;
            }
        }
        if (!email.equals("-")){
            if (!(email.length() > 2)) {
                checkAll[2] = 0;
            }int count = 0;
            for (int i = 0; i < email.length(); i++) {
                if (email.charAt(i) == '@') {
                    count++;
                }
            }if (count != 1) {
                checkAll[2] =0;
            }
        }
        if (!phone.equals("-")){
            if (!(phone.length() == 10 )) {
                checkAll[3] = 0;
            }for (int i = 0; i < phone.length(); i++) {
                if (phone.charAt(i) < '0' || phone.charAt(i) > '9') {
                    checkAll[3] = 0;
                }
            }
        }

        if (checkAll[0] == 1 && checkAll[1] == 1 && checkAll[2] == 1 && checkAll[3] == 1) {
            if (!firstname.equals("-")) {
                store.getMemberIndex(index - 1).setFirstname(firstname);
            }if (!lastname.equals("-")) {
                store.getMemberIndex(index - 1).setLastname(lastname);
            }if (!email.equals("-")) {
                store.getMemberIndex(index - 1).setEmail(email);
            }if (!phone.equals("-")) {
                store.getMemberIndex(index - 1).setPhone(phone);
            }
            displayEditMemberSuccess(oldName, firstname, lastname, email, phone);
        } else {
                displayEditMemberIncorrect(firstname, lastname, email, phone);
            }
        PrintWriter writer = new PrintWriter(new FileWriter("D:\\java\\Data_1\\Test\\src\\Home5\\MEMBER.txt"));
        for (int i = 0; i < store.members.size(); i++) {
            writer.println(store.getMemberIndex(i).getId() + "\t" + store.getMemberIndex(i).getFirstname() + "\t" + store.getMemberIndex(i).getLastname() + "\t" +
                    store.getMemberIndex(i).getEmail() + "\t" + store.getMemberIndex(i).getPassword() + "\t" + store.getMemberIndex(i).getPhone() + "\t" +
                    String.format("%.2f", store.getMemberIndex(i).getPoint()));
        }
        writer.close();
    }

    //เพิ่มข้อมูลสมาชิกใน file
    public void addwriteMember(Store store,String firstname, String lastname, String email, String phone) throws IOException {
        RandomPassword randomPassword = new RandomPassword();
        int idToInteger = Integer.parseInt(store.getMemberIndex(store.members.size()-1).getId())+1;
        String idToSring = String.valueOf(idToInteger);
        int checkall[] = {1,1,1,1};
        String randomMember = randomPassword.generateCustomPassword();
        int count = 0;
        if (!firstname.equals("-")){
            if (!(firstname.length() > 2)) {
                checkall[0] = 0;
            }
        }
        if (!lastname.equals("-")){
            if (!(lastname.length() > 2)) {
                checkall[1] = 0;
            }
        }
        if (!email.equals("-")){
            if (!(email.length() > 2)) {
                checkall[2] = 0;
            }
            for (int i = 0; i < email.length(); i++) {
                if (email.charAt(i) == '@') {
                    count++;
                }
            }if (count != 1) {
                checkall[2] =0;
            }
        }
        if (!phone.equals("-")){
            if (!(phone.length() == 10)) {
                checkall[3] = 0;
            }for (int i = 0; i < phone.length(); i++) {
                if (phone.charAt(i) < '0' || phone.charAt(i) > '9') {
                    checkall[3] = 0;
                }
            }
        }
        if (checkall[0] == 1 && checkall[1] == 1 && checkall[2] == 1 && checkall[3] == 1) {
            store.addMember(idToSring, firstname, lastname, email, randomMember, phone, "0.00");

            displayAddMemberCorrect(store.getMemberIndex(store.members.size()-1));
        }else {
            displayAddMemberIncorrect(firstname, lastname, email, phone);
        }
        PrintWriter writer = new PrintWriter(new FileWriter("D:\\java\\Data_1\\Test\\src\\Home5\\MEMBER.txt"));
        for (int i = 0; i < store.members.size(); i++) {
            writer.println(store.getMemberIndex(i).getId() + "\t" + store.getMemberIndex(i).getFirstname() + "\t" + store.getMemberIndex(i).getLastname() + "\t" +
                    store.getMemberIndex(i).getEmail() + "\t" + store.getMemberIndex(i).getPassword() + "\t" + store.getMemberIndex(i).getPhone() + "\t" +
                    String.format("%.2f", store.getMemberIndex(i).getPoint()));
        }
        writer.close();
    }

    //MemberEnd--------------------------------------------------------------------------------------------------------------------------

    //Store--------------------------------------------------------------------------------------------------------------------------
    public void playStore() throws IOException {
        for (;;){
            File fileCategoly = new File("D:\\java\\Data_1\\Test\\src\\Home5\\CATEGORY.txt");
            File fileProduct = new File("D:\\java\\Data_1\\Test\\src\\Home5\\PRODUCT.txt");
            File fileMember = new File("D:\\java\\Data_1\\Test\\src\\Home5\\MEMBER.txt");
            Scanner keyboard = new Scanner(System.in);
            //display #1
            Store store = new Store();
            store.display1();
            String input_select = keyboard.next();
            //เลือก1
            if (input_select.equals("1")){
                //อ่านไฟล์  category เเล้วเก็บไว้ใน store
                store.addAllCategory(fileCategoly);
                // อ่านไฟล์ product เเล้วเก็บไว้ใน store
                store.addAllProduct(fileProduct);
                // อ่านไฟล์ member เเล้วเก็บไว้ใน store
                store.addAllMember(fileMember);
                //**************
                store.display2(store);
            }else if (input_select.equals("2")) {
                //display #exit
                System.out.println("===== SE Store =====\t\t\t\n" + //
                        "Thank you for using our service :3");
                break;
            }
        }
    }

    //display1
    public void display1(){
        System.out.println("===== SE Store =====");
        System.out.println("1. Login");
        System.out.println("2. Exit");
        System.out.println("====================");
        System.out.print("Select (1-2) : ");
    }
    //display #2
    public void display2(Store store) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        String input_select;
        int numCheckOutput = 0;
        for (;;) {//display#2
            String email;
            System.out.print("===== LOGIN =====\n");
            System.out.print("Email : ");
            email = keyboard.next();
            System.out.print("Password : ");
            String password = keyboard.next();
            //เงื่อนไขที่ถูกต้องหมดเเละรหัสไม่หมดอายุ
            if (store.checktypeMember(email,password)==1){

                if (store.getMemberEmail(email).checkRole().equals("Staff")) {
                    //******************
                    store.display3_Staff(store,email);
                }else {
                    //******************
                    store.display3_Member(store,email);
                }
                numCheckOutput =-1;
            }else if (store.checktypeMember(email,password)==2){
                numCheckOutput=-1;
                //เงื่อนไขหมดอายุ display #3.2
                //**************
                store.displayMemberExpired(store,email);

            }else {//display #4.1
                //เงื่อนไขใส่รหัสหรืออีเมลผิด
                numCheckOutput++;
                System.out.println("===== LOGIN =====");
                System.out.println("Email : "+email);
                System.out.println("Password : "+password);
                System.out.println("====================");
                System.out.println("Error! - Email or Password is Incorrect "+"("+numCheckOutput+")");
                if (numCheckOutput==3){//display #4.2
                    System.out.println("Sorry, Please try again later :(");
                    numCheckOutput=-1;
                }
            }
            if (numCheckOutput==-1){
                break;
            }
        }
    }

    //display3 #staff
    public void display3_Staff(Store store , String email) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        String input_select;
        for (;;) {//display #3.1
            System.out.println("===== SE STORE =====");
            store.showInfoMember(email);
            System.out.print("1. Show Category\n" +
                    "2. Add Member\n"+
                    "3. Edit Member\n" +
                    "4. Edit Product\n"+
                    "5. Logout\n" +
                    "====================\n" +
                    "Select (1-4) : ");
            input_select = keyboard.next();
            if (input_select.equals("1")) {// input 1
                //displayCategory
                //**************
                store.displayCategory(store,store.getMemberEmail(email).checkRole());
            } else if (input_select.equals("2")) {
                store.displayAddMember(store);
            }else if (input_select.equals("3")){
                //display #editMember
                //**************
                store.displayShowAllMemberToEdit(store);
            }else if (input_select.equals("4")){
                //display #editProduct
                //**************
                store.displayShowAllProduct(store);
            }else if (input_select.equals("5")){
                break;
            }
        }
    }

    //display3 # Member
    public void display3_Member(Store store , String email) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        String input_select;
        for (;;) {//display #3.1
            System.out.println("===== SE STORE =====");
            store.showInfoMember(email);
            System.out.print("1. Show Category\n" +
                    "2. Order Product\n" +
                    "3. Logout\n" +
                    "====================\n" +
                    "Select (1-3) : ");
            input_select = keyboard.next();
            if (input_select.equals("1")) {// input 1
                //displayCategory
                //**************
                store.displayCategory(store,store.getMemberEmail(email).checkRole());

            } else if (input_select.equals("2")) {
                //display #orderProduct
                //**************
                store.displayOrderProduct(store,email);
            }else if (input_select.equals("3")) {
                break;
            }
        }
    }

    //โชคำสั่งหมวดหมู่สินค้า
    public void displayCategory(Store store, String role) {
        Scanner keyboard = new Scanner(System.in);
        String input_select;
        for (;;) {
            try {//display #category
                System.out.println("===== SE Store's Product Categories =====\t");
                System.out.println("#\tCategory\t");
                store.showAllCategory();
                System.out.println("=========================================");
                System.out.print("Select Category to Show Product (1-");
                System.out.print(store.getSizeCategory());
                System.out.println(") or Q for exit");
                System.out.print("Select : ");
                input_select = keyboard.next();
                if (input_select.equals("Q") || input_select.equals("q")) {
                    break;
                } else if (Integer.parseInt(input_select) < 1 || Integer.parseInt(input_select) > store.getSizeCategory()) {
                    System.out.println("Invalid Input");
                    continue;
                } else {
                    //display #Product
                    //**************
                    store.displayProduct(store,input_select,role,0);
                }
            } catch (Exception e) {
                continue;
            }
        }
    }

    //โชสินค้า
    public void displayProduct(Store store, String input_select, String role, int show) {
        Scanner keyboard = new Scanner(System.in);
        for (; ; ) {
            try {
                System.out.print("============ ");
                System.out.print(store.getCategory(Integer.parseInt(input_select)).getName());
                System.out.println(" ============");
                // ตรวจสอบค่า `show` เพื่อแสดงข้อมูลตามลำดับที่ต้องการ
                if (show == 1) {
                    store.productDESC(store, Integer.parseInt(input_select), role);
                } else if (show == 2) {
                    store.productASC(store, Integer.parseInt(input_select), role);
                } else {
                    store.showNormalProduct(store, Integer.parseInt(input_select), role);
                }

                System.out.println("================================");
                System.out.print("1. Show Name By DESC\n" +
                        "2. Show Quantity By ASC\n" +
                        "or Press Q to Exit : ");
                String input_select1 = keyboard.next();

                // ตรวจสอบการเลือกเมนู
                if (input_select1.equalsIgnoreCase("Q")) {
                    break;
                } else if (input_select1.equals("1")) {
                    // เรียกใช้ฟังก์ชันอีกครั้งเพื่อแสดงผลเรียงตามชื่อแบบ DESC
                    store.displayProduct(store, input_select, role, 1);
                    break;
                } else if (input_select1.equals("2")) {
                    // เรียกใช้ฟังก์ชันอีกครั้งเพื่อแสดงผลเรียงตามจำนวนสินค้าแบบ ASC
                    store.displayProduct(store, input_select, role, 2);
                    break;
                }
            } catch (Exception e) {
                // จับข้อผิดพลาดและวนลูปใหม่
                continue;
            }
        }
    }

    //โชกรณีรหัสหมดอายุ
    public void displayMemberExpired(Store store,String email) {
        System.out.println("===== LOGIN =====");
        System.out.println("Email : " + email);
        System.out.println("Password : " + store.getMemberEmail(email).getTruePassword());
        System.out.println("====================");
        System.out.println("Error! - Your Account are Expired! ");
    }
    //display #addMember
    public void displayAddMember(Store store) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("===== Add Member =====");
        System.out.print("Firstname : ");
        String firstname = keyboard.nextLine();
        System.out.print("Lastname : ");
        String lastname = keyboard.nextLine();
        System.out.print("Email : ");
        String email = keyboard.nextLine();
        System.out.print("Phone : ");
        String phone = keyboard.nextLine();
        store.addwriteMember(store,firstname,lastname,email,phone);
    }
    //display #เพิ่มสมาชิกไม่ถูกต้องหรือเเก้ไขไม่ถูกต้อง
    public void displayAddMemberIncorrect(String firstname, String lastname, String email, String phone){
        System.out.println("===== Add Member =====");
        System.out.println("Firstname : "+firstname);
        System.out.println("Lastname : "+lastname);
        System.out.println("Email : "+email);
        System.out.println("Phone : "+phone);
        System.out.println("Error! - Your Information are Incorrect!");
    }

    public void displayAddMemberCorrect(Member newMember){
        System.out.println("===== Add Member =====");
        System.out.println("Firstname : "+newMember.getFirstname());
        System.out.println("Lastname : "+newMember.getLastname());
        System.out.println("Email : "+newMember.getEmail());
        System.out.println("Phone : "+newMember.getPhone());
        System.out.println("Success - New Member has been created!");
        System.out.println("Apisit's Password is "+newMember.getTruePassword());
    }

    public void displayShowAllMemberToEdit(Store store) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        for (;;) {
            //try {
                String input_select;
                System.out.println("===== SE STORE's Member =====");
                // เรียกฟังก์ชันเพื่อแสดงผลข้อมูลสมาชิก
                store.showAllMember(store);

                System.out.println("================================");
                System.out.println("Type Member Number, You want to edit or Press Q to Exit\t");
                System.out.print("Select (1-" + store.members.size() + ") or Q : ");
                input_select = keyboard.next();

                // ตรวจสอบการเลือกเมนู
                if (input_select.equalsIgnoreCase("Q")) {
                    break;
                } else if (Integer.valueOf(input_select) < 1 || Integer.valueOf(input_select) > store.members.size()) {
                    continue;
                } else {
                    // แสดงหน้าจอแก้ไขสมาชิก
                    store.displayEditMember(store, Integer.valueOf(input_select));
                    break;
                }
            //} catch (Exception e) {
                // จับข้อผิดพลาดและวนลูปใหม่
          //      continue;
           // }
        }
    }
    public void displayEditMember(Store store,int index) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        String firstname;
        String lastname;
        String email;
        String phone;
        System.out.print("====");
        System.out.print(" Edit info of " + store.getMemberIndex(index - 1).getFirstname() + " " + store.getMemberIndex(index - 1).getLastname());
        System.out.println(" ====");
        System.out.println("Type new info or Hyphen (-) for none edit.");
        System.out.print("Firstname : ");
        firstname = keyboard.nextLine();
        System.out.print("Lastname : ");
        lastname = keyboard.nextLine();
        System.out.print("Email : ");
        email = keyboard.nextLine();
        System.out.print("Phone : ");
        phone = keyboard.nextLine();
        store.editMember(store,index,firstname,lastname,email,phone);
    }
    public void displayEditMemberIncorrect(String firstname, String lastname, String email, String phone){
        System.out.println("===== Edit Member =====");
        System.out.println("Firstname : "+firstname);
        System.out.println("Lastname : "+lastname);
        System.out.println("Email : "+email);
        System.out.println("Phone : "+phone);
        System.out.println("Error! - Your Information are Incorrect!");
    }

    public void displayEditMemberSuccess(String oldName, String firstname, String lastname, String email, String phone){
        System.out.println("==== Edit info of "+oldName+" ====");
        System.out.println("Firstname : "+firstname);
        System.out.println("Lastname : "+lastname);
        System.out.println("Email : "+email);
        System.out.println("Phone : "+phone);
        System.out.println("Success - Member info has been updated!");
    }
    public void displayShowAllProduct(Store store) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        for (;;) {
            try {
                String input_select;
                System.out.println("===== SE STORE's Product =====");
                store.showAllProduct(store, "Staff");
                System.out.println("================================");
                System.out.println("Type Product Number, You want to edit or Press Q to Exit");
                System.out.print("Select (1-" + store.products.size() + ") or Q : ");
                input_select = keyboard.next();
                if (input_select.equals("Q") || input_select.equals("q")) {
                    break;
                } else if (Integer.parseInt(input_select) < 1 || Integer.parseInt(input_select) > store.products.size()) {
                    continue;
                } else {
                    // display #editProduct
                    store.displayEditProduct(store, Integer.parseInt(input_select));
                    break;
                }
            } catch (Exception e) {
                continue;
            }
        }
    }

    public void displayEditProduct(Store store,int index) throws IOException {
        Scanner keyboard = new Scanner(System.in);
        String name;
        String quantity;
        System.out.print("====");
        System.out.print(" Edit info of " + store.products.get(index - 1).getName());
        System.out.println(" ====");
        System.out.println("Type new info or Hyphen (-) for none edit.");
        System.out.print("Name : ");
        name = keyboard.nextLine();
        System.out.print("Quantity : ");
        quantity = keyboard.nextLine();
        store.editProduct(store,index,name,quantity);
    }
    public void displayEditProductIncorrect(String oldName, String name, String quantity){
        System.out.println("===== Edit info of "+oldName+" =====");
        System.out.println("Name : "+name);
        System.out.println("Quantity : "+quantity);
        System.out.println("Error! - Your Information are Incorrect!");
    }
    public void displayEditProductrSuccess(String oldName, String name, String quantity){
        System.out.println("==== Edit info of "+oldName+" ====");
        System.out.println("Name : "+name);
        System.out.println("Quantity : "+quantity);
        System.out.println("Success - Product info has been updated!");
    }
    public void displayOrderProduct(Store store,String email) throws IOException {
        // ล้างรถเข็น
        store.carts.clear();
        // อ่านไฟล์ CART เเล้วเก็บไว้ใน store
        File fileCart = new File("D:\\java\\Data_1\\Test\\src\\Home5\\CART.txt");
        store.addAllCart(fileCart);
        Scanner keyboard = new Scanner(System.in);
        String input_select;
        System.out.println("=========== SE STORE's Products ===========");
        store.showAllProduct(store, store.getMemberEmail(email).checkRole());
        System.out.println("===========================================");
        System.out.println("Enter the product number followed by the quantity");
        System.out.println("1. How to Order\n" +
                "2. List Products\n" +
                "Q. Exit");
        for (;;){
            try {
//                for (int i = 0; i < store.carts.size(); i++) {
//                    System.out.println(store.carts.get(i).getIdMember()+" " +store.carts.get(i).getIdProduct()+" "+store.carts.get(i).getQuantity());
//                }
                System.out.print("Enter : ");
                input_select = keyboard.nextLine();
                String input_split[] = input_select.split(" ");
                if (input_split.length == 1) {
                    if (input_select.equalsIgnoreCase("Q")) {
                        System.out.println("Your cart has been saved!");
                        break;
                    } else if (input_select.equals("1")) {
                       store.displayHowToOrder();
                    }else if (input_select.equals("2")) {
                        System.out.println("=========== SE STORE's Products ===========");
                        store.showAllProduct(store, store.getMemberEmail(email).checkRole());
                        System.out.println("===========================================");
                    }
                }else if (input_split.length == 2 && isInteger(input_split[1]) && Integer.parseInt(input_split[0]) > 0 && Integer.parseInt(input_split[0]) <= store.products.size()){
                    store.basket(store, email, input_split[0], input_split[1]);
                }else {
                    System.out.println("Your input is invalid!");
                }
            }catch (Exception e){
                continue;
            }
        }
        PrintWriter writer = new PrintWriter(new FileWriter("D:\\java\\Data_1\\Test\\src\\Home5\\CART.txt"));
        for (int i = 0; i < store.carts.size(); i++) {
            writer.println(store.carts.get(i).getIdMember() + "\t" + store.carts.get(i).getIdProduct() + "\t" + store.carts.get(i).getQuantity());
        }
        writer.close();
    }
    public void displayHowToOrder(){
        System.out.println("Enter : 1\n" +
                "How to Order:\n" +
                "To Add Product: \n" +
                "\tEnter the product number followed by the quantity.\n" +
                "\tExample: 1 50 (Adds 50 chips)\n" +
                "To Adjust Quantity:\n" +
                "\t+ to add more items: 1 +50 (Adds 50 more chips)\n" +
                "\t- to reduce items: 1 -50 (Removes 50 chips)\n");
    }
    //End Store--------------------------------------------------------------------------------------------------------------------------
}

