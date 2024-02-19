import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class Main {   
    private static final String ALGORITHM = "AES/CBC/PKCS5Padding";
    private static final String KEY = "REtgV24bDB7xQYoMuypiBASMEaJbc59n";
    private static final String IV = "8d2bc3f0f69426fc";

	public static void main(String[] args) {
		String data = "Fui gerado no java";
        System.out.println("Data=[" + data + "]");
        String encryptedData = "";
        try{
            encryptedData = encrypt(data);
            System.out.println("Data encrypted=[" + encryptedData + "]");
        }catch(Exception ex){
             System.out.println("Fail to encryptedData");
        }
        
        try{
            String decryptedData = decrypt("UXOvi7lf9P/NjQQrrNVvLX8npqUyCn4mYvMD9IOV6uQ=");
            System.out.println("Data decrypted=[" + decryptedData + "]");
        }catch(Exception ex){
             System.out.println("Fail to decryptedData");
        }
	}
	
	public static String encrypt(String data) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes("UTF-8"));
    
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);
    
        byte[] encrypted = cipher.doFinal(data.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encrypted);
    }
    
    public static String decrypt(String encryptedData) throws Exception {
        SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(IV.getBytes("UTF-8"));
    
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
    
        byte[] decoded = Base64.getDecoder().decode(encryptedData);
        byte[] decrypted = cipher.doFinal(decoded);
        return new String(decrypted, "UTF-8");
    }
}
