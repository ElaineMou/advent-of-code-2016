import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Elaine on 12/14/2016.
 */
public class advent15 {
    public static void main (String[] args) {
        int[] numPositions = {13, 17, 19, 7, 5, 3, 11};
        int[] originalPositions = {10, 15, 17, 1, 0, 1, 0};
        int[] positions;
        boolean done = false;
        int start = 0;
        while(!done) {
            positions = originalPositions.clone();
            boolean drops = true;
            for(int j=0;j<positions.length;j++) {
                positions[j] = (positions[j] + start) % numPositions[j];
            }
            for(int i=0;i<numPositions.length;i++) {
                for(int j=0;j<positions.length;j++) {
                    positions[j] = (positions[j] + 1) % numPositions[j];
                }
                if (!(positions[i] == 0)) {
                    System.out.println(positions[i]);
                    drops = false;
                    break;
                }
            }
            if (drops) {
                done = true;
            } else {
                start++;
            }
        }
        System.out.println(start);
    }
}
