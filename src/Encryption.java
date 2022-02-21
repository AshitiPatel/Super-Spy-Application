

/**
 * 
 */

/**
 * @author Ashiti
 * Dates: 1-17 October 2021
 * Descryption: A class that encrypts or decrypts input based on which method is called from the UI
 * Method List: boolean isALetter (char character)		//A method that tells whether the given character is a letter or not
 * 				char encode (char letter, int encryptKey)		//A method that calculates the encrypted character
 * 				char decode (char letter, int decryptKey)		//A method that calculates the decrypted character
 * 				boolean isUppercase (int asciiValue)		//A method that takes in the ascii value(of a particular character) and determines whether the character is uppercase or not
 * 				int decodeKey (int key)		//A method that decodes the key read(either live or from a file) in order to get the actual key
 * 				void main(String[] args)		// Self-testing main method	
 */

public class Encryption {

	/**
	 * A method that tells whether the given character is a letter or not
	 * @param character
	 * @return true/false based on the what the character is
	 */
	public static boolean isALetter (char character) {

		//calculate the ascii value of the character and store it 
		int asciiValue = (int) character;

		//use the ascii value to determine whether it is a letter or not
		if(isUppercase(asciiValue) || ((asciiValue >= 97) && (asciiValue <= 122))) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * A method that calculates the encrypted character
	 * @param letter
	 * @param encryptKey
	 * @return encrypted character
	 */
	public static char encode (char letter, int encryptKey) {
		//if statement to encrypt letters ONLY
		if(Encryption.isALetter(letter)) {
			//declare and initialize the required variables
			int oriLetterAsciiValue = (int) letter, sumAscii = 0, tempAscii = 0, newCharAscii = 0;
			char encryptedChar = ' ';

			//calculating the sub ascii value if the character is upper case
			if(Encryption.isUppercase(oriLetterAsciiValue)) {		
				tempAscii = oriLetterAsciiValue - 64;
			}
			//calculating the sub ascii value if the character is lower case
			else {
				tempAscii = oriLetterAsciiValue - 96;
			}


			//adding ascii values to check whether they exceed 26 or not
			sumAscii = tempAscii + encryptKey;



			//if it is less than 0 calculate the new ascii value
			if(sumAscii < 0) {
				newCharAscii = sumAscii + 26;
			}
			//if they exceed 26 then calculate the new ascii value
			else if(sumAscii > 26) {
				newCharAscii = sumAscii - 26;
			}
			//when the sum/sub is in proper logical range
			else {
				newCharAscii = sumAscii;
			}


			//convert the value to the respective uppercase/lowercase letter
			if(Encryption.isUppercase(oriLetterAsciiValue)) {		//for uppercase
				encryptedChar = (char) (newCharAscii+64);
			}
			//for lowercase
			else {
				encryptedChar = (char) (newCharAscii+96);
			}


			return encryptedChar;
		}
		//returning the letter if it isn't a character that is not to be encrypted
		else {
			return letter;
		}
	}

	/**
	 * A method that calculates the decrypted character
	 * @param letter
	 * @param decryptKey
	 * @return decrypted character
	 */
	public static char decode (char letter, int decryptKey) {

		//multiply the decryptKey by -1 and send it to the encrypt method to decrypt the character
		return Encryption.encode(letter, (-1*decryptKey));

	}

	/**
	 * A method that takes in the ascii value(of a particular character) and determines whether the character is uppercase or not
	 * (I know this method may not really be necessary, 
	 * however to avoid making errors with the signs and not make to if statements look complicated, 
	 * I created this method)
	 * @param asciiValue
	 * @return true/false 
	 */
	public static boolean isUppercase (int asciiValue) {
		//if statement to compare the value with the range of uppercase letters and return true/false accordingly
		if(((asciiValue >= 65) && (asciiValue <= 90))) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * [EXTENSION]
	 * A method that decodes the key read(either live or from a file) in order to get the actual key
	 * @param key
	 * @return decoded key 
	 */
	public static int decodeKey (int key) {

		//decode the key by multiplying it by 2
		return (key*2)+3;
	}

	/**
	 * Self-testing main method
	 * @param args
	 */
	public static void main(String[] args) {

		//		String inp = JOptionPane.showInputDialog("input character:");
		//		int encryptKey = Integer.parseInt(JOptionPane.showInputDialog("the encryption key:"));
		//
		//		if(isALetter(inp.charAt(0))) {
		//			System.out.println("it went in");
		//		}
		//
		//		System.out.println(encode(inp.charAt(0), UserInterface.putKeyInRange(encryptKey)));

		//try for a phrase(encryption)
		String phrase = "Helloy! How's it going?";
		String phraseEncrypted = "", phraseDecrypted = "";
		char encryptedCharacter = ' ', decryptedCharacter = ' ';

		for(int i = 0; i < phrase.length(); i++) {
			encryptedCharacter = encode(phrase.charAt(i), 3);
			phraseEncrypted += encryptedCharacter;
		}

		System.out.println(phraseEncrypted);

		//try for phrase decrypted
		for(int i = 0; i < phrase.length(); i++) {
			decryptedCharacter = decode(phrase.charAt(i), 3);
			phraseDecrypted += decryptedCharacter;
		}

		System.out.println(phraseDecrypted);

	}
}
