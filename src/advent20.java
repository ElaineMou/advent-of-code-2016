import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Created by Elaine on 12/20/2016.
 */
public class advent20 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("src/input.txt"));
        byte[] flags = new byte[(int) (Math.pow(2,32) / 8)];
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] parts = line.split("-");
            long low = Long.parseLong(parts[0]);
            long high = Long.parseLong(parts[1]);
            for(long i=low;i<=high;i++) {
                set(i,flags);
            }
        }
        long allowed = 0;
        for(int i=0;i<flags.length;i++) {
            if(flags[i] != 255) {
                int mask = 1 << 7;
                for(int j=0;j<8;j++) {
                    if ((flags[i] & mask) == 0) {
                        allowed++;
                    }
                    mask = mask >> 1;
                }
            }
        }
        System.out.println(allowed);
    }

    static void set(long index, byte[] flags) {
        int numByte = (int)(index / 8);
        int mask = 1 << (7 - index % 8);
        flags[numByte] |= mask;
    }
}
