import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Elaine on 12/10/2016.
 */
public class advent11v2 {
    public static String[] contents = new String[] {"SG", "SM", "PG", "PM", "TG", "RG", "RM", "CG", "CM", "TM","EM", "EG", "DM","DG", null};
    //public static String[] contents = new String[] {"HG","HM","LG","LM",null};
    public static HashSet<Integer> allPastPlans = new HashSet<Integer>();

    public static void main (String[] args) throws FileNotFoundException {
        String floors = "000011111200000";
        //ArrayList<Plan> plans = new ArrayList<Plan>();
        PriorityQueue<Plan> plans = new PriorityQueue<Plan>(new Comparator<Plan>() {
            @Override
            public int compare(Plan o1, Plan o2) {
                int sum1 = 0;
                int sum2 = 0;
                for(char c : o1.floors.toCharArray()) {
                    sum1 += 2*('3' - c);
                }
                for(char c : o2.floors.toCharArray()) {
                    sum2 += 2*('3' - c);
                }
                return Integer.compare(sum1, sum2);
            }
        });
        Plan initial = new Plan(floors,0, 0, null);
        plans.add(initial);
        allPastPlans.add(initial.hashCode());
        while(!plans.isEmpty()) {
            Plan plan = plans.poll();
            System.out.println(plan.movesSoFar);
            if (isDone(plan.floors)) {
                System.out.println("Done: " + plan.movesSoFar);
                break;
            } else {
                HashSet<Plan> possible = possibleMoves(plan);
                plans.addAll(possible);
            }
        }
    }

    private static boolean isDone(String floors) {
        for(int i=0;i<floors.length()-1;i++) {
            if (!(floors.charAt(i) == '3')) {
                return false;
            }
        }
        return true;
    }

    private static class Plan {
        String floors;
        int currentFloor;
        int movesSoFar;
        Plan (String floors, int currentFloor, int movesSoFar, Plan previous) {
            this.floors = floors;
            this.currentFloor = currentFloor;
            this.movesSoFar = movesSoFar;
        }
        Plan(Plan toCopy) {
            this.floors = toCopy.floors;
            this.currentFloor = toCopy.currentFloor;
            this.movesSoFar = toCopy.movesSoFar+1;
        }
        boolean equals(Plan other) {
            if (other == null) {
                return false;
            }
            return this.currentFloor == other.currentFloor;
        }
        public int hashCode() {
            String newString = floors.substring(0,floors.length()-1) + currentFloor;
            return newString.hashCode();
        }
        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb.append("CurrFloor: " + currentFloor + "\n");
            sb.append("Moves : " + movesSoFar + "\n");
            for(int i=3;i>=0;i--) {
                sb.append(i + ": ");
                for(int j=0;j<floors.length();j++) {
                    if (Character.getNumericValue(floors.charAt(j)) == i) {
                        sb.append(contents[j] + " ");
                    }
                }
                sb.append("\n");
            }
            sb.append("\n");
            return sb.toString();
        }
    }

    public static HashSet<Plan> possibleMoves(Plan plan) {
        HashSet<Plan> plans = new HashSet<Plan>();
        for(int i=0;i<contents.length;i++) {
            for (int j=0;j<contents.length;j++) {
                int floor1 = Character.getNumericValue(plan.floors.charAt(i));
                int floor2 = Character.getNumericValue(plan.floors.charAt(j));
                if (i!=j && (floor1 == plan.currentFloor || i == contents.length - 1) &&
                        (floor2 == plan.currentFloor || j == contents.length - 1)) {
                    for(int floor=plan.currentFloor-1;floor<=plan.currentFloor+1;floor+=2) {
                        if (floor >=0 && floor <= 3) {
                            Plan newPlan = new Plan(plan);
                            char[] charArray = newPlan.floors.toCharArray();
                            charArray[i] = (char) ('0' + floor);
                            charArray[j] = (char) ('0' + floor);
                            newPlan.floors = new String(charArray);
                            newPlan.currentFloor = floor;

                            if (planSafe(newPlan) && !allPastPlans.contains(newPlan.hashCode())) {
                                plans.add(newPlan);
                                allPastPlans.add(newPlan.hashCode());
                            }
                        }
                    }
                }
            }
        }
        return plans;
    }

    public static boolean planSafe(Plan plan) {
        boolean safe = true;
        for (int i=0;i<4;i++) {
            HashSet<String> floor = new HashSet<String>();
            for(int j=0;j<plan.floors.length()-1;j++) {
                if (Character.getNumericValue(plan.floors.charAt(j)) == i) {
                    floor.add(contents[j]);
                }
            }
            safe &= safe(floor);
        }
        return safe;
    }

    public static boolean safe(HashSet<String> set) {
        boolean safe = true;
        boolean hasRTG = false;
        for(String item : set) {
            if (item!=null && item.charAt(1) == 'G') {
                hasRTG = true;
                break;
            }
        }

        for(String item : set) {
            if (item!=null && item.charAt(1) == 'M') {
                boolean thisSafe = !hasRTG;
                for(String otherItem : set) {
                    if (item!=otherItem && otherItem !=null) {
                        thisSafe |= otherItem.charAt(0) == item.charAt(0);
                    }
                }
                safe &= thisSafe;
            }
        }
        return safe;
    }
}
