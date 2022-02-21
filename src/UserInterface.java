import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.io.IOException;

import javax.swing.ImageIcon;

/**
 * @authors Ashiti and Mahitaab
 * Date: 1-17 October 2021
 * Description: Performs all interactions with the user along with reading the phrases from the file, sending the phrases to the Encryption class to be encrypted or decrypted and then displaying the new phrases to the user.
 * Method List: boolean checkKey (int encryptKey) 		//A method that checks the key and returns true if the key is within the specified range of -32767 and +32768.
 * 				int putKeyInRange (int encryptKey)		//A method that returns a key that is usable by the Encryption class(a value between 0 to 26)
 * 				void main(String[] args) throws IOException			//Main method
 *
 */
public class UserInterface {

	/**
	 * A method that checks the key and returns true if the key is within the specified range of -32767 and +32768.
	 * @param encryptKey
	 * @return true(if the key is within the specified range)/false(if the key isn't within the range)
	 */
	public static boolean checkKey (int encryptKey) {
		//check whether the key entered is within the specified range and return true/false accordingly 
		if((encryptKey >= -32767) && (encryptKey <= 32767)) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * A method that returns a key that is usable by the Encryption class(a value between 0 to 26)
	 * @param encryptKey
	 * @return simplified key(value between 0 to 26 or -26 to 0)
	 */
	public static int putKeyInRange (int encryptKey) {
		//calculate and return the simplified key
		return encryptKey % 26;
	}

	//I know that there are places in the program where the code is repeated, but, to keep the details intact(like being able to display the initial key and actual key, both), the code was made to repeat itself. 

	//Here is an example of many such methods created to shorten the code but, unfortunately, it could not produce the right initial key when displaying since a method can return only one vale

	//	public static int inpToActualKey (int inputKey) {
	//		
	//		int actualKey = 0;
	//		
	//		//find actual key using method
	//		actualKey = Encryption.decodeKey(inputKey);
	//
	//		//check whether the actualKey is valid
	//		while(!checkKey(actualKey)) {
	//
	//			//prompt for key again
	//			inputKey = Integer.parseInt(JOptionPane.showInputDialog("Enter an initial key that leads to a VALID actual key:"));
	//
	//			//find actual key using method
	//			actualKey = Encryption.decodeKey(inputKey);
	//		}
	//		
	//		return actualKey;
	//	}

	/**
	 * Main method
	 * @param args 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		//setup textfields for username and password
		JTextField username = new JTextField();
		JTextField password = new JPasswordField();

		//setup for login
		Object[] login = {
				"Username:", username,
				"Password:", password
		};

		//images setup
		ImageIcon superspy = new ImageIcon("superspy.png");
		ImageIcon alertImage = new ImageIcon("alert.png");
		ImageIcon topSecretImage = new ImageIcon("topsecret.png");
		ImageIcon loginSuccessImage = new ImageIcon("loginSuccess.png");
		ImageIcon outputDispImage = new ImageIcon("outputDisp.jpg");

		//declare and initialize the required variables
		int trials = 0, inputKey = 0, actualKey = 0, simplifiedKey = 0;
		String inputPhrase = "", outPhrase = "", inputFile = "", outputFile = "", output = "";


		int LoginOrCancel = 
				JOptionPane.showConfirmDialog(null, login, "Spy Login", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.INFORMATION_MESSAGE, superspy);		


		//if user selects ok
		if(LoginOrCancel == JOptionPane.OK_OPTION) {

			//while(logincancel == ok and username/pwd not correct and try < 3) //fyi try starts at 0
			while((LoginOrCancel == JOptionPane.OK_OPTION) && (!username.getText().equals("spy5181") && !password.getText().equals("I_forgot_OOOPS_hehe#1234")) && trials < 2) {
				LoginOrCancel = 
						JOptionPane.showConfirmDialog(null, login, "Incorrect Username or Password", JOptionPane.OK_CANCEL_OPTION,
								JOptionPane.INFORMATION_MESSAGE, superspy);

				trials+= 1;

				//break out of the loop 
				if(username.getText().equals("spy5181") && password.getText().equals("I_forgot_OOOPS_hehe#1234")) {
					break;
				}
				else if(((LoginOrCancel == JOptionPane.OK_OPTION) && (!username.getText().equals("spy5181") && !password.getText().equals("I_forgot_OOOPS_hehe#1234")) && trials == 2)) {
					JOptionPane.showConfirmDialog(null, "Initiating Alert Alarm!", "Intruder Detected", JOptionPane.OK_CANCEL_OPTION,
							JOptionPane.INFORMATION_MESSAGE, alertImage);
				}	
			}

			//proceed only if the username and passwords are correct
			if(username.getText().equals("spy5181") && password.getText().equals("I_forgot_OOOPS_hehe#1234")) {

				//custon buttons text
				UIManager.put("OptionPane.yesButtonText", "LIVE");
				UIManager.put("OptionPane.noButtonText", "FILE");

				//ask whether live or through file
				int liveOrFile = JOptionPane.showConfirmDialog(null, "Welcome!\n"
						+ "Select a mode for encryption/deryption", "LOGIN SUCCESSFULL" ,JOptionPane.YES_NO_OPTION, 
						JOptionPane.INFORMATION_MESSAGE, loginSuccessImage);

				//if spy selects live
				while (liveOrFile == JOptionPane.YES_OPTION) {

					//setup textfields for key and phrase
					JTextField keyTextField = new JTextField();
					JTextField phraseTextField = new JTextField();

					//setup for inputing key and phrase
					Object[] liveInput = {
							"Initial Key:\n", keyTextField,
							"Phrase:\n", phraseTextField
					};

					//change text on buttons
					UIManager.put("OptionPane.yesButtonText", "ENCRYPT");
					UIManager.put("OptionPane.noButtonText", "DECRYPT");

					//prompt and take in spy's input
					int liveEncOrDec = JOptionPane.showConfirmDialog(null, liveInput, "LIVE MODE", JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE, topSecretImage);

					//if user chooses to encrypt
					if(liveEncOrDec == JOptionPane.YES_OPTION) {

						//read input
						inputKey = Integer.parseInt(keyTextField.getText());
						inputPhrase = phraseTextField.getText();

						//find actual key using method
						actualKey = Encryption.decodeKey(inputKey);

						//check whether the actualKey is valid
						while(!checkKey(actualKey)) {

							//prompt for key again
							inputKey = Integer.parseInt(JOptionPane.showInputDialog("Enter an initial key that leads to a VALID actual key:"));

							//find actual key using method
							actualKey = Encryption.decodeKey(inputKey);
						}

						//put key in range
						simplifiedKey = UserInterface.putKeyInRange(actualKey);

						//loop to encrypt each character of the phrase
						for(int i = 0; i < inputPhrase.length(); i++) {
							//add each encrypted character to the encrypted phrase
							outPhrase = outPhrase + Encryption.encode(inputPhrase.charAt(i), simplifiedKey);
						}

						//change text on buttons
						UIManager.put("OptionPane.yesButtonText", "Continue LIVE");
						UIManager.put("OptionPane.noButtonText", "FILE Mode");
						UIManager.put("OptionPane.cancelButtonText", "EXIT");

						//display and take in user's choice
						liveOrFile = JOptionPane.showConfirmDialog(null,"Input Key: " + inputKey + "\nActual Key: " + actualKey + "\nEncrypted Phrase: " + outPhrase, "LIVE MODE", 
								JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, outputDispImage);

						//clear outPhrase in case the user chooses to repeat
						outPhrase = "";
					}

					//if user chooses to decrypt
					else {
						//read input
						inputKey = Integer.parseInt(keyTextField.getText());
						inputPhrase = phraseTextField.getText();

						//find actual key using method
						actualKey = Encryption.decodeKey(inputKey);

						//check whether the actualKey is valid
						while(!checkKey(actualKey)) {

							//prompt for key again
							inputKey = Integer.parseInt(JOptionPane.showInputDialog("Enter an initial key that leads to a VALID actual key:"));

							//find actual key using method
							actualKey = Encryption.decodeKey(inputKey);
						}

						//put key in range
						simplifiedKey = UserInterface.putKeyInRange(actualKey);

						//loop to encrypt each character of the phrase
						for(int i = 0; i < inputPhrase.length(); i++) {
							//add each encrypted character to the encrypted phrase
							outPhrase = outPhrase + Encryption.decode(inputPhrase.charAt(i), simplifiedKey);
						}

						//change text on buttons
						UIManager.put("OptionPane.yesButtonText", "Continue LIVE");
						UIManager.put("OptionPane.noButtonText", "FILE Mode");
						UIManager.put("OptionPane.cancelButtonText", "EXIT");

						//display and take in user's choice
						liveOrFile = JOptionPane.showConfirmDialog(null,"Input Key: " + inputKey + "\nActual Key: " + actualKey + 
								"\nDecrypted Phrase: " + outPhrase, "LIVE MODE", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, outputDispImage);

						//clear outPhrase in case the user chooses to repeat
						outPhrase = "";
					}

				}

				while(liveOrFile == JOptionPane.NO_OPTION) {

					//setup textfields for key and phrase
					JTextField fileNameTextField = new JTextField();

					//setup for inputing key and phrase
					Object[] fileNameInput = {
							"Enter the name of the file to load phrases from:\n", fileNameTextField
					};

					//change text on buttons
					UIManager.put("OptionPane.yesButtonText", "ENCRYPT");
					UIManager.put("OptionPane.noButtonText", "DECRYPT");

					//prompt and take in spy's input
					int fileEncOrDec = JOptionPane.showConfirmDialog(null, fileNameInput, "FILE MODE", JOptionPane.YES_NO_OPTION,
							JOptionPane.INFORMATION_MESSAGE, topSecretImage);

					//read file name
					inputFile = fileNameTextField.getText();

					//get a combined array of phrases and key
					String keyAndPhrases[] = FileAccessClass.readKeyAndPhrases(inputFile);

					//arrays for input and output phrases
					String inputPhrases[] = new String[keyAndPhrases.length - 1];
					String outputPhrases[] = new String[keyAndPhrases.length - 1];

					//seperate phrases from key
					inputKey = Integer.parseInt(keyAndPhrases[0]);

					for(int i = 1; i < keyAndPhrases.length; i++) {
						inputPhrases[i - 1] = keyAndPhrases[i]; 
					}

					//find actual key using method
					actualKey = Encryption.decodeKey(inputKey);

					//check whether the actualKey is valid
					while(!checkKey(actualKey)) {

						//prompt for key again
						inputKey = Integer.parseInt(JOptionPane.showInputDialog("Enter an initial key that leads to a VALID actual key:"));

						//find actual key using method
						actualKey = Encryption.decodeKey(inputKey);
					}

					//put it in range
					simplifiedKey = UserInterface.putKeyInRange(actualKey);

					//add keys to output
					output += "Input Key: " + inputKey + 
							"\nActual Key: " + actualKey;

					//calculate encrypted phrase if encryption chosen
					if(fileEncOrDec == JOptionPane.YES_OPTION)
						for(int j = 0; j < inputPhrases.length; j++) {
							for(int i = 0; i < inputPhrases[j].length(); i++) {
								outputPhrases[j] += Encryption.encode(inputPhrases[j].charAt(i), simplifiedKey);
							}

							//add the encrypted phrases heading to output in the end
							if(j == inputPhrases.length - 1) {
								output += "\nEncrypted Phrases:";
							}
						}
					//calculate decrypted phrase if decryption chosen
					else {
						for(int j = 0; j < inputPhrases.length; j++) {
							for(int i = 0; i < inputPhrases[j].length(); i++) {
								outputPhrases[j] += Encryption.decode(inputPhrases[j].charAt(i), simplifiedKey);
							}

							//add the decrypted phrases heading to output in the end
							if(j == inputPhrases.length - 1) {
								output += "\nDecrypted Phrases:";
							}
						}	
					}

					//add phrases to the output
					for(int x = 0; x < outputPhrases.length; x++) {
						output += "\n" + outputPhrases[x];
					}

					//change text on buttons
					UIManager.put("OptionPane.yesButtonText", "Save in new File");
					UIManager.put("OptionPane.noButtonText", "Continue FILE MODE");
					UIManager.put("OptionPane.cancelButtonText", "EXIT");

					//display and take in user's response
					liveOrFile = JOptionPane.showConfirmDialog(null, output, "FILE MODE", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, outputDispImage);

					//clear output
					output = "";

					//if user chooses to save to a new file
					if(liveOrFile == JOptionPane.YES_OPTION) {

						//prompt and get name of output file
						outputFile = JOptionPane.showInputDialog("Enter the name of the file in which you'd like to save the data:", "trialOutput.txt");

						//call method to save to new file
						FileAccessClass.writeKeyandPhrases(outputFile, inputKey, outputPhrases);

						//a friendly info message
						JOptionPane.showMessageDialog(null, "File saved.");
					}	
				}

				//ending loggout message
				JOptionPane.showMessageDialog(null, "Spy work done.\nLogout successfull.");
			}
		}

		//if cancel selected
		else {
			JOptionPane.showMessageDialog(null, "Login Cancelled");
		}
	}
}

