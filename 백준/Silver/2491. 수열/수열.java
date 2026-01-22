import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static int[] arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int maxAsc = 1;
        int ascCnt = 1;
        int last = arr[0];
        int maxDesc = 1;
        int descCnt = 1;
        for (int i = 1; i < N; i++) {
            if (arr[i] >= last) {
                ascCnt++;
            }else{
                maxAsc = Math.max(ascCnt, maxAsc);
                ascCnt = 1;
            }

            if (arr[i] <= last) {
                descCnt++;
            }else{
                maxDesc = Math.max(descCnt, maxDesc);
                descCnt = 1;
            }
            last = arr[i];
        }

        maxAsc = Math.max(ascCnt, maxAsc);
        maxDesc = Math.max(descCnt, maxDesc);
        System.out.println(Math.max(maxAsc, maxDesc));
    }
}
