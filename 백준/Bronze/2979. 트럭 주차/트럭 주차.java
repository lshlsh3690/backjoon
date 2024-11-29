import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int a,b,c;
    static int[] arr = new int[100];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] str = br.readLine().split(" ");
        a = Integer.parseInt(str[0]);
        b = Integer.parseInt(str[1]);
        c = Integer.parseInt(str[2]);

        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            for (int k = s - 1; k < e-1; k++) {
                arr[k]++;
            }
        }

        int result = 0;
        for (int e : arr) {
            if (e == 1) {
                result += a;
            } else if (e == 2) {
                result += 2 * b;
            } else if (e == 3) {
                result += 3 * c;
            }
        }
        System.out.println(result);
    }
}
