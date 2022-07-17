package encryption;


import encryption.exceptions.PasswordEncryptionException;
import utils.exceptions.ReadingFilePropertiesException;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.Properties;


public class PasswordEncryptor {
    private final String pepper;
    private final String propertiesFilePath;

    public PasswordEncryptor(String propertiesFilePath) throws ReadingFilePropertiesException {
        this.propertiesFilePath = propertiesFilePath;
        this.pepper = initializePepper("encryption.properties");
    }


    public String getHash(String passwordToHash, String salt) throws PasswordEncryptionException {
        String generatedPassword = null;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new PasswordEncryptionException("Произошла ошибка во время шифорования", e);
        }
        byte[] bytes = md.digest((salt + passwordToHash + pepper).getBytes(StandardCharsets.UTF_8));
        //md.update(salt.getBytes());
        //byte[] bytes = md.digest(passwordToHash.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16)
                    .substring(1));
        }
        generatedPassword = sb.toString();

        return generatedPassword;
    }

    private String initializePepper(String propertiesFilePath) {
        try {
            Properties property = getProperty();
            return property.getProperty("pepper");
        }catch (IOException e){
            return null;
        }
    }

    private Properties getProperty() throws  IOException{
        try(InputStream is = PasswordEncryptor.class.getClassLoader().getResourceAsStream("encryption.properties")) {
            Properties properties = new Properties();
            properties.load(is);
            return properties;
        }
    }

    private String getPepper() {
        return pepper;
    }

    public String getSalt() throws PasswordEncryptionException {
        // Always use a SecureRandom generator
        SecureRandom sr;
        try {
            sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            throw new PasswordEncryptionException("Произошла ошибка при шифровании пароля", e);
        }

        // Create array for salt
        byte[] salt = new byte[16];

        // Get a random salt
        sr.nextBytes(salt);

        // return salt
        return salt.toString();
    }

}
