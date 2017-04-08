import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Elaine on 12/5/2016.
 */
public class advent6 {

    public static void main (String[] args) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileInputStream("src/input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        HashMap<Character, Integer>[] maps = new HashMap[8];
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            int i=0;
            for(char c : line.toCharArray()) {
                HashMap<Character, Integer> map = maps[i];
                if (map==null) {
                    maps[i] = new HashMap<Character, Integer>();
                    map = maps[i];
                }
                if(!map.containsKey(c)) {
                    map.put(c, 0);
                }
                map.put(c, map.get(c) + 1);
                i++;
            }
        }
        for(int i=0;i<maps.length;i++) {
            HashMap<Character, Integer> map = maps[i];
            int min = 9999;
            char minChar = 'a';
            for(Character c : map.keySet()) {
                if (map.get(c) < min) {
                    min = map.get(c);
                    minChar = c;
                }
            }
            System.out.print(minChar + "");
        }
    }
}
