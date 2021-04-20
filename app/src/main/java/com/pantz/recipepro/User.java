/**
 * This class sets the variables needed for the user in order two create objects,
 * contains two constructors and setters and getters.
 */

package com.pantz.recipepro;

public class User {
    private String username;
    private String password;
    private String repassword;


    //Constructor
    public User(String username, String password, String repassword) {
        this.username = username;
        this.password = password;
        this.repassword = repassword;
    }

    //Empty constructor (might be deleted later)
    public User() {
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
}
