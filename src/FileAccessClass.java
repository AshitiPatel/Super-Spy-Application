import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * 
 */

/**
 * @author Maihtaab
 * Date: 1-17 October 2021
 * Description: Reans and loads data into the respective text files
 * Method List: String[] readKeyAndPhrases(String fileName) throws IOException		//A method that reads the key and phrases present in the file, and returns it
 * 				void writeKeyandPhrases(String outFile, int key, String[] arrayOfPhrases) throws IOException		//A method that writes the key and phrases provided into the respective output file
 *				void main(String[] args) throws IOException		//Self-testing main method
 */
public class FileAccessClass {
	
	/**
	 * A method that reads the key and phrases present in the file, and returns it
	 * @param fileName
	 * @return an array, including the key and the phrases
	 * @throws IOException
	 */
	public static String[] readKeyAndPhrases(String fileName) throws IOException {
		//open a file for reading
		FileReader fr = new FileReader(fileName);
		BufferedReader input = new BufferedReader(fr);

		//declare variable to store the temporary phrase
		String tempPhrase = "";

		//create an array list to save the phrases
		ArrayList<String> phrasesArrayList = new ArrayList<String>();

		while(true) {
			//read each line
			tempPhrase = input.readLine();

			//break out of the loop if line says EOF
			if(tempPhrase.equals("EOF")) {
				break;
			}
			else {
				//store the phrase in the array list
				phrasesArrayList.add(tempPhrase);
			}
		}
		//closes file stream
		input.close();

		//array list to array conversion
		String phrases[] = new String[phrasesArrayList.size()];              
		for(int j =0;j<phrasesArrayList.size();j++){
			phrases[j] = phrasesArrayList.get(j);
		}

		//return array of phrases
		return phrases;
	}
/**
 * A method that writes the key and phrases provided into the respective output file
 * @param outFile
 * @param key
 * @param arrayOfPhrases
 * @throws IOException
 */
	public static void writeKeyandPhrases(String outFile, int key, String[] arrayOfPhrases) throws IOException {
		//setup the writer
		PrintWriter fw = new PrintWriter (new FileWriter(outFile));
		
		//write the key
		fw.println(key);
		
		//write the phrases
		for(int i = 0; i < arrayOfPhrases.length; i++) {
			fw.println(arrayOfPhrases[i]);
		}
		
		//write the last line
		fw.println("EOF");

		//close
		fw.close();

	}

	/**
	 * Self-testing main method
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		 //try reading from a file
		String fileName = JOptionPane.showInputDialog("enter file to read from", "trials.txt");
		String phrases[] = readKeyAndPhrases(fileName);
		for(int i = 0; i <phrases.length; i ++) {
		System.out.println(phrases[i]);
		 }

		//try writing to a file
		String sample[] = new String[6];
		sample[0] = "Hiiii!";
		sample[1] = "How are you?";
		sample[2] = "Wow, that's amazing";
		sample[3] = "Really? Cool!";
		sample[4] = "I want pizza.";
		sample[5] = "You're a good friend.";

		writeKeyandPhrases("outFile.txt", 4, sample);

	}

}
