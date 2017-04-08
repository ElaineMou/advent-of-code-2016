import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by Elaine on 12/22/2016.
 */
public class advent23 {
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
        registers[0] = 12;

        for(int i=0;i<instructions.size();i++) {
            System.out.println(registers[0] + " " + registers[1] + " " + registers[2] + " " + registers[3]);
            System.out.println(instructions.get(i));
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
            } else if (parts[0].equals("mlt")) {
                char register = parts[2].charAt(0);

                if (parts[1].matches("-?\\d+")) {
                    registers[register - 'a'] *= Integer.parseInt(parts[1]);
                } else {
                    char register2 = parts[1].charAt(0);
                    registers[register - 'a'] *= registers[register2 - 'a'];
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
                } else{
                    char register = parts[1].charAt(0);
                    num = registers[register - 'a'];
                }
                if (num!=0) {
                    if (parts[2].matches("-?\\d+")) {
                        i += Integer.parseInt(parts[2]) - 1;
                    } else {
                        i += registers[parts[2].charAt(0) - 'a'] - 1;
                    }
                }
            } else if (parts[0].equals("tgl")) {
                int num;
                if (parts[1].matches("-?\\d+")) {
                    num = Integer.parseInt(parts[1]);
                } else{
                    char register = parts[1].charAt(0);
                    num = registers[register - 'a'];
                }
                if (i+num < 0 || i+num >= instructions.size()) {
                    continue;
                }
                String[] nextParts = instructions.get(i+num).split("\\s+");
                String replacement=null;
                if (nextParts[0].equals("inc")) {
                    replacement = "dec " + nextParts[1];
                } else if (nextParts.length == 2) {
                    replacement = "inc " + nextParts[1];
                } else if (nextParts[0].equals("jnz")) {
                    replacement = "cpy " + nextParts[1] + " " + nextParts[2];
                } else if (nextParts.length == 3) {
                    replacement = "jnz " + nextParts[1] + " " + nextParts[2];
                }
                instructions.set(i+num, replacement);
            }
        }

        System.out.println(registers[0]);
    }
}
