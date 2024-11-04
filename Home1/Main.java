package Home1;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner kb = new Scanner(System.in);
        System.out.println("-------------------");
        System.out.print("Enter Text : ");
        String str = kb.nextLine();
        System.out.println("-------------------");
        int numE = 0;
        char arrayStr[] = new char[str.length()];
        for (int i = 0; i < str.length(); i++) {
            arrayStr[i] = str.charAt(i);
        }
        for (int i = 0; i < arrayStr.length; i++) {
            if (arrayStr[i] == 'e'||arrayStr[i] == 'E') {
                arrayStr[i] = '-';
                numE++;
            }
            }
        System.out.println("----------------------------------------------\n" +
                "Text : " + str + "\n" +
                "----------------------------------------------");
        System.out.println("Total number of ’e or E’ in Text = "+ numE);
        System.out.print("New Text = ");
        for (int i = 0; i < arrayStr.length; i++) {
            System.out.print(arrayStr[i]);
        }
        System.out.println("\n----------------------------------------------");
    }
}