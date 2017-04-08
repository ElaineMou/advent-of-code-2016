import javafx.util.Pair;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Elaine on 12/3/2016.
 */
public class advent4 {
    public static void main (String[] args) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileInputStream("src/input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        int sum = 0;
        while(scanner.hasNextLine()) {
            int[] histogram = new int[26];

            String line = scanner.nextLine();
            String[] words = line.split("[^a-zA-Z0-9]");
            int i=0;
            while(!words[i].matches("^-?\\d+$")) {
                for(char c : words[i].toCharArray()) {
                    histogram[c - 'a'] = histogram[c - 'a'] + 1;
                }
                i++;
            }
            int sectorId = Integer.parseInt(words[i]);
            i++;
            String checksum = words[i];
            String topFive = getTopFive(histogram);

            if (checksum.equals(topFive)) {
                printDecrypted(words, sectorId);
                sum += sectorId;
            }
        }
        System.out.println(sum);
    }

    private static void printDecrypted(String[] words, int sectorId) {
        for(int i=0;i<words.length -2;i++) {
            for(char c : words[i].toCharArray()) {
                System.out.print((char)((c - 'a' + sectorId) %26 + 'a'));
            }
            System.out.print("-");
        }
        System.out.println(sectorId);
    }

    private static String getTopFive(int[] histogram) {
        Pair<Character, Integer>[] pairs;
        pairs = new Pair[26];
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<26;i++) {
            pairs[i] = new Pair<Character, Integer>((char)('a' + i), histogram[i]);
        }
        Arrays.sort(pairs, new Comparator<Pair<Character, Integer>>() {
            @Override
            public int compare(Pair<Character, Integer> o1, Pair<Character, Integer> o2) {
                if (Integer.compare(o1.getValue(), o2.getValue())!=0) {
                    return Integer.compare(o1.getValue(), o2.getValue());
                } else {
                    return -1*Character.compare(o1.getKey(), o2.getKey());
                }
            }
        });

        for(int i=21;i<26;i++) {
            sb.append(pairs[i].getKey());
        }
        return sb.reverse().toString();
    }
}
