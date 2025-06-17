package helper;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

@Named
@ViewScoped
public class TextEncryptionController implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	private final String salt = KeyGenerators.string().generateKey();
	private final String password = "Password2015";
	
	public void doEncryptText() {
		TextEncryptor encryptor = Encryptors.text(password, salt);
		String encryptedText = encryptor.encrypt(text);
		String message = String.format("The encrypted text for %s is %s",
				text, encryptedText);
		JSFHelper.addInfoMessage(message);
	}
	
	public void doDecryptText() {
		TextEncryptor encryptor = Encryptors.text(password, salt);
		String decryptedText = encryptor.decrypt(text);
		String message = String.format("The decrypted text for %s is %s",
				text, decryptedText);
		JSFHelper.addInfoMessage(message);
	}
	
	
	private String text;	// generate getter/setter

	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	public void doEncodePassword() {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String hashedPassword = passwordEncoder.encode(text);
		String message = String.format("The hash password for %s is: %s", 
				text, hashedPassword);
		JSFHelper.addInfoMessage(message);
	}
	
}
