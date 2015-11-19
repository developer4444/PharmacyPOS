/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package generatelicenseutility;

import static generatelicenseutility.EncryptionUtil.PRIVATE_KEY_FILE;
import static generatelicenseutility.EncryptionUtil.PUBLIC_KEY_FILE;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.ArrayList;

/**
 *
 * @author Pasha
 */
public class GenerateLicenseUtility {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // Decrypt the cipher text using the private key.
        try
        {
            String vendorName = "MoonMedicalStore";
            FileInputStream inputFile = new FileInputStream("LicenseProfile.dat");

            byte cipherText[] = new byte[128];
            int noBytesRead = inputFile.read(cipherText);

            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
            final PrivateKey privateKey = (PrivateKey) inputStream.readObject();

            String plainText = EncryptionUtil.decrypt(cipherText, privateKey);
            StringBuffer license = new StringBuffer();
            license.append(vendorName);
            license.append(";");
            license.append(plainText);

            // Encrypt the string using the public key
            try
            {
              inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
              final PublicKey publicKey = (PublicKey) inputStream.readObject();
              final byte[] licenseEncText = EncryptionUtil.encrypt(license.toString(), publicKey);        

              FileOutputStream outFile = new FileOutputStream("License.lic");
              outFile.write(licenseEncText);
              outFile.close();
            } catch(Exception ex)        
            {
            }
        } catch(Exception ex)
        {
        }
    }
}
