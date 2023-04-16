package p2;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;

import p2.DataStructures.List.List;
import p2.DataStructures.Map.HashTableSC;
import p2.DataStructures.Map.Map;
import p2.DataStructures.SortedList.SortedLinkedList;
import p2.DataStructures.SortedList.SortedList;
import p2.DataStructures.Tree.BTNode;

/**
 * The Huffman Encoding Algorithm
 * 
 * This is a data compression algorithm designed 
 * by David A. Huffman and published in 1952
 * 
 * What it does is it takes a string and by constructing 
 * a special binary tree with the frequencies of each character.
 * 
 * This tree generates special prefix codes that make the size 
 * of each string encoded a lot smaller, thus saving space.
 * 
 * @author Fernando J. Bermudez Medina (Template) 
 * @author Jorge Colon Velez - 843-18-8733 (Implementation)
 * @version 3.0
 * @since 03/28/2023
 */
public class HuffmanCoding {

	public static void main(String[] args) {
		HuffmanEncodedResult();
	}

	/* This method just runs all the main methods developed or the algorithm */
	private static void HuffmanEncodedResult() {
		/* You can create other test input files and add them to the inputData Folder */
		String data = load_data("input1.txt");

		/* If input string is not empty we can encode the text using our algorithm */
		if(!data.isEmpty()) {
			Map<String, Integer> fD = compute_fd(data);
			BTNode<Integer,String> huffmanRoot = huffman_tree(fD);
			Map<String,String> encodedHuffman = huffman_code(huffmanRoot);
			String output = encode(encodedHuffman, data);
			process_results(fD, encodedHuffman,data,output);
		} else 
			System.out.println("Input Data Is Empty! Try Again with a File that has data inside!");
		

	}

	/**
	 * Receives a file named in parameter inputFile (including its path),
	 * and returns a single string with the contents.
	 * 
	 * @param inputFile name of the file to be processed in the path inputData/
	 * @return String with the information to be processed
	 */
	public static String load_data(String inputFile) {
		BufferedReader in = null;
		String line = "";

		try {
			/**
			 * We create a new reader that accepts UTF-8 encoding and 
			 * extract the input string from the file, and we return it
			 */
			in = new BufferedReader(new InputStreamReader(new FileInputStream("inputData/" + inputFile), "UTF-8"));

			/**
			 * If input file is empty just return an 
			 * empty string, if not just extract the data
			 */
			String extracted = in.readLine();
			if(extracted != null)
				line = extracted;

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null) 
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return line;
	}

	/**
	 * TODO ADD DESCRIPTION OF WHAT THIS METHOD DOES HERE
	 * 
	 * @param TODO ADD PARAMETER AND DESCRIPTION
	 * @return TODO ADD RETURN AND DESCRIPTION
	 */
	public static Map<String, Integer> compute_fd(String inputString) {
		/* TODO Compute Symbol Frequency Distribution of each character inside input string */
		Map<String, Integer> freq = new HashTableSC<String, Integer>();
		
		for(int i = 0; i < inputString.length(); i++) {
			String c = Character.toString(inputString.charAt(i));
			
			if(!freq.containsKey(c))
				freq.put(c, 1);
			else {
				int count = freq.get(c);
				freq.put(c, count + 1);
			}
		}
		return freq; //Dummy Return
	}

	/**
	 * TODO ADD DESCRIPTION OF WHAT THIS METHOD DOES HERE
	 * 
	 * @param TODO ADD PARAMETER AND DESCRIPTION
	 * @return TODO ADD RETURN AND DESCRIPTION
	 */
	public static BTNode<Integer, String> huffman_tree(Map<String, Integer> fD) {

		/* TODO Construct Huffman Tree */
		BTNode<Integer,String> rootNode = null;
		
		SortedList<BTNode<Integer, String>> sortedList = new  SortedLinkedList<BTNode<Integer, String>>();
		
	    for (String key : fD.getKeys()) {
	        BTNode<Integer, String> node = new BTNode<>(fD.get(key), key);
	        sortedList.add(node);
	    }

	    while (sortedList.size() > 1) {
	        BTNode<Integer, String> leftNode = sortedList.get(0);
	        sortedList.removeIndex(0);
	        BTNode<Integer, String> rightNode = sortedList.get(0);
	        sortedList.removeIndex(0);

	        int combinedFrequency = leftNode.getKey() + rightNode.getKey();
	        BTNode<Integer, String> newNode = new BTNode<>(combinedFrequency, leftNode.getValue() + rightNode.getValue());
	        newNode.setLeftChild(leftNode);
	        newNode.setRightChild(rightNode);

	        sortedList.add(newNode);
	    }
		/* Use this method to see full Huffman Tree built with the generated root node
		BinaryTreePrinter.print(rootNode); 
		 */
	    
	    rootNode = sortedList.get(0);
		return rootNode; //Dummy Return
	}

	/**
	 * TODO ADD DESCRIPTION OF WHAT THIS METHOD DOES HERE
	 * 
	 * @param ADD PARAMETER AND DESCRIPTION
	 * @return ADD RETURN AND DESCRIPTION
	 */
	public static Map<String, String> huffman_code(BTNode<Integer,String> huffmanRoot) {
		/* TODO Construct Prefix Codes */
		Map<String, String> encodingMap = new HashTableSC<String, String>();
		recEncode(huffmanRoot, encodingMap, "");
		
		return encodingMap; //Dummy Return
	}

	/**
	 * TODO ADD DESCRIPTION OF WHAT THIS METHOD DOES HERE
	 * 
	 * @param TODO ADD PARAMETER AND DESCRIPTION
	 * @param TODO ADD PARAMETER AND DESCRIPTION
	 * @return TODO ADD RETURN AND DESCRIPTION
	 */
	public static String encode(Map<String, String> encodingMap, String inputString) {
		/* TODO Encode String */
		return ""; //Dummy Return
	}

	/**
	 * Receives the frequency distribution map, the Huffman Prefix Code HashTable, the input string, 
	 * and the output string, and prints the results to the screen (per specifications).
	 * 
	 * Output Includes: symbol, frequency and code. 
	 * Also includes how many bits has the original and encoded string, plus how much space was saved using this encoding algorithm
	 * 
	 * @param fD Frequency Distribution of all the characters in input string
	 * @param encodedHuffman Prefix Code Map
	 * @param inputData text string from the input file
	 * @param output processed encoded string
	 */
	public static void process_results(Map<String, Integer> fD, Map<String, String> encodedHuffman, String inputData, String output) {
		/*To get the bytes of the input string, we just get the bytes of the original string with string.getBytes().length*/
		int inputBytes = inputData.getBytes().length;

		/**
		 * For the bytes of the encoded one, it's not so easy.
		 * 
		 * Here we have to get the bytes the same way we got the bytes 
		 * for the original one but we divide it by 8, because 
		 * 1 byte = 8 bits and our huffman code is in bits (0,1), not bytes. 
		 * 
		 * This is because we want to calculate how many bytes we saved by 
		 * counting how many bits we generated with the encoding 
		 */
		DecimalFormat d = new DecimalFormat("##.##");
		double outputBytes = Math.ceil((float) output.getBytes().length / 8);

		/**
		 * to calculate how much space we saved we just take the percentage.
		 * the number of encoded bytes divided by the number of original bytes 
		 * will give us how much space we "chopped off".
		 * 
		 * So we have to subtract that "chopped off" percentage to the total (which is 100%) 
		 * and that's the difference in space required
		 */
		String savings =  d.format(100 - (( (float) (outputBytes / (float)inputBytes) ) * 100));


		/**
		 * Finally we just output our results to the console 
		 * with a more visual pleasing version of both our 
		 * Hash Tables in decreasing order by frequency.
		 * 
		 * Notice that when the output is shown, the characters 
		 * with the highest frequency have the lowest amount of bits.
		 * 
		 * This means the encoding worked and we saved space!
		 */
		System.out.println("Symbol\t" + "Frequency   " + "Code");
		System.out.println("------\t" + "---------   " + "----");

		SortedList<BTNode<Integer,String>> sortedList = new SortedLinkedList<BTNode<Integer,String>>();

		/**
		 * To print the table in decreasing order by frequency, 
		 * we do the same thing we did when we built the tree.
		 * 
		 * We add each key with it's frequency in a node into a SortedList, 
		 * this way we get the frequencies in ascending order
		 */
		for (String key : fD.getKeys()) {
			BTNode<Integer,String> node = new BTNode<Integer,String>(fD.get(key),key);
			sortedList.add(node);
		}

		/**
		 * Since we have the frequencies in ascending order, 
		 * we just traverse the list backwards and start printing 
		 * the nodes key (character) and value (frequency) and find 
		 * the same key in our prefix code "Lookup Table" we made 
		 * earlier on in huffman_code(). 
		 * 
		 * That way we get the table in decreasing order by frequency
		 */
		for (int i = sortedList.size() - 1; i >= 0; i--) {
			BTNode<Integer,String> node = sortedList.get(i);
			System.out.println(node.getValue() + "\t" + node.getKey() + "\t    " + encodedHuffman.get(node.getValue()));
		}

		System.out.println("\nOriginal String: \n" + inputData);
		System.out.println("Encoded String: \n" + output);
		System.out.println("Decoded String: \n" + decodeHuff(output, encodedHuffman) + "\n");
		System.out.println("The original string requires " + inputBytes + " bytes.");
		System.out.println("The encoded string requires " + (int) outputBytes + " bytes.");
		System.out.println("Difference in space requiered is " + savings + "%.");
	}


	/*************************************************************************************
	 ** ADD ANY AUXILIARY METHOD YOU WISH TO IMPLEMENT TO FACILITATE YOUR SOLUTION HERE **
	 *************************************************************************************/

	/**
	 * Auxiliary Method that decodes the generated string by the Huffman Coding Algorithm
	 * 
	 * Used for output Purposes
	 * 
	 * @param output - Encoded String
	 * @param lookupTable 
	 * @return The decoded String, this should be the original input string parsed from the input file
	 */
	public static String decodeHuff(String output, Map<String, String> lookupTable) {
		String result = "";
		int start = 0;
		List<String>  prefixCodes = lookupTable.getValues();
		List<String> symbols = lookupTable.getKeys();

		/**
		 * Loop through output until a prefix code is found on map and 
		 * adding the symbol that the code that represents it to result 
		 */
		for(int i = 0; i <= output.length();i++){

			String searched = output.substring(start, i);

			int index = prefixCodes.firstIndex(searched);

			if(index >= 0) { // Found it!
				result= result + symbols.get(index);
				start = i;
			}
		}
		return result;    
	}
	
	private static void recEncode(BTNode<Integer, String> node, Map<String, String> encodingMap, String code) {
		
		if(node != null) {
			
			if(node.getLeftChild() == null && node.getRightChild() == null)
				encodingMap.put(node.getValue(), code);
			else {
				recEncode(node.getLeftChild(), encodingMap, code + "0");
				recEncode(node.getRightChild(), encodingMap, code + "1");
				
			}
		}
	}
}