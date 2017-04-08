/**
 * Created by Elaine on 12/20/2016.
 */
public class advent18 {
    public static void main(String[] args) {
        int numRows = 400000;
        String input = ".^^^^^.^^^..^^^^^...^.^..^^^.^^....^.^...^^^...^^^^..^...^...^^.^.^.......^..^^...^.^.^^..^^^^^...^.";
        String[] rows = new String[numRows];
        rows[0] = input;
        int numCols = input.length();
        for(int i=1;i<numRows;i++) {
            StringBuffer sb = new StringBuffer();
            sb.append(tile('.',rows[i-1].charAt(0),rows[i-1].charAt(1)));
            for(int j=1;j<numCols-1;j++) {
                sb.append(tile(rows[i-1].charAt(j-1),rows[i-1].charAt(j),rows[i-1].charAt(j+1)));
            }
            sb.append(tile(rows[i-1].charAt(numCols-2),rows[i-1].charAt(numCols-1),'.'));
            rows[i] = sb.toString();
        }

        int numSafe = 0;
        for(String row : rows) {
            for(char c : row.toCharArray()) {
                if (c == '.') {
                    numSafe++;
                }
            }
        }
        System.out.println(numSafe);
    }
    static char tile(char left, char center, char right) {
        if (left != center && center == right) {
            return '^';
        }
        if (left == center && center != right) {
            return '^';
        }
        return '.';
    }
}
