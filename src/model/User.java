package model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User {

    private int userID;
    private String role;
    private String username;
    private String password;
    private String email;
    private String phoneNumber;

    //constructors
    public User() {
    }
    public User(int userID, String role, String username, String password, String email, String phoneNumber) {
        this.userID = userID;
        this.role = role;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    public User(String role, String username, String password, String email, String phoneNumber) {
        this.role = role;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    //getters
    public int getUserID() {
        return userID;
    }
    public String getRole() {
        return role;
    }
    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }

    //setters
    public void setUserID(int userID) {
        this.userID = userID;
    }
    public void setRole(String role) {
        this.role = role;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    //method
    public void encrypt() {

        String encryptedPassword = null;

        try {

            MessageDigest m = MessageDigest.getInstance("MD5");

            m.update(this.password.getBytes());

            byte[] bytes = m.digest();

            StringBuilder s = new StringBuilder();
            for (byte aByte : bytes) s.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));

            encryptedPassword = s.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        this.password = encryptedPassword;
    }
}