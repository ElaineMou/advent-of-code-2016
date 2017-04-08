import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by Elaine on 12/23/2016.
 */
public class advent19v2 {
    public static void main (String[] args) {
        int numElves = 3014387;
        /*Elf firstElf = new Elf(1);
        Elf currElf = firstElf;
        for(int i=0;i<numElves-1;i++) {
            currElf.next = new Elf(i+2);
            currElf = currElf.next;
        }
        currElf.next = firstElf;

        currElf = firstElf;
        while(currElf.next != currElf) {
            currElf.next = currElf.next.next;
            currElf = currElf.next;
        }
        System.out.println(currElf.number);*/


        //q = [1..n]; while(len(q) > 0) { q.push(q.pop()); q.pop(); }
        /*for(int j=1;j<=40;j++) {
            LinkedList<Integer> elves = new LinkedList<Integer>();
            for (int i = 1; i <= j; i++) {
                elves.add(i);
            }
            while (elves.size() > 2) {
                elves.add(elves.remove(0));
                elves.remove(0);
            }
            System.out.println(j + ":" + elves.get(0));
        }*/
        //System.out.println(2*(numElves %(1 << (int)Math.floor(Math.log(numElves)/Math.log(2)))) + 1);

        LinkedList<Integer> firstHalf = new LinkedList<Integer>();
        for(int i=1;i<=numElves/2;i++) {
            firstHalf.add(i);
        }
        LinkedList<Integer> secondHalf = new LinkedList<Integer>();
        for(int i=numElves/2 + 1;i<=numElves;i++) {
            secondHalf.add(i);
        }
        while(firstHalf.size() + secondHalf.size() > 1) {
            secondHalf.remove(0);
            secondHalf.add(firstHalf.remove(0));
            while(secondHalf.size() - firstHalf.size() > 1) {
                firstHalf.add(secondHalf.remove(0));
            }
        }
        System.out.println(firstHalf);
        System.out.println(secondHalf);

    }

    private static class Elf {
        int number;
        Elf(int num) {
            this.number = num;
        }
        Elf next;
    }
}
