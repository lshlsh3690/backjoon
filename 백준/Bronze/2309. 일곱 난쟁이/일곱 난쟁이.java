import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

import static java.lang.System.exit;

public class Main {
    public static int N = 9;
    public static int[] arr = new int[N];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int[] result = new int[7];

        for (int i = 0; i < N; i++) {
            recursive(i, result, 0);
        }

    }

    private static void recursive(int i, int[] result, int k) {
        if (k == 7 || i == 9)
        {
            if (Arrays.stream(result).sum() == 100) {
                for (int a :
                        result) {
                    System.out.println(a);
                }
                exit(0);
            }
            return;
        }

        result[k] = arr[i];
        recursive(i+1, result, k+1);
        result[k] = 0;
        recursive(i+1, result, k);

    }
}
