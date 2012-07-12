/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utilitarios;

 import java.security.MessageDigest;
 import java.security.NoSuchAlgorithmException;

 import sun.misc.BASE64Encoder;

 /**
07 * @author
08 */
 public class EncriptaSenha {
 public static String encripta (String senha) {
 try {
 MessageDigest digest = MessageDigest.getInstance("MD5");
 digest.update(senha.getBytes());
 BASE64Encoder encoder = new BASE64Encoder ();
 return encoder.encode (digest.digest ());
 } catch (NoSuchAlgorithmException ns) {
 ns.printStackTrace ();
 return senha;
 }
 }
 }