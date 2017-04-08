import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Elaine on 12/8/2016.
 */
public class advent9 {
    public static void main (String[] args) throws FileNotFoundException {
        Scanner scanner;
        scanner = new Scanner(new FileInputStream("src/input.txt"));
        String line = scanner.nextLine();
        System.out.println(length(line));
    }

    public static long length(String line) {
        if (line.indexOf('(',0) == -1) {
            System.out.println("line length: " + line.length());
            return line.length();
        }
        int i=0;
        long count=0;
        while(i < line.length()) {
            int openIndex = line.indexOf('(',i);
            if (openIndex != -1) {
                int xIndex = line.indexOf('x',i);
                int closeIndex = line.indexOf(')',i);
                int numChars = Integer.valueOf(line.substring(openIndex+1,xIndex));
                count += openIndex - i;
                int numRepeat = Integer.valueOf(line.substring(xIndex+1,closeIndex));
                System.out.println(numChars + " " + numRepeat);

                i = closeIndex + 1;
                count += length(line.substring(i,i+numChars)) * numRepeat;
                System.out.println("count: " + count);
                i += numChars;
            } else {
                break;
            }
        }

        count += line.length() - i;
        return count;
    }
}
