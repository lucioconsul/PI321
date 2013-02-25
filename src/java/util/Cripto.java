/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author user
 */
public class Cripto {
     public static String criptoGraf(String senha) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");

            md.update(senha.getBytes());
            BigInteger hash = new BigInteger(1, md.digest());
            senha = hash.toString(16);
        } catch (NoSuchAlgorithmException ns) {
            ns.printStackTrace();
        }
        return senha;
    }
     
  //   public static void main(String[] args) {
  //      String senha="senhateste";
      //   System.out.println(criptoGraf(senha)); 
 //   }
}
