import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        ArrayList<int[]> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            list.add(new int[]{d, l});
        }

        list.sort((o1,o2) -> {
            return Integer.compare(o1[0], o2[0]);
        });

        PriorityQueue<boj1781_pq> pq = new PriorityQueue<>(new Comparator<boj1781_pq>() {
            @Override
            public int compare(boj1781_pq o1, boj1781_pq o2) {
                if (o1.l == o2.l) {
                    return o2.d - o1.d;
                }
                return o1.l - o2.l;
            }
        });

        for (int i = 0; i < list.size(); i++) {
            int[] e = list.get(i);
            int d = e[0];
            int l = e[1];

            pq.add(new boj1781_pq(d, l));
            while (pq.size() > d) {
                pq.poll();
            }
        }

        int sum =0;
        while (!pq.isEmpty()) {
            boj1781_pq poll = pq.poll();
            sum += poll.l;
        }

        System.out.println(sum);
    }
}

class boj1781_pq {
    int d;
    int l;

    public boj1781_pq(int d, int l) {
        this.d = d;
        this.l = l;
    }

    @Override
    public String toString() {
        return "boj1781_pq{" +
                "d=" + d +
                ", l=" + l +
                '}';
    }
}
