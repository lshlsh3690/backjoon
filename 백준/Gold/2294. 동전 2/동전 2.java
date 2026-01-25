import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static int N, K;
    public static List<Integer>coins;
    public static int[]  arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        coins = new ArrayList<>();
        arr = new int[K + 1];
        for (int i = 0; i < N; i++) {
            int value = Integer.parseInt(br.readLine());
            if (!coins.contains(value)) {
                coins.add(value);
            }
        }

        for (int i = 0; i < K  + 1; i++) {
            arr[i] = Integer.MAX_VALUE;
        }

        Collections.sort(coins);

        for (int i = 1; i <= K; i++) {
            for (int c = 0; c < coins.size(); c++) {
                int coin = coins.get(c);
                if (coin == i) {
                    arr[i] = 1;
                    continue;
                }
                int prevIdx = i - coin;//이전 인덱스
                if (prevIdx < 1 ){
                    continue;
                }
                
                if (arr[prevIdx] != Integer.MAX_VALUE) {
                    arr[i] = Math.min(arr[i], arr[prevIdx] + 1);
                }
            }
        }

        System.out.println(arr[K] == Integer.MAX_VALUE ? -1 : arr[K]);
    }
}

