import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Elaine on 12/11/2016.
 */
public class advent12 {
    public static void main (String[] args) {
        ArrayList<String> instructions = new ArrayList<String>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileInputStream("src/input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(scanner.hasNextLine()) {
            String line = scanner.nextLine();
            instructions.add(line);
        }
        int[] registers = new int[4];
        registers[2] = 1;

        for(int i=0;i<instructions.size();i++) {
            String[] parts = instructions.get(i).split(" ");
            if (parts[0].equals("cpy")) {
                char register = parts[2].charAt(0);

                if (parts[1].matches("\\d+")) {
                    registers[register - 'a'] = Integer.parseInt(parts[1]);
                } else {
                    char register2 = parts[1].charAt(0);
                    registers[register - 'a'] = registers[register2 - 'a'];
                }
            } else if (parts[0].equals("inc")) {
                char register = parts[1].charAt(0);
                registers[register - 'a']++;
            } else if (parts[0].equals("dec")) {
                char register = parts[1].charAt(0);
                registers[register - 'a']--;
            } else if (parts[0].equals("jnz")) {
                if (parts[1].matches("-?\\d+")) {
                    int num = Integer.parseInt(parts[1]);
                    if (num!=0) {
                        System.out.println(parts[2]);
                        i += Integer.parseInt(parts[2]) - 1;
                    }
                } else{
                    char register = parts[1].charAt(0);
                    if (registers[register - 'a'] != 0) {
                        i += Integer.parseInt(parts[2]) - 1;
                    }
                }
            }
        }

        System.out.println(registers[0]);
    }
}
