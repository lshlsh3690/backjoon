import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static int K, Len;
    public static int[] arr;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        Len = (1 << K) - 1;
        arr = new int[Len];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < Len; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0, Len - 1});
        dfs(1, q);
    }

    public static void dfs(int depth, Queue<int[]> q) {
        Queue<int[]> nextQ = new LinkedList<>();

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int s = cur[0];
            int e = cur[1];
            int mid = (s + e) / 2;

            System.out.print(arr[mid] + " ");

            if (s <= mid - 1) nextQ.add(new int[]{s, mid - 1});
            if (mid + 1 <= e) nextQ.add(new int[]{mid + 1, e});
        }

        System.out.println();
        if (!nextQ.isEmpty()) dfs(depth + 1, nextQ);
    }
}
