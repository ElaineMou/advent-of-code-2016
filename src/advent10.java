import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Elaine on 12/10/2016.
 */
public class advent10 {
    static HashMap<Integer, int[]> bots = new HashMap<Integer, int[]>();
    static HashMap<Integer, ArrayList<Integer>> outputs = new HashMap<Integer, ArrayList<Integer>>();

    public static void main (String[] args) throws FileNotFoundException {
        Scanner scanner;
        scanner = new Scanner(new FileInputStream("src/input.txt"));
        int count =0;
        while(count < 31) {
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");

            if (parts[0].equals("value")) {
                int val = Integer.parseInt(parts[1]);
                int bot = Integer.parseInt(parts[5]);
                if (!bots.containsKey(bot)) {
                    bots.put(bot, new int[2]);
                }
                int[] vals = bots.get(bot);
                vals[0] = val;
                Arrays.sort(vals);
                printBot(bot);
                if (isDroid(vals)) {
                    System.out.println(bot);
                    //break;
                }
            } else if (parts[0].equals("bot")){
                int giverNum = Integer.parseInt(parts[1]);
                int lowRecNum = Integer.parseInt(parts[6]);
                if (!bots.containsKey(giverNum)) {
                    bots.put(giverNum, new int[2]);
                }
                int[] giver = bots.get(giverNum);
                printBot(giverNum);
                if (isDroid(giver)) {
                    System.out.println(giverNum);
                    //break;
                }

                if (parts[5].equals("bot")) {
                    if (!bots.containsKey(lowRecNum)) {
                        bots.put(lowRecNum, new int[2]);
                    }
                    int[] lowBot = bots.get(lowRecNum);
                    lowBot[0] = giver[0] == 0 ? giver[1] : giver[0];
                    Arrays.sort(lowBot);
                    printBot(lowRecNum);
                    if (isDroid(lowBot)) {
                        System.out.println(lowRecNum);
                        //break;
                    }
                } else if (parts[5].equals("output")) {
                    if (!outputs.containsKey(lowRecNum)) {
                        outputs.put(lowRecNum, new ArrayList<Integer>());
                    }
                    ArrayList<Integer> lowOut = outputs.get(lowRecNum);
                    lowOut.add(giver[0] == 0 ? giver[1] : giver[0]);
                }
                if (giver[0] == 0) {
                    giver[1] = 0;
                } else {
                    giver[0] = 0;
                }
                Arrays.sort(giver);
                if (isDroid(giver)) {
                    System.out.println(giverNum);
                    //break;
                }

                if (parts.length > 7) {
                    int highRecNum = Integer.parseInt(parts[11]);
                    if (parts[10].equals("bot")) {
                        if (!bots.containsKey(highRecNum)) {
                            bots.put(highRecNum, new int[2]);
                        }
                        int[] highBot = bots.get(highRecNum);
                        highBot[0] = giver[1];
                        Arrays.sort(highBot);
                        printBot(highRecNum);
                        if (isDroid(highBot)) {
                            System.out.println(highRecNum);
                            //break;
                        }
                    } else if (parts[10].equals("output")) {
                        if (!outputs.containsKey(highRecNum)) {
                            outputs.put(highRecNum, new ArrayList<Integer>());
                        }
                        ArrayList<Integer> highOut = outputs.get(highRecNum);
                        highOut.add(giver[1]);
                    }
                    giver[1] = 0;
                    Arrays.sort(giver);
                }
            }
            if (!scanner.hasNextLine()) {
                scanner = new Scanner(new FileInputStream("src/input.txt"));
                count++;
            }
        }
        int a=0;
        int b=0;
        int c=0;
        for(int num : outputs.get(0)) {
            if (num > 0) {
                a = num;
                //break;
            }
        }
        for(int num : outputs.get(1)) {
            if (num > 0) {
                b = num;
                //break;
            }
        }
        for(int num : outputs.get(2)) {
            if (num > 0) {
                c = num;
                //break;
            }
        }
        System.out.println(a + " " + b + " " + c + ":" + a*b*c);
    }

    public static boolean isDroid(int[] bot) {
        return bot[0] == 17 && bot[1] == 61;
    }
    public static void printBot(int botNum) {
        //System.out.print(botNum + ": ");
        //int[] bot = bots.get(botNum);
        //System.out.println(bot[0] + " " + bot[1]);
    }
}
