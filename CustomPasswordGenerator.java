import java.security.SecureRandom;

public class CustomPasswordGenerator {

    // ตัวอักษรภาษาอังกฤษพิมพ์เล็กและพิมพ์ใหญ่
    private static final String charLowercase = "abcdefghijklmnopqrstuvwxyz";
    private static final String charUppercase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String number = "0123456789";

    private static final String passwordString = charLowercase + charUppercase;
    private static final SecureRandom random = new SecureRandom();

    public static void main(String[] args) {
        // สร้างรหัสผ่านตามรูปแบบที่กำหนด
        String password = generateCustomPassword();
        System.out.println("รหัสผ่านที่สร้างขึ้น: " + password);
    }

    // ฟังก์ชันสร้างรหัสผ่านแบบกำหนดเอง
    public static String generateCustomPassword() {
        // สร้างตัวแปรสำหรับเก็บรหัสผ่าน ขนาด 19 อักขระ
        char[] password = new char[19];

        // สุ่มตัวอักษรภาษาอังกฤษในตำแหน่ง 0, 1, 3, 4, 5, 7, 8, 11, 12, 17, 18
        int[] letterIndices = {0, 1, 3, 4, 5, 7, 8, 11, 12, 17, 18};
        for (int index : letterIndices) {
            password[index] = randomCharacter();
        }

        // สุ่มตัวเลขในตำแหน่ง 9, 10, 13, 14, 15, 16 (รหัสผ่าน 6 หลัก)
        int[] digitIndices = {9, 10, 13, 14, 15, 16};
        for (int index : digitIndices) {
            password[index] = randomDigit();
        }

        // กำหนดสถานะบัญชีที่ Index 2 ให้เป็น "1" (พร้อมใช้งาน)
        password[2] = '1';

        // กำหนด Role ที่ Index 6 ให้เป็น "1" (Regular Member)
        password[6] = '1';

        // แปลงเป็น String และคืนค่า
        return new String(password);
    }

    // ฟังก์ชันสุ่มตัวอักษร (พิมพ์เล็กหรือพิมพ์ใหญ่)
    private static char randomCharacter() {
        int index = random.nextInt(passwordString.length());
        return passwordString.charAt(index);
    }

    // ฟังก์ชันสุ่มตัวเลข
    private static char randomDigit() {
        int index = random.nextInt(number.length());
        return number.charAt(index);
    }
}
