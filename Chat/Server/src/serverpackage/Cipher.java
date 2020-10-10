package serverpackage;

public class Cipher {

	// encryption
	public static String encryption(String str) {
		char[] chars = str.toCharArray();
		StringBuilder str2 = new StringBuilder();
		for (byte b = 0; chars.length > b; b++) {
			chars[b] = (char) (chars[b] + 2);
			str2.append(chars[b]);
		}
		return str2.toString();
	}

	// decryption
	public static String decryption(String str) {
		char[] chars = str.toCharArray();
		StringBuilder str2 = new StringBuilder();
		for (byte b = 0; chars.length > b; b++) {
			chars[b] = (char) (chars[b] - 2);
			str2.append(chars[b]);
		}
		return str2.toString();
	}
}
