import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Elaine on 12/7/2016.
 */
public class advent8 {
    public static void main (String[] args) throws FileNotFoundException {
        Scanner scanner;
        scanner = new Scanner(new FileInputStream("src/input.txt"));
        int count = 0;

        boolean[][] screen = new boolean[6][50];

        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            if (parts[0].equals("rect")) {
                String[] dims = parts[1].split("x");
                int r = Integer.parseInt(dims[1]);
                int c = Integer.parseInt(dims[0]);
                for(int i=0;i<r;i++) {
                    for(int j=0;j<c;j++) {
                        screen[i][j] = true;
                    }
                }
            } else if (parts[0].equals("rotate") && parts[1].equals("row")) {
                int row = Integer.parseInt(parts[2].substring(2));
                int shift = Integer.parseInt(parts[4]);
                for(int i=0;i<shift;i++) {
                    rotateRight(screen[row]);
                }
            } else if (parts[0].equals("rotate") && parts[1].equals("column")) {
                int column = Integer.parseInt(parts[2].substring(2));
                int shift = Integer.parseInt(parts[4]);
                for(int i=0;i<shift;i++) {
                    rotateDown(screen, column);
                }
            }
        }

        for(int i=0;i<screen.length;i++) {
            for(int j=0;j<screen[i].length;j++) {
                if (screen[i][j]) {
                    System.out.print("#");
                    count++;
                } else {
                    System.out.print(".");
                }
            }
            System.out.println("");
        }
        System.out.println(count);
    }

    private static void rotateDown(boolean[][] screen, int column) {
        boolean last = screen[screen.length-1][column];
        for(int i=screen.length-1;i>0;i--) {
            screen[i][column] = screen[i-1][column];
        }
        screen[0][column] = last;
    }

    private static void rotateRight(boolean[] row) {
        boolean last = row[row.length-1];
        for(int i=row.length-1;i>0;i--) {
            row[i] = row[i-1];
        }
        row[0] = last;
    }
}
