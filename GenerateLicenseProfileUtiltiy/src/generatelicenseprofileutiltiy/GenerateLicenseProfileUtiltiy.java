/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generatelicenseprofileutiltiy;

import static generatelicenseprofileutiltiy.EncryptionUtil.PUBLIC_KEY_FILE;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.security.PublicKey;

/**
 *
 * @author Pasha
 */
public class GenerateLicenseProfileUtiltiy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String serialNumber = DiskUtils.getSerialNumber("C:\\");
        System.out.println(serialNumber);

        StringBuffer originalText = new StringBuffer();
        originalText.append(serialNumber);

        if (!EncryptionUtil.areKeysPresent()) {
        // Method generates a pair of keys using the RSA algorithm and stores it
        // in their respective files
            EncryptionUtil.generateKey();
        }
        
      ObjectInputStream inputStream = null;

      // Encrypt the string using the public key
      try
      {
        inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
        final PublicKey publicKey = (PublicKey) inputStream.readObject();
        final byte[] cipherText = EncryptionUtil.encrypt(originalText.toString(), publicKey);        

        FileOutputStream outFile = new FileOutputStream("LicenseProfile.dat");
        outFile.write(cipherText);
        outFile.close();
      } catch(Exception ex)        
      {
          
      }

    }

}
