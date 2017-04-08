import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Elaine on 12/20/2016.
 */
public class advent21 {
    public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException {
        //String input = "abcdefgh";
        ArrayList<String> allPossible = permutation("fbgdceah");

        for(String word : allPossible) {
            String input = word;
            System.out.println(input);
            Scanner scanner = new Scanner(new FileInputStream("src/input.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\s+");
                if (line.startsWith("swap position")) {
                    int pos1 = Integer.parseInt(parts[2]);
                    int pos2 = Integer.parseInt(parts[5]);
                    char first = input.charAt(pos1);
                    char[] charArray = input.toCharArray();
                    charArray[pos1] = charArray[pos2];
                    charArray[pos2] = first;
                    input = new String(charArray);
                } else if (line.startsWith("swap letter")) {
                    char char1 = parts[2].charAt(0);
                    char char2 = parts[5].charAt(0);
                    char[] charArray = input.toCharArray();
                    for (int i = 0; i < charArray.length; i++) {
                        if (charArray[i] == char1) {
                            charArray[i] = '*';
                        }
                    }
                    for (int i = 0; i < charArray.length; i++) {
                        if (charArray[i] == char2) {
                            charArray[i] = char1;
                        }
                    }
                    for (int i = 0; i < charArray.length; i++) {
                        if (charArray[i] == '*') {
                            charArray[i] = char2;
                        }
                    }
                    input = new String(charArray);
                } else if (line.startsWith("rotate right")) {
                    int numSteps = Integer.parseInt(parts[2]);
                    input = input.substring(input.length() - numSteps) +
                            input.substring(0, input.length() - numSteps);
                } else if (line.startsWith("rotate left")) {
                    int numSteps = Integer.parseInt(parts[2]);
                    input = input.substring(numSteps) +
                            input.substring(0, numSteps);
                } else if (line.startsWith("rotate based on ")) {
                    int index = input.indexOf(parts[6].charAt(0));
                    index += (index >= 4) ? 2 : 1;
                    index = index % input.length();
                    input = input.substring(input.length() - index) +
                            input.substring(0, input.length() - index);
                } else if (line.startsWith("reverse positions")) {
                    StringBuffer sb = new StringBuffer();
                    int x = Integer.parseInt(parts[2]);
                    int y = Integer.parseInt(parts[4]);
                    StringBuffer toReverse = new StringBuffer();
                    sb.append(input.substring(0, x));
                    toReverse.append(input.substring(x, y + 1));
                    sb.append(toReverse.reverse().toString());
                    sb.append(input.substring(y + 1));
                    input = sb.toString();
                } else if (line.startsWith("move position")) {
                    StringBuffer sb = new StringBuffer();
                    int x = Integer.parseInt(parts[2]);
                    int y = Integer.parseInt(parts[5]);
                    ArrayList<Character> chars = new ArrayList<Character>();
                    for (char c : input.toCharArray()) {
                        chars.add(c);
                    }
                    char c = chars.remove(x);
                    chars.add(y, c);
                    for (char d : chars) {
                        sb.append(d);
                    }
                    input = sb.toString();
                } else {
                    System.out.println("wtf");
                }
            }
            if (input.equals("fbgdceah")) {
                return;
            }
        }
    }

    public static ArrayList<String> permutation(String s) {
        // The result
        ArrayList<String> res = new ArrayList<String>();
        // If input string's length is 1, return {s}
        if (s.length() == 1) {
            res.add(s);
        } else if (s.length() > 1) {
            int lastIndex = s.length() - 1;
            // Find out the last character
            String last = s.substring(lastIndex);
            // Rest of the string
            String rest = s.substring(0, lastIndex);
            // Perform permutation on the rest string and
            // merge with the last character
            res = merge(permutation(rest), last);
        }
        return res;
    }

    /**
     * @param list a result of permutation, e.g. {"ab", "ba"}
     * @param c    the last character
     * @return     a merged new list, e.g. {"cab", "acb" ... }
     */
    public static ArrayList<String> merge(ArrayList<String> list, String c) {
        ArrayList<String> res = new ArrayList<String>();
        // Loop through all the string in the list
        for (String s : list) {
            // For each string, insert the last character to all possible postions
            // and add them to the new list
            for (int i = 0; i <= s.length(); ++i) {
                String ps = new StringBuffer(s).insert(i, c).toString();
                res.add(ps);
            }
        }
        return res;
    }


    public static String bytesToHex(byte[] bytes) {
        final char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for ( int j = 0; j < bytes.length; j++ ) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
    static char direction(int i) {
        switch(i) {
            case 0:
                return 'U';
            case 1:
                return 'D';
            case 2:
                return 'L';
            case 3:
                return 'R';
        }
        return '0';
    }
}
