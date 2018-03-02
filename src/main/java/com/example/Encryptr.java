package com.example;

import java.nio.charset.StandardCharsets;
import java.security.AlgorithmParameters;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Service;
import com.example.model.Cypher;

@Service
public class Encryptr {
	private static final int ITR_COUNT = 2 ^ 20;
	private static final int KEY_SIZE = 256;
	private static final String KEY_ALGO = "AES";
	private static final String ALGO = "AES/CBC/PKCS5Padding";

	public SecretKey generateSecretKey(String password, String salt)
			throws NoSuchAlgorithmException, InvalidKeySpecException {
		SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), ITR_COUNT, KEY_SIZE);
		SecretKey tmp = factory.generateSecret(spec);
		return new SecretKeySpec(tmp.getEncoded(), KEY_ALGO);
	}
	public Cypher encrypt(String password, String salt, String input) throws Exception {
		return this.encrypt(this.generateSecretKey(password, salt), input);
	}
	public String decrypt(String password, String salt, Cypher cypher) throws Exception {
		return this.decrypt(this.generateSecretKey(password, salt), cypher);
	}
	public String decrypt(String password, String salt, char[] ciphertext, char[] iv) throws Exception {
		return this.decrypt(this.generateSecretKey(password, salt), new Cypher(ciphertext, iv));
	}
	public Cypher encrypt(SecretKey secret, String input) throws Exception {
		Cipher cipher = Cipher.getInstance(ALGO);
		cipher.init(Cipher.ENCRYPT_MODE, secret);
		AlgorithmParameters params = cipher.getParameters();
		char[] enc = Hex.encodeHex(cipher.doFinal(input.getBytes(StandardCharsets.UTF_8)));
		char[] iv = Hex.encodeHex(params.getParameterSpec(IvParameterSpec.class).getIV());
		return new Cypher(enc, iv);
	}
	public String decrypt(SecretKey secret, Cypher cypher) throws Exception {
		Cipher cipher = Cipher.getInstance(ALGO);
		byte[] iv = Hex.decodeHex(cypher.getIv());
		cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
		byte[] dec = Hex.decodeHex(cypher.getCiphertext());
		String plaintext = new String(cipher.doFinal(dec), StandardCharsets.UTF_8);
		return plaintext;
	}
}
