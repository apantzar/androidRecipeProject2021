/**
 * This class sets the variables needed for the user in order two create objects,
 * contains two constructors and setters and getters.
 */

package com.pantz.recipepro;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    private String username;
    private String password;
    private String repassword;


    //Constructor for register activity
    public User(String username, String password, String repassword) {
        this.username = username;
        this.password = password;
        this.repassword = repassword;
    }

   //Constructor for log in activity
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    //Setters and Getters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    //it will be used for printing the contents of the objects
    @Override
    public String toString() {
        return "User{" +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    /*Function that checks if the password is valid. The password must contain:
      1)at least 8 characters,
      2)at least 1 number
      3)at least 1 uppercase letter
      4)at least 1 lowercase letter
      5)on of these symbols: @, #, $, %, ^, &, +, =, !
    */
    public static boolean ValidPassword(final String password) {
        Pattern pattern;
        Matcher matcher;

        int passLength = password.length();

        if(passLength>=8){
            final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[a-z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$";
            pattern = Pattern.compile(PASSWORD_PATTERN);
            matcher = pattern.matcher(password);
        }else{
            return  false;
        }


        return matcher.matches();
    }
}
