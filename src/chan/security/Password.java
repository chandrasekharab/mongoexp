package chan.security;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;






import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.security.SecureRandom;

public class Password {
    // The higher the number of iterations the more 
    // expensive computing the hash is for us
    // and also for a brute force attack.
    private static final int iterations = 10*1024;
    private static final int saltLen = 16;
    private static final int desiredKeyLen = 256;

    /** Computes a salted PBKDF2 hash of given plaintext password
        suitable for storing in a database. 
        Empty passwords are not supported. */
    public static String getSaltedHash(String password) throws Exception {
        byte[] salt = SecureRandom.getInstance("SHA1PRNG").generateSeed(saltLen);
        // store the salt with the password
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(salt) + "$" + hash(password, salt);
        //return Base64.encode(new String(salt)) + "$" + hash(password, salt);
        
        
    }

    /** Checks whether given plaintext password corresponds 
        to a stored salted hash of the password. */
    public static boolean check(String password, String stored) throws Exception{
        String[] saltAndPass = stored.split("\\$");
        if (saltAndPass.length != 2)
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] salt = decoder.decodeBuffer(saltAndPass[0]);
        //String salt = saltAndPass[0];
        String hashOfInput = hash(password, salt);
        return hashOfInput.equals(saltAndPass[1]);
    }

    // using PBKDF2 from Sun, an alternative is https://github.com/wg/scrypt
    // cf. http://www.unlimitednovelty.com/2012/03/dont-use-bcrypt.html
    private static String hash(String password, byte[] salt) throws Exception {
        if (password == null || password.length() == 0)
            throw new IllegalArgumentException("Empty passwords are not supported.");
        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        SecretKey key = f.generateSecret(new PBEKeySpec(
            password.toCharArray(), salt, iterations, desiredKeyLen)
        );
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(key.getEncoded());
        //return encoder.encode(new String(key.getEncoded()));
    }
}