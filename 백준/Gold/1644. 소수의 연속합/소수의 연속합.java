import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        boolean[] isSosu = new boolean[N + 1];
        boolean[] visited = new boolean[N + 1];
        List<Integer> list = new ArrayList<>();
        for (int i = 2; i <= N; i++) {
            if (visited[i]) {
                continue;
            }
            isSosu[i] = true;
            visited[i] = true;
            list.add(i);
//            System.out.printf(i + " ");
            int t = i + i;
            while (t <= N) {
                isSosu[t] = false;
                visited[t] = true;
                t += i;
            }
        }

        int left = 0;
        int right = 0;
        int sum = 0;
        int cnt = 0;
        while (right < list.size()) {
            sum += list.get(right);
            right++;
            if (sum == N) {
//                System.out.println(left+" "+(right-1));
                cnt++;
            } else if (sum > N) {
                while (sum > N) {
                    int sub = list.get(left);
                    sum -= sub;
                    left++;
                }
                if (sum == N) {
                    cnt++;
//                    System.out.println(left+" "+(right-1));
                }
            }
        }

        System.out.println(cnt);
    }
}
