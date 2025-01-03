import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int[] arr;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[K];
        visited = new boolean[101];
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });

        int cnt = 0;
        for (int i = 0; i < K; i++) {
            boolean flag = false;
            int pivot = arr[i];
            for (int j = i + 1; j < K; j++) {
                if (arr[j] == pivot) {
                    if (pq.size() >= N) {
                        if (visited[pivot]) {
                            ArrayList<int[]> list = new ArrayList<>();
                            while (!pq.isEmpty()) {
                                int[] poll = pq.poll();
                                if (poll[0] == pivot) {
                                    visited[pivot] = false;
                                    continue;
                                }
                                list.add(poll);
                            }
                            pq.addAll(list);
                        } else {
                            int[] poll = pq.poll();
                            cnt++;
                            visited[poll[0]] = false;
                        }
                    } else if (pq.size() < N && visited[pivot]) {
                        ArrayList<int[]> list = new ArrayList<>();
                        while (!pq.isEmpty()) {
                            int[] poll = pq.poll();
                            if (poll[0] == pivot) {
                                visited[pivot] = false;
                                continue;
                            }
                            list.add(poll);
                        }
                        pq.addAll(list);
                    }
                    pq.add(new int[]{arr[i], j});
                    visited[pivot] = true;
                    flag = true;
                    break;
                }
            }
            if (!flag) {
                if (pq.size() >= N) {
                    if (visited[pivot]) {
                        ArrayList<int[]> list = new ArrayList<>();
                        while (!pq.isEmpty()) {
                            int[] poll = pq.poll();
                            if (poll[0] == pivot) {
                                visited[pivot] = false;
                                continue;
                            }
                            list.add(poll);
                        }
                        pq.addAll(list);
                    } else {
                        int[] poll = pq.poll();
                        cnt++;
                        visited[poll[0]] = false;
                    }
                }
                pq.add(new int[]{arr[i], K});
                visited[arr[i]] = true;
            }
        }
        System.out.println(cnt);
    }
}
