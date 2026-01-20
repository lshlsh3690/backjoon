import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static int N;
    public static int []arr;
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (arr[i-1] > list.size()) {
                list.add(0, i);
            }else if (arr[i-1] == 0){
                list.add(i);
            }else {
                int idx = list.size()-arr[i-1];
                list.add(idx, i);
            }
        }

        for (int i : list) {
            System.out.print(i + " ");
        }
    }
}
