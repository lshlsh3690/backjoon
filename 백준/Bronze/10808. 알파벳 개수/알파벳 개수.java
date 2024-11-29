import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[] arr = new int[26];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();

        for (int i = 0; i < s.length(); i++) {
            char a = s.charAt(i);
            int idx = a - 97;
            arr[idx]++;
        }

        for (int a :
                arr) {
            System.out.print(a + " ");
        }
    }
}
