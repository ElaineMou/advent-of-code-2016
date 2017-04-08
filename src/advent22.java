import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Elaine on 12/21/2016.
 */
public class advent22 {
    public static void main(String[] args) throws FileNotFoundException, NoSuchAlgorithmException {
        Scanner scanner = new Scanner(new FileInputStream("src/input.txt"));
        int NUM_ROWS = 28;
        int NUM_COLS = 38;
        int count = 0;
        int numNode = 0;
        scanner.nextLine();
        scanner.nextLine();
        Node[][] grid = new Node[NUM_ROWS][NUM_COLS];
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            // String nodeName = parts[0];
            // String[] segments = nodeName.split("/");
            // segments = segments[3].split("-");
            // int x = Integer.parseInt(segments[1].substring(1));
            // int y = Integer.parseInt(segments[2].substring(1));

            int size = Integer.parseInt(parts[1].substring(0,parts[1].length()-1));
            int used = Integer.parseInt(parts[2].substring(0,parts[2].length()-1));
            int avail = Integer.parseInt(parts[3].substring(0,parts[3].length()-1));
            int usePerc = Integer.parseInt(parts[4].substring(0,parts[4].length()-1));

            grid[numNode%NUM_ROWS][numNode/NUM_ROWS] = new Node(size, used, avail, usePerc);
            numNode++;
        }

        char[][] symbols = new char[NUM_ROWS][NUM_COLS];
        for(int i=0;i<grid.length;i++) {
            for(int j=0;j<grid[i].length;j++) {
                /*if (grid[i][j].used == 0) {
                    System.out.print("_");
                    symbols[i][j] = '_';
                } else if (grid[i][j].used > 200) {
                    System.out.print("#");
                    symbols[i][j] = '#';
                } else {
                    System.out.print(".");
                    symbols[i][j] = '.';
                }
                System.out.print(" ");*/
                System.out.print(String.format("%3d",grid[i][j].used) + " ");
            }
            System.out.println("");
        }
        for(int i=0;i<symbols.length;i++) {
            for(int j=0;j<symbols[i].length;j++) {
                symbols[i][j] = '#';
            }
        }
        symbols[0][NUM_COLS-2] = '_';
        symbols[0][NUM_COLS-1] = 'G';

        int x = 34;
        int y = 26;
        int moves = (x - 8) + 3 + (36 - 8) + (y - 3) + 36*5 + 1;
        System.out.println(moves);

    }
    private static class Node {
        int size;
        int used;
        int avail;
        int usePerc;
        public Node(int size, int used, int avail, int usePerc) {
            this.size = size;
            this.used = used;
            this.avail = avail;
            this.usePerc = usePerc;
        }
    }
}
