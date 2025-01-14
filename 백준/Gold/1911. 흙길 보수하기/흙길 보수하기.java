import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
    static int N, L;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        arr = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }


        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        int cnt = 0;
        int lastPoint = 0;
        for (int i = 0; i < N; i++) {
            int start = Math.max(arr[i][0], lastPoint);
            int dest = arr[i][1];
            while (start < dest) {

                start += L;
                cnt++;
                lastPoint = start;
            }

//            System.out.println(String.valueOf(i+1)+"= "+arr[i][0] + ":"+arr[i][1]);
        }

        System.out.println(cnt);
    }
}
