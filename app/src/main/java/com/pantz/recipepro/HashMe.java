/**
 * @author Anastasios Pantzartzis
 * this class is responsible to encrypt
 * passwords
 * Algorithm: shhh be quiet (¬‿¬)
 */

package com.pantz.recipepro;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;



public class HashMe {


    private String passwordToHash;


    public HashMe(String passwordToHash) {
        this.passwordToHash = passwordToHash;
    }


    public String getPasswordToHash() {
        return passwordToHash;
    }


    /**
     * In order to hash the given password : Collaboration with MainActivity.java & ActivityLogin.java
     * @author Anastasios Pantzartzis
     * @param passwordToHash --> Given password
     * @return --> Hashed password value
     * @throws NoSuchAlgorithmException --> exception
     */
    public String theHasher(String passwordToHash) throws NoSuchAlgorithmException {

        //The encryption algorithm is SHA-256
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        messageDigest.update(passwordToHash.getBytes());

        byte [] digest = messageDigest.digest();
        StringBuffer stringBuffer = new StringBuffer();

        for(byte theByte: digest){

            stringBuffer.append(String.format("%02x", theByte & 0xff));

        }


        return stringBuffer.toString();
    }


}



