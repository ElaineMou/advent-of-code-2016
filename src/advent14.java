import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

/**
 * Created by Elaine on 12/13/2016.
 */
public class advent14 {

    public static void main (String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        int count = 0;
        String salt = "qzyelonm";
        MessageDigest md = MessageDigest.getInstance("MD5");
        int index = 0;
        HashMap<Integer, String> indexToHash = new HashMap<Integer, String>();

        while(count < 64) {
            byte[] digest = md.digest((salt + index).getBytes("UTF-8"));
            for(int i=0;i<2016;i++) {
                digest = md.digest(bytesToHex(digest).getBytes("UTF-8"));
            }
            /*StringBuilder sb = new StringBuilder();
            for (int i=0;i<digest.length;i++) {
                sb.append(String.format("%02x",digest[i] & 0xff));
            }*/
            String hash = bytesToHex(digest);
            char triple = hasTriple(hash);
            if(triple != '.') {
                for(int i=index+1;i<index+1001;i++) {
                    String hash2 = indexToHash.get(i);
                    if (hash2 == null) {
                        byte[] digest2 = md.digest((salt + i).getBytes("UTF-8"));
                        for(int j=0;j<2016;j++) {
                            /*StringBuilder sb2 = new StringBuilder();
                            for (int k=0;k<digest2.length;k++) {
                                sb2.append(String.format("%02x",digest2[k] & 0xff));
                            }
                            digest2 = md.digest(sb2.toString().getBytes("UTF-8"));*/
                            digest2 = md.digest(bytesToHex(digest2).getBytes("UTF-8"));
                        }
                        /*StringBuilder sb2 = new StringBuilder();
                        for (int j = 0; j < digest2.length; j++) {
                            sb2.append(String.format("%02x", digest2[j] & 0xff));
                        }
                        hash2 = sb2.toString();*/
                        hash2 = bytesToHex(digest2);
                        indexToHash.put(i, hash2);
                    }
                    if (hasFive(hash2, triple)) {
                        count++;
                        System.out.println(count);
                        break;
                    }
                }
            }
            System.out.println(index);
            index++;
        }

        System.out.println(index-1);
    }

   public static char hasTriple(String hash) {
        for(int i=0;i<hash.length()-2;i++) {
            if (hash.charAt(i) == hash.charAt(i+1) && hash.charAt(i+1) == hash.charAt(i+2)) {
                return hash.charAt(i);
            }
        }
        return '.';
    }


    public static boolean hasFive(String hash, char match) {
        for(int i=0;i<hash.length()-4;i++) {
            if (hash.charAt(i) == match && hash.charAt(i) == hash.charAt(i+1) && hash.charAt(i+1) == hash.charAt(i+2) &&
                    hash.charAt(i+2) == hash.charAt(i+3) && hash.charAt(i+3) == hash.charAt(i+4)) {
                return true;
            }
        }
        return false;
    }

    public static String bytesToHex(byte[] bytes) {
        final char[] hexArray = {'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};
        char[] hexChars = new char[bytes.length * 2];
        int v;
        for ( int j = 0; j < bytes.length; j++ ) {
            v = bytes[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }
        return new String(hexChars);
    }
}
