import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.Key;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());


        String[] pattern = br.readLine().split("\\*");

        String prefix = pattern[0];
        String suffix = pattern[1];


        for (int i = 0; i < n; i++) {
            String s = br.readLine();

            if (s.length() < prefix.length() || s.length() < suffix.length() || s.length() < prefix.length() + suffix.length()) {
                System.out.println("NE");
                continue;
            }
            

            String t1 = s.substring(0, prefix.length());
            boolean pre = prefix.equals(t1);
            String t = s.substring(s.length() - suffix.length());
            boolean suf = suffix.equals(t);

            if (pre && suf) {
                System.out.println("DA");
            } else {
                System.out.println("NE");
            }
        }


    }
}
