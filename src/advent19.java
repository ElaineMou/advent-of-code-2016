import java.util.ArrayList;

/**
 * Created by Elaine on 12/20/2016.
 */
public class advent19 {
    public static void main (String[] args) {
        int numElves = 3014387;
        ArrayList<Integer> elves = new ArrayList<Integer>(numElves);
        int[] numPresents = new int[numElves];
        for(int i=0;i<numElves;i++) {
            elves.add(i);
            numPresents[i] = 1;
        }
        int i=0;
        while(elves.size() > 1) {
            int currElf = elves.get(i);
            int stealingFrom = (i+(elves.size() / 2)) % elves.size();
            numPresents[elves.get(i)] += numPresents[elves.get(stealingFrom)];
            numPresents[elves.get(stealingFrom)] = 0;
            System.out.println((elves.get(i) + 1) + ": " + numPresents[elves.get(i)]);
            if (numPresents[elves.get(i)] == numElves) {
                System.out.println(elves.get(i) + 1);
                return;
            }
            elves.remove(stealingFrom);
            if (i >= elves.size()) {
                i = 0;
            }
            if(elves.get(i) == currElf) {
                i = (i+1) % elves.size();
            }
        }
        System.out.println(elves.get(0));
    }
}
