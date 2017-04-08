/**
 * Created by Elaine on 12/20/2016.
 */
public class advent16 {

    public static void main(String[] args) {
        String INPUT = "10011111011011001";
        int MAX_LENGTH = 35651584;

        StringBuffer sb = new StringBuffer();
        sb.append(INPUT);

        while(sb.length() < MAX_LENGTH) {
            String a = sb.toString();
            StringBuffer sb2 = new StringBuffer(a);
            char[] b = sb2.reverse().toString().toCharArray();
            for(int i=0;i<b.length;i++) {
                if (b[i] == '0') {
                    b[i] = '1';
                } else {
                    b[i] = '0';
                }
            }
            sb.append('0' + new String(b));
        }
        StringBuffer checksum = new StringBuffer();
        String data = sb.toString().substring(0,MAX_LENGTH);

        while(checksum.length()%2 != 1) {
            checksum = new StringBuffer();
            for (int i = 0; i < data.length();i+= 2) {
                checksum.append((data.charAt(i) == data.charAt(i+1)) ? '1' : '0');
            }
            data = checksum.toString();
        }


        System.out.println(checksum.toString());
    }
}
