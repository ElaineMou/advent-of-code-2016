import javafx.util.Pair;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/**
 * Created by Elaine on 12/20/2016.
 */
public class advent17 {
    static int[][] deltas = {{-1,0},{1,0},{0,-1},{0,1}};
    static int NUM_ROWS = 4;
    static int NUM_COLS = 4;
    static String input = "vkjiggvb";
    public static void main(String[] args) throws NoSuchAlgorithmException {
        ArrayList<Pair<Integer,String>> toVisit = new ArrayList<Pair<Integer, String>>();
        toVisit.add(new Pair(0,""));
        MessageDigest md = MessageDigest.getInstance("MD5");

        while(!toVisit.isEmpty()) {
            Pair pair = toVisit.remove(0);
            String pathSoFar = (String) pair.getValue();
            String combined = input + pathSoFar;
            int r = (Integer) pair.getKey() / NUM_COLS;
            int c = (Integer) pair.getKey() % NUM_ROWS;
            String hash = bytesToHex(md.digest(combined.getBytes()));
            for(int i=0;i<4;i++) {
                if(open(hash.charAt(i))) {
                    int nextR = r + deltas[i][0];
                    int nextC = c + deltas[i][1];
                    if (nextR >=0 && nextR < NUM_ROWS && nextC >=0 && nextC < NUM_COLS) {
                        if (nextR == NUM_ROWS - 1 && nextC == NUM_COLS - 1) {
                            System.out.println(pathSoFar.length() + 1);
                        } else {
                            toVisit.add(new Pair(roomNumber(nextR, nextC), pathSoFar + direction(i)));
                        }
                    }
                }
            }
        }

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
    static int roomNumber(int r, int c) {
        return r*NUM_COLS + c;
    }
    static boolean open(char c ) {
        return c == 'B' || c == 'C' || c == 'D' || c == 'E' || c == 'F';
    }

    public static String bytesToHex(byte[] bytes) {
        final char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for ( int j = 0; j < bytes.length; j++ ) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
