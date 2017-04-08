import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by Elaine on 12/6/2016.
 */
public class advent7 {
    public static void main (String[] args) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileInputStream("src/input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int count = 0;
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("[\\[\\]]");
            ArrayList<String> outer = new ArrayList<String>();
            ArrayList<String> inner = new ArrayList<String>();
            for(int i=0;i<parts.length;i++){
                if (i%2==0) {
                    outer.add(parts[i]);
                } else {
                    inner.add(parts[i]);
                }
            }
            boolean valid = false;
            HashSet<String> abas = new HashSet<String>();
            for(String word : outer) {
                    abas.addAll(allAbas(word));
            }
            for(String aba : abas) {
                for (String word : inner) {
                    if (bab(word, aba)!=null) {
                        valid = true;
                    }
                }
            }
            if (valid) {
                count++;
            }

        }

        System.out.println(count);
    }

    private static String bab(String word, String aba) {
        String target = "" + aba.charAt(1) + aba.charAt(0) + aba.charAt(1);
        System.out.println(aba + ":" + target);
        for(int i=0;i<word.length()-2;i++) {
            if(word.substring(i,i+3).equals(target)) {
                return target;
            }
        }
        return null;
    }

    private static boolean hasAbba(String word) {
        for(int i=0;i<word.length() - 3; i++) {
            if (word.charAt(i) == word.charAt(i+3) && word.charAt(i+1) == word.charAt(i+2) && word.charAt(i) != word.charAt(i+1)) {
                return true;
            }
        }
        return false;
    }

    private static HashSet<String> allAbas(String word) {
        HashSet<String> all = new HashSet<String>();
        for(int i=0;i<word.length() - 2; i++) {
            if (word.charAt(i) == word.charAt(i+2) && word.charAt(i) != word.charAt(i+1)) {
                all.add( word.substring(i, i+3));
            }
        }
        return all;
    }
}
