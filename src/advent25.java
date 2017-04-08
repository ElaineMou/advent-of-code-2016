
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Elaine on 12/24/2016.
 */

/**
 * cpy a d
 cpy 15 c
 cpy 170 b
 mlt c b
 add b d
 cpy 0 b
 cpy 0 c


 */
public class advent25 {
    public static void main (String[] args) {
        ArrayList<String> instructions = new ArrayList<String>();
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileInputStream("src/input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            instructions.add(line);
        }

        for(int j=1;j<500;j++) {
            String output = "";
            int[] registers = new int[4];
            registers[0] = j;

            for (int i = 0; i < instructions.size(); i++) {
                //System.out.println(instructions.get(i));
                String[] parts = instructions.get(i).split("\\s+");
                if (parts[0].equals("cpy")) {
                    if (!parts[2].matches("-?\\d+")) {
                        char register = parts[2].charAt(0);

                        if (parts[1].matches("-?\\d+")) {
                            registers[register - 'a'] = Integer.parseInt(parts[1]);
                        } else {
                            char register2 = parts[1].charAt(0);
                            registers[register - 'a'] = registers[register2 - 'a'];
                        }
                    }
                } else if (parts[0].equals("inc")) {
                    char register = parts[1].charAt(0);
                    registers[register - 'a']++;
                } else if (parts[0].equals("dec")) {
                    char register = parts[1].charAt(0);
                    registers[register - 'a']--;
                } else if (parts[0].equals("jnz")) {
                    int num;
                    if (parts[1].matches("-?\\d+")) {
                        num = Integer.parseInt(parts[1]);
                    } else {
                        char register = parts[1].charAt(0);
                        num = registers[register - 'a'];
                    }
                    if (num != 0) {
                        if (parts[2].matches("-?\\d+")) {
                            i += Integer.parseInt(parts[2]) - 1;
                        } else {
                            i += registers[parts[2].charAt(0) - 'a'] - 1;
                        }
                    }
                } else if (parts[0].equals("out")) {
                    int num;
                    if (parts[1].matches("-?\\d+")) {
                        num = Integer.parseInt(parts[1]);
                    } else {
                        char register = parts[1].charAt(0);
                        num = registers[register - 'a'];
                    }
                    output = output + num;
                    if (output.length() >= 10 || (output.length() >= 10 && !(output.startsWith("0101")))) {
                        break;
                    }
                }
            }
            if (output.equals("0101010101")) {
                System.out.println("HOLY SHIT");
            }
            System.out.println(j + ": " + output);
        }


        //System.out.println(registers[0]);
    }
}
