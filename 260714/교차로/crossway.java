
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static Queue<int[]>[] qs = new LinkedList[4];
    static int[] arr, result;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 4; i++) {
            qs[i] = new LinkedList<>();
        }

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        result = new int[N];
        Arrays.fill(result, -1);

        int time = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int t = Integer.parseInt(st.nextToken());
            char w = st.nextToken().charAt(0);
            arr[i] = t;

            time = Math.min(time, t);
            if (w == 'A') {
                qs[0].add(new int[] { i, t });
            } else if (w == 'B') {
                qs[1].add(new int[] { i, t });
            } else if (w == 'C') {
                qs[2].add(new int[] { i, t });
            } else {
                qs[3].add(new int[] { i, t });
            }
        }

        while (!qs[0].isEmpty() || !qs[1].isEmpty() || !qs[2].isEmpty() || !qs[3].isEmpty()) {
            boolean a, b, c, d;
            a = b = c = d = false;

            boolean allPresent = !qs[0].isEmpty() && qs[0].peek()[1] <= time && !qs[1].isEmpty()
                    && qs[1].peek()[1] <= time && !qs[2].isEmpty() && qs[2].peek()[1] <= time && !qs[3].isEmpty()
                    && qs[3].peek()[1] <= time;

            if (allPresent) {
                break;
            }

            // a
            if (!qs[0].isEmpty()) {
                if (qs[0].peek()[1] <= time && (qs[3].isEmpty() || qs[3].peek()[1] > time)) {
                    a = true;
                }
            }

            // b
            if (!qs[1].isEmpty()) {
                if (qs[1].peek()[1] <= time && (qs[0].isEmpty() || qs[0].peek()[1] > time)) {
                    b = true;
                }
            }

            // c
            if (!qs[2].isEmpty()) {
                if (qs[2].peek()[1] <= time && (qs[1].isEmpty() || qs[1].peek()[1] > time)) {
                    c = true;
                }
            }

            // d
            if (!qs[3].isEmpty()) {
                if (qs[3].peek()[1] <= time && (qs[2].isEmpty() || qs[2].peek()[1] > time)) {
                    d = true;
                }
            }

            if (!a && !b && !c && !d) {
                // 이번 time에는 아무도 통과하지 못함 (아직 아무도 도착 안 한 상태)
                // -> 다음으로 의미 있는 시각(각 큐의 다음 도착 시각 중 최솟값)으로 점프
                int next = Integer.MAX_VALUE;
                for (int k = 0; k < 4; k++) {
                    if (!qs[k].isEmpty()) {
                        next = Math.min(next, qs[k].peek()[1]);
                    }
                }
                time = next;
                continue;
            }

            if (a) {
                int[] poll = qs[0].poll();
                result[poll[0]] = time;
            }
            if (b) {
                int[] poll = qs[1].poll();
                result[poll[0]] = time;
            }
            if (c) {
                int[] poll = qs[2].poll();
                result[poll[0]] = time;
            }
            if (d) {
                int[] poll = qs[3].poll();
                result[poll[0]] = time;
            }

            time++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(result[i]).append('\n');
        }
        System.out.print(sb);
    }
}