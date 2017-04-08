import javafx.util.Pair;

import java.util.ArrayList;

/**
 * Created by Elaine on 12/12/2016.
 */
public class advent13 {
    private static final int MAZE_SIZE = 100;

    public static void main (String[] args) {
        int favorite = 1362;
        char[][] maze = new char[MAZE_SIZE][MAZE_SIZE];

        for(int i=0;i<maze.length;i++) {
            for(int j=0;j<maze[i].length;j++) {
                int sum = j*j + 3*j + 2*i*j + i + i*i;
                sum += favorite;
                int numOnes = Integer.bitCount(sum);
                if (numOnes %2 ==0) {
                    maze[i][j] = '.';
                } else {
                    maze[i][j] = '#';
                }
            }
        }
        maze[39][31] = '*';
        //maze[4][7] = '*';

        int[][] visited = new int[MAZE_SIZE][MAZE_SIZE];

        int count=0;
        ArrayList<Pair<Integer,Integer>> queue = new ArrayList<Pair<Integer, Integer>>();
        queue.add(new Pair<Integer, Integer>(1,1));
        while(!queue.isEmpty()) {
            Pair<Integer,Integer> coords = queue.remove(0);
            int x = coords.getKey();
            int y = coords.getValue();
            if (visited[y][x] > 50) {
                break;
            }
            count++;
            int[][] neighbors = {{-1,0},{1,0},{0,-1},{0,1}};
            for(int i=0;i<neighbors.length;i++) {
                int newX = x + neighbors[i][1];
                int newY = y + neighbors[i][0];
                if (newX >=0 && newX <maze.length &&
                        newY >=0 && newY <maze.length &&
                        visited[newY][newX] ==0 && maze[newY][newX] != '#') {
                    queue.add(new Pair(newX, newY));
                    visited[newY][newX] = visited[y][x] + 1;
                }
            }
        }

        System.out.println(count);
    }
}
