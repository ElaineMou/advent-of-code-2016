
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Elaine on 12/5/2016.
 */
public class advent5 {
    public static void main(String[] args) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String input = "ugkcyxxp";
        int index = 0;
        boolean[] decrypted = new boolean[8];
        char[] password = new char[8];
        boolean done = false;
        MessageDigest md = MessageDigest.getInstance("MD5");

        while (!done) {
            String toHash = input + index;
            index++;
            byte[] bytes = toHash.getBytes("UTF-8");
            byte[] digest = md.digest(bytes);
            StringBuilder sb = new StringBuilder();
            for (int i=0;i<4;i++) {
                sb.append(String.format("%02x",digest[i]));
            }
            String hash = sb.toString();
            if (hash.startsWith("00000")) {
                System.out.println(hash);
                int position = Character.getNumericValue(hash.charAt(5));
                if (position < 8 && !decrypted[position]) {
                    char value = hash.charAt(6);
                    password[position] = value;
                    decrypted[position] = true;
                }
                done = true;
                for(int i=0;i<decrypted.length;i++) {
                    done &= decrypted[i];
                }
            }
        }
        System.out.println(new String(password));
    }
}
