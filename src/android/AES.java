package com.gi.encryp_decript;
import android.util.Base64;
import android.util.Log;

import java.net.URLDecoder;
import java.net.URLEncoder;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by praveen on 03-Feb-18.
 */

public class AES {

    private static String keyValue = "A1234&ABCDE/98745#000078";
    private static byte[] sharedvector = {8, 7, 5, 6, 4, 1, 2, 3, 18, 17, 15, 16, 14, 11, 12, 13};

    private String result = "";

    public static String encrypt(String strToEncrypt){
       try {
           Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
           c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyValue.getBytes(), "AES"), new IvParameterSpec(sharedvector));
           byte[] encrypted = c.doFinal(strToEncrypt.getBytes());
           String strret = Base64.encodeToString(encrypted, Base64.DEFAULT);
           strret = strret.replace("\n", "");
           return URLEncoder.encode(strret, "UTF-8");
       } catch (Exception e) {
           Log.i("praveen","Encrypt error "+e.getMessage());
       }
       return null;
   }

 public static String encrypt_no_url_encode(String strToEncrypt)
 {
   try {
     Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
     c.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(keyValue.getBytes(), "AES"), new IvParameterSpec(sharedvector));
     byte[] encrypted = c.doFinal(strToEncrypt.getBytes());
     String strret = Base64.encodeToString(encrypted, Base64.DEFAULT);
     return strret;
    //return URLEncoder.encode(strret, "UTF-8");
   } catch (Exception e) {
     Log.i("praveen","Encrypt error "+e.getMessage());
   }
   return null;
 }


    public static String decrypt(String decrypt_string){
        try {
            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, new SecretKeySpec(keyValue.getBytes(), "AES"), new IvParameterSpec(sharedvector));
//                        byte[] decrypted = c.doFinal(Base64.decode(args.getString(0), Base64.DEFAULT));
//            byte[] decrypted = c.doFinal(Base64.decode(decrypt_string, Base64.DEFAULT));
          byte[] decrypted = c.doFinal(Base64.decode((URLDecoder.decode(decrypt_string,"UTF-8")), Base64.DEFAULT));
            String decrypt = new String(decrypted, "UTF-8");
            //callbackContext.success();
            return decrypt;

        } catch (Exception e) {
            Log.i("praveen","Decrypt Error "+e.getMessage());
        }
        return  null;
    }

}
