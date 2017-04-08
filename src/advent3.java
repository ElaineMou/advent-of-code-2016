import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by Elaine on 12/2/2016.
 */

public class advent3 {
    public static void main (String[] args) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new FileInputStream("src/input.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        int triangles = 0;
        while(scanner.hasNextLine()) {
           String line1 = scanner.nextLine();
            String line2 = scanner.nextLine();
            String line3 = scanner.nextLine();
            String[] nums1 = line1.split("\\s+");
            String[] nums2 = line2.split("\\s+");
            String[] nums3 = line3.split("\\s+");

            int a = Integer.parseInt(nums1[1]);
            int b = Integer.parseInt(nums2[1]);
            int c = Integer.parseInt(nums3[1]);

            if (a + b > c  && b+c > a && c + a > b) {
                triangles++;
            }


             a = Integer.parseInt(nums1[2]);
            b = Integer.parseInt(nums2[2]);
            c = Integer.parseInt(nums3[2]);
            if (a + b > c  && b+c > a && c + a > b) {
                triangles++;
            }


            a = Integer.parseInt(nums1[3]);
            b = Integer.parseInt(nums2[3]);
            c = Integer.parseInt(nums3[3]);
            if (a + b > c  && b+c > a && c + a > b) {
                triangles++;
            }
        }
        System.out.println(triangles);
    }
}
