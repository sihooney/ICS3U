import java.util.*;
import java.io.*;

public class Cipher {

    private static final String ORIGINAL = "AEIJOPRSTVX ";
    private static final String SUBSTITUTION = "@=!?*#&$+^%_";

    public static void main(String[] args) throws IOException {
        // Global variables
        Scanner userInput;
        Scanner inputFile;
        PrintWriter outputFile;
        boolean encryption;

        // Run code
        // User Input
        userInput = new Scanner(System.in);
        System.out.println("Encrypt or decrypt file? Enter 0 for encrypt, 1 for decrypt:");
        encryption = Integer.parseInt(userInput.nextLine()) == 0;
        System.out.println("Name of input file to read from:");
        inputFile = new Scanner(new File(userInput.nextLine() + ".txt"));
        System.out.println("Name of output file to output to:");
        outputFile = new PrintWriter(userInput.nextLine() + ".txt");
        userInput.close();

        // Read from input file and write the encryped/decryted text to output file
        while (inputFile.hasNextLine()) {
            String strLine = inputFile.nextLine();
            if (encryption) {
                outputFile.println(encrypt(strLine.toUpperCase()));
            } else {
                outputFile.println(decrypt(strLine));
            }
        }
        inputFile.close();
        outputFile.close();
    }

    /**
     * Encrypts the input string based on the encryption procedure
     *
     * @param str is the string to be encrypted
     * @return the encrypted string
     */
    public static String encrypt(String str) {
        String res = substitute(str, true);
        int split = (int) Math.ceil(str.length() / 2.0);
        res = res.substring(split) + res.substring(0, split);
        res = res.substring(res.length() - 2) + res.substring(2, res.length() - 2) + res.substring(0, 2);
        res = res.substring(0, split - 2) + res.substring(split, split + 2) +
                res.substring(split - 2, split) + res.substring(split + 2);
        return swapEveryTwo(res);
    }

    /**
     * Decrypts the input string based on the encryption method
     *
     * @param str is the string to be decrypted
     * @return the decrypted string
     */
    public static String decrypt(String str) {
        String res = swapEveryTwo(str);
        int split = (int) Math.ceil(str.length() / 2.0);
        res = res.substring(0, split - 2) + res.substring(split, split + 2) +
                res.substring(split - 2, split) + res.substring(split + 2);
        res = res.substring(res.length() - 2) + res.substring(2, res.length() - 2) + res.substring(0, 2);

        if (str.length() % 2 == 1) {
            split--;
        }
        res = res.substring(split) + res.substring(0, split);
        return substitute(res, false);
    }

    /**
     * Swaps every two characters of the input string
     *
     * @param str is the input string
     * @return a new string which is the input string with every two characters swapped
     */
    public static String swapEveryTwo(String str) {
        String ans = "";
        for (int j = 1; j < str.length(); j += 2) {
            ans += str.charAt(j);
            ans += str.charAt(j - 1);
        }
        if (str.length() % 2 == 1) {
            ans += str.charAt(str.length() - 1);
        }
        return ans;
    }

    /**
     * Performs the character substitutions based on if
     * it is encrypting or decrypting
     *
     * @param str     is the original string
     * @param encrypt true for encryption, false for decryption
     * @return a new string with the proper character substitutions
     */
    public static String substitute(String str, boolean encrypt) {
        String temp = "";
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (encrypt) {
                if (ORIGINAL.contains(String.valueOf(c))) {
                    temp += SUBSTITUTION.charAt(ORIGINAL.indexOf(c));
                } else {
                    temp += c;
                }
            } else {
                if (SUBSTITUTION.contains(String.valueOf(c))) {
                    temp += ORIGINAL.charAt(SUBSTITUTION.indexOf(c));
                } else {
                    temp += c;
                }
            }
        }
        return temp;
    }
}
