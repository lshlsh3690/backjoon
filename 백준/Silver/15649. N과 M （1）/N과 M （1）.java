import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    private static int N,M;
    private static String s;
    private static int[] arr;
    private static boolean[] visisted;
    public static void main(String[] args) throws  Exception{
        Scanner sc = new Scanner(System.in);
        StringTokenizer st = new StringTokenizer(sc.nextLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visisted = new boolean[N];

        for (int i = 0; i < N; i++) {
            visisted[i] = true;
            s = i+1 + " ";
            asdf(1);
            visisted[i] = false;
        }
    }

    private static void asdf(int depth) {
        if (depth >= M) {
            System.out.println(s);
            return;
        }

        for (int i = 0; i < N; i++) {
            if (!visisted[i]) {
                visisted[i] = true;
                s += i + 1 + " ";
                asdf(depth + 1);
                s = s.substring(0, s.length() - 2);
                visisted[i] = false;
            }
        }

    }
}
