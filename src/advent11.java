import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

/**
 * Created by Elaine on 12/10/2016.
 */
public class advent11 {
    public static String[] contents = new String[] {"SG", "SM", "PG", "PM", "TG", "RG", "RM", "CG", "CM", "TM", null};

    public static void main (String[] args) throws FileNotFoundException {
        String floors = "00001111120";

        /*HashSet<String>[] floors = new HashSet[4];
        for(int i=0;i<4;i++) {
            floors[i] = new HashSet<String>();
        }
        PriorityQueue<Plan> plans = new PriorityQueue<Plan>(new Comparator<Plan>() {
            @Override
            public int compare(Plan o1, Plan o2) {
                int priority1 = (o1.floors[0].size()-1)*6+ (o1.floors[1].size()-1) * 4 + (o1.floors[2].size()-1)*2;
                int priority2 = (o2.floors[0].size()-1)*6 + (o2.floors[1].size()-1) * 4 + (o2.floors[2].size()-1)*2;
                return Integer.compare(priority1,priority2);
            }
        });
        //ArrayList<Plan> plans = new ArrayList<Plan>();
        int currentFloor = 0;
        /*floors[0].add("SG");
        floors[0].add("SM");
        floors[0].add("PG");
        floors[0].add("PM");
        floors[0].add(null);

        floors[1].add("TG");
        floors[1].add("RG");
        floors[1].add("RM");
        floors[1].add("CG");
        floors[1].add("CM");
        floors[1].add(null);

        floors[2].add("TM");
        floors[2].add(null);

        floors[3].add(null);

        floors[0].add("HM");
        floors[0].add("LM");
        floors[0].add(null);
        floors[1].add("HG");
        floors[1].add(null);
        floors[2].add("LG");
        floors[2].add(null);
        floors[3].add(null);

        plans.add(new Plan(floors,currentFloor, 0, null));
        while(!plans.isEmpty()) {
            Plan plan = plans.poll();
            //Plan plan = plans.remove(0);
            System.out.println("Not done: " + plan.movesSoFar);
            if (isDone(plan.floors)) {
                System.out.println("Done: " + plan.movesSoFar);
                break;
            } else {
                plans.addAll(possibleMoves(plan));
            }
        }*/
    }

    private static boolean isDone(HashSet<String>[] plan) {
        for(int i=0;i<plan.length-1;i++) {
            if (!plan[i].isEmpty()) {
                if (plan[i].size() == 1 && plan[i].contains(null)) {

                } else {
                    return false;
                }
            }
        }
        return true;
    }

    private static class Plan {
        HashSet<String>[] floors = new HashSet[4];
        int currentFloor;
        int movesSoFar;
        Plan previousPlan;
        Plan (HashSet<String>[] floors, int currentFloor, int movesSoFar, Plan previous) {
            this.floors = new HashSet[4];
            for(int i=0;i<floors.length;i++) {
                this.floors[i] = new HashSet<String>();
                this.floors[i].addAll(floors[i]);
            }
            this.currentFloor = currentFloor;
            this.movesSoFar = movesSoFar;
            this.previousPlan = previous;
        }
        Plan(Plan toCopy) {
            this.floors = new HashSet[4];
            for(int i=0;i<toCopy.floors.length;i++) {
                this.floors[i] = new HashSet<String>();
                this.floors[i].addAll(toCopy.floors[i]);
            }
            this.currentFloor = toCopy.currentFloor;
            this.movesSoFar = toCopy.movesSoFar+1;
            this.previousPlan = toCopy;
        }
        boolean equals(Plan other) {
            if (other == null) {
                return false;
            }
            for(int i=0;i<floors.length;i++) {
                if (!this.floors[i].equals(other.floors[i])) {
                    return false;
                }
            }
            return currentFloor == other.currentFloor;
        }
        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb.append("CurrFloor: " + currentFloor + "\n");
            sb.append("Moves : " + movesSoFar + "\n");
            for(int i=0;i<4;i++) {
                for(String item : floors[i]) {
                    sb.append(item + " ");
                }
                sb.append("\n");
            }
            sb.append("\n");
            return sb.toString();
        }
    }

    public static ArrayList<Plan> possibleMoves(Plan plan) {
        ArrayList<Plan> plans = new ArrayList<Plan>();
        for(int i=0;i<4;i++) {
            if (i!= plan.currentFloor) {
                for(String item1 : plan.floors[plan.currentFloor]) {
                    for (String item2 : plan.floors[plan.currentFloor]) {
                        if (item1 != item2) {
                            Plan newPlan = new Plan(plan);
                            newPlan.floors[plan.currentFloor].remove(item1);
                            newPlan.floors[plan.currentFloor].remove(item2);
                            newPlan.floors[i].add(item1);
                            newPlan.floors[i].add(item2);
                            newPlan.currentFloor = i;

                            if (planSafe(newPlan) && !newPlan.equals(plan.previousPlan)) {
                                plans.add(newPlan);
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
        for (HashSet<String> floor : plan.floors) {
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
                boolean thisSafe = hasRTG;
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
