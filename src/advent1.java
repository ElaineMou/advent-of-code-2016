/**
 * Created by Elaine on 11/30/2016.
 */
public class advent1 {
    public static void main (String[] args) {
        String input = "L3, R1, L4, L1, L2, R4, L3, L3, R2, R3, L5, R1, R3, L4, L1, L2, R2, R1, L4, L4, R2, L5, R3, R2, R1, L1, L2, R2, R2, L1, L1, R2, R1, L3, L5, R4, L3, R3, R3, L5, L190, L4, R4, R51, L4, R5, R5, R2, L1, L3, R1, R4, L3, R1, R3, L5, L4, R2, R5, R2, L1, L5, L1, L1, R78, L3, R2, L3, R5, L2, R2, R4, L1, L4, R1, R185, R3, L4, L1, L1, L3, R4, L4, L1, R5, L5, L1, R5, L1, R2, L5, L2, R4, R3, L2, R3, R1, L3, L5, L4, R3, L2, L4, L5, L4, R1, L1, R5, L2, R4, R2, R3, L1, L1, L4, L3, R4, L3, L5, R2, L5, L1, L1, R2, R3, L5, L3, L2, L1, L4, R4, R4, L2, R3, R1, L2, R1, L2, L2, R3, R3, L1, R4, L5, L3, R4, R4, R1, L2, L5, L3, R1, R4, L2, R5, R4, R2, L5, L3, R4, R1, L1, R5, L3, R1, R5, L2, R1, L5, L2, R2, L2, L3, R3, R3, R1";
        String[] directions = input.split(", ");
        int x = 0;
        int y = 0;
        int direction = 0;
        boolean[][] map = new boolean[500][500];
        map[x+250][y+250] = true;
        boolean done = false;

        for(String instruction : directions) {
            if (instruction.charAt(0) == 'R') {
                direction = (direction + 1) % 4;
            } else {
                direction = (direction == 0) ? 3 : direction - 1;
            }
            int steps = Integer.parseInt(instruction.substring(1));
            for(int i=1;i<=steps;i++) {
                switch(direction) {
                    case 0:
                        y++;
                        break;
                    case 1:
                        x++;
                        break;
                    case 2:
                        y--;
                        break;
                    case 3:
                        x--;
                }
                if (map[x+250][y+250]) {
                    System.out.println("x: " + x + " y: " + y);
                    done = true;
                    break;
                }
                map[x+250][y+250] = true;
            }
            if (done) {
                break;
            }
        }
    }
}
