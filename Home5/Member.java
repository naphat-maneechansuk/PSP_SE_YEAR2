package Home5;

import java.io.PrintStream;
import java.util.ArrayList;

public class Member {
    private String id ;
    private String firstname;
    private String lastname;
    private String email;
    private ArrayList<Character> password = new ArrayList<>();
    private String phone;
    private double point;

    public Member(){}
    public Member(String id,String firstname,String lastname,String email,String password,String phone ,String point){
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.point = Double.parseDouble(point);
        for (int i = 0; i < password.length(); i++) {
            this.password.add(password.charAt(i));
        }
    }
    //เช็คสถานะว่าอีเมลนี้หมดอายุรึยัง
    public boolean checkStatus(){
        if (this.password.get(2) == '1'){
            return true;
        }else{
            return false;
        }
    }
    public String checkRole() {
        char role = this.password.get(6);  // เอาค่าที่ตำแหน่งที่ 6 ของ password
        switch (role) {
            case '0':
                return "Staff";
            case '1':
                return "Regular";
            case '2':
                return "Silver";
            case '3':
                return "Gold";
            default:
                return null;
        }
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        for (int i = 0; i < password.length(); i++) {
            this.password.add(password.charAt(i));
        }
    }
    public String getId() {
        return id;
    }
    public String getFirstname() {
        return firstname;
    }
    public String getLastname() {
        return lastname;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    //คืนค่ารหัสผ่านเเบบเต็มๆ
    public String getPassword() {
        String passwordtostring = "";
        for (int i = 0; i < this.password.size(); i++) {
            passwordtostring += this.password.get(i);
        }
        return passwordtostring;
    }

    public String getPhone() {
        return phone;
    }
    //คืนค่ารหัสผ่านเเบบมีขีด "-" ด้วย
    public String getFullPhone() {
        return phone.substring(0,3)+"-"+phone.substring(3,6)+"-"+phone.substring(6,10);
    }
    public double getPoint() {
        return point;
    }
    //เเสดงอีเมลเเบบเซนเซอร์
    public String getCensorEmail(){
        String emailSensor = "";
        String splitEmail[] = email.split("@");
        String face = splitEmail[0];
        String back = splitEmail[1];
        String emailFace = "";
        String emailBack = "";
        emailFace += face.substring(0,2);
        emailFace += "***";
        emailBack += back.substring(0,2);
        emailBack += "***";
        emailSensor += emailFace+"@"+emailBack;
        return emailSensor;
    }
    //เเสดงรหัสผ่านเเค่ 6 ตัวที่เป็นรหัสจริง ๆ
    public String getTruePassword(){
        String truePassword = "";
        for (int i = 0; i < getPassword().length(); i++) {
            if (i==9||i==10||i==13||i==14||i==15||i==16){
                truePassword += getPassword().charAt(i);
            }
        }
        return truePassword;
    }
    public void toStringMember() {
        // จัดรูปแบบข้อมูลสมาชิกแต่ละคน
        System.out.printf("%-15s %-15s %-25s %-15s %-10.2f\n", this.firstname, this.lastname, this.email, getFullPhone(), this.point);
    }

}
