import javafx.util.Pair;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * Created by Elaine on 12/23/2016.
 */
public class advent24 {
    static int NUM_ROWS = 41;
    static int NUM_COLS = 185;
    public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException {
        Scanner scanner = new Scanner(new FileInputStream("src/input.txt"));
        int count = 0;
        char[][] grid = new char[NUM_ROWS][NUM_COLS];
        int rows[] = new int[8];
        int cols[] = new int[8];
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            for(int i=0;i<line.length();i++) {
                grid[count][i] = line.charAt(i);
                if (line.charAt(i) >= '0' && line.charAt(i) <= '7') {
                    rows[line.charAt(i) - '0'] = count;
                    cols[line.charAt(i) - '0'] = i;
                }
            }
            count++;
        }
        for(int i=0;i<8;i++) {
            System.out.println(i + ": " + rows[i] + "," + cols[i]);
        }

        int[][] distances = new int[8][8];

        for(int i=0;i<=7;i++) {
            for(int j=0;j<=7;j++) {
                if (i!=j) {
                    //System.out.println(i + " distance to " + j + " " + distanceTo(i,j, grid, rows, cols));
                    distances[i][j] = distanceTo(i,j, grid, rows, cols);
                }
            }
        }
        int shortest = Integer.MAX_VALUE;
        ArrayList<String> allPossible = permutation("1234567");
        for (String string : allPossible) {
            int distance = 0;
            int currentNode = 0;
            string = string + '0';
            for(char c : string.toCharArray()) {
                distance += distances[currentNode][c - '0'];
                currentNode = c - '0';
            }
            if (distance < shortest) {
                shortest = distance;
            }
        }
        System.out.println(shortest);
    }

    private static int distanceTo(int i, int j, char[][] grid, int[] rows, int[] cols) {
        int distance = 0;
        int start = rows[i]*NUM_COLS + cols[i];
        int end = rows[j]*NUM_COLS + cols[j];
        HashSet<Integer> visited = new HashSet<Integer>();
        ArrayList<Pair<Integer,Integer>> queue = new ArrayList<Pair<Integer, Integer>>();
        queue.add(new Pair(start,0));
        visited.add(start);
        while(!queue.isEmpty()) {
            Pair<Integer,Integer> spotDistance = queue.remove(0);
            if(spotDistance.getKey() == end) {
                distance = spotDistance.getValue();
                break;
            }
            int r = spotDistance.getKey()/NUM_COLS;
            int c = spotDistance.getKey()%NUM_COLS;
            int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};
            for(int[] delta : deltas) {
                int newR = r + delta[0];
                int newC = c + delta[1];
                if(newR >= 0 && newR <NUM_ROWS &&
                        newC >=0 && newC < NUM_COLS) {
                    int newSpot = newR*NUM_COLS + newC;
                    if (grid[newR][newC] != '#' && !visited.contains(newSpot)) {
                        queue.add(new Pair(newSpot, spotDistance.getValue() + 1));
                        visited.add(newSpot);
                    }
                }
            }
        }
        if (queue.isEmpty()) {
            distance = -1;
        }
        return distance;
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

}
