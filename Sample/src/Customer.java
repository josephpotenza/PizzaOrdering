
import java.sql.ResultSet;
import java.util.Scanner;


public class Customer {

    private String firstName;
    private String lastName;
    private String phone;
    private String email;
    private String pass;
    private String creditCard;
    private String valid ="";
    //private String validCC ="";

    private boolean login = false;


    Scanner read = new Scanner(System.in);


    public Customer() {

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }


    public void createAccount(){

        System.out.print("First Name:");
        firstName  = read.next();

        System.out.print("Last Name:");
        lastName = read.next();

        System.out.print("Password:");
        pass = read.next();

        System.out.print("Phone #");
        phone = read.next();
        validPhone(phone);

        System.out.print("Email");
        email = read.next();
        validEmail(email);

        System.out.print("Enter credit card number");
        creditCard = read.next();
        validCreditCard(creditCard);


     //String SQL = "Insert INTO customers(LastName, FirstName, phone, email) VALUES ('"+firstName+"','"+lastName+"','"+phone+"','"+email+"')";
        //Database.executeQuery(SQL);


    }

    public boolean validEmail( String email){

        //checks to see if email has @, .com , and no spaces in the email.
        while (!email.contains("@") || !email.contains(".com") || email.contains(" ")) {
            return false;
        }
        return true;

    }

    public void validPhone(String phone) {
        //checks to see if phone is larger than 11 digits or less than 9 digits. just in case user adds a 1 in the beginning.
        //ex. 1 718
        while (phone.length() > 11 || phone.length() < 9 ) {
            System.out.println("phone number is not valid. Enter it again: ");
            phone = read.next();
        }

    }

    public boolean validPass(String pass){
        while(pass.length() < 6){
            return false;
        }
        return true;
    }

    public boolean validName(String name){

        while (name.matches(".*\\d+.*")){
            return false;
        }
        return true;
    }

    public void validCreditCard(String cc){

        int[] ints = new int[cc.length()];
        while(true) {
            for (int i = 0; i < cc.length(); i++) {
                ints[i] = Integer.parseInt(cc.substring(i, i + 1));
            }
            for (int i = ints.length - 2; i >= 0; i = i - 2) {
                int j = ints[i];
                j = j * 2;
                if (j > 9) {
                    j = j % 10 + 1;
                }
                ints[i] = j;
            }
            int sum = 0;
            for (int i = 0; i < ints.length; i++) {
                sum += ints[i];
            }
            if (sum % 10 == 0) {

                System.out.println(cc + " is valid credit card number. ");
                    break;

            } else {
                System.out.println(cc + " is invalid credit card number. ");
                    break;


            }

        }

    }


    public void getCustomerInfo(String firstName_){
        //access database and save all information that exist for customer with name firstName_
    }

}
