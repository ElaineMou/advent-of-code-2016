import javafx.util.Pair;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Created by Elaine on 12/20/2016.
 */
public class advent20v2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileInputStream("src/input.txt"));
        ArrayList<Pair<Long,Long>> ranges = new ArrayList<Pair<Long, Long>>();

        while(scanner.hasNextLine()) {
            String[] parts = scanner.nextLine().split("-");
            ranges.add(new Pair(Long.parseLong(parts[0]),Long.parseLong(parts[1])));
        }
        ranges.sort(new Comparator<Pair<Long, Long>>() {
            @Override
            public int compare(Pair<Long, Long> o1, Pair<Long, Long> o2) {
                int comp = Long.compare(o1.getKey(),o2.getKey());
                if (comp == 0) {
                    return Long.compare(o1.getValue(), o2.getValue());
                } else {
                    return comp;
                }
            }
        });

        long lowest = Long.MAX_VALUE;
        long highestEdge = 0;
        int count = 0;
        for(int i=0;i<ranges.size()-1;i++) {
            Pair<Long, Long> range = ranges.get(i);
            Pair<Long, Long> range2 = ranges.get(i+1);
            if(range2.getKey() <= range.getValue() + 1) {
                highestEdge = Math.max(highestEdge,Math.max(range.getValue(), range2.getValue()));
                continue;
            }
            if (range2.getKey() > highestEdge) {
                count += (range2.getKey() - highestEdge) - 1;
            }
            if (range.getValue() + 1 < lowest && highestEdge <= range.getValue()) {
                lowest = range.getValue() + 1;
            }
            highestEdge = Math.max(highestEdge,range2.getValue());
        }
        System.out.println(lowest);
        System.out.println(count);
    }
}
