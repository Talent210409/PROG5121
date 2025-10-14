/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package userregistration;

/**
 *
 * @author RC_Student_Lab
 */
public class UserRegistration {

    private String username;
    private String password;
    private String cellNumber;
    private String firstName;
    private String lastName;

    public UserRegistration() {
        
    }

    
    public void setUserFullName(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    
    public boolean checkUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    
    public boolean checkPasswordComplexity(String password) {
        boolean hasUpper = password.matches(".*[A-Z].*");
        boolean hasDigit = password.matches(".*[0-9].*");
        boolean hasSpecial = password.matches(".*[^a-zA-Z0-9].*");
        return password.length() >= 8 && hasUpper && hasDigit && hasSpecial;
    }

    
    public boolean checkCellPhoneNumber(String cellNumber) {
        return cellNumber.matches("\\+27\\d{9}");
    }

   
    public String registerUser(String username, String password, String cellNumber) {
        if (!checkUserName(username)) {
            return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        }
        if (!checkPasswordComplexity(password)) {
            return "Password is not correctly formatted, please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        }
        if (!checkCellPhoneNumber(cellNumber)) {
            return "Cell phone number is incorrectly formatted or does not contain international code.";
        }

        this.username = username;
        this.password = password;
        this.cellNumber = cellNumber;

        return "Username successfully captured.\nPassword successfully captured.\nCell phone number successfully added.";
    }

    
    public boolean loginUser(String username, String password) {
        return this.username != null && this.username.equals(username) && this.password.equals(password);
    }

   
    public String returnLoginStatus(String username, String password) {
        if (loginUser(username, password)) {
            return "Welcome " + firstName + " " + lastName + ", it is great to see you again.";
        } else {
            return "Username or password incorrect, please try again.";
        }
    }
    
}
