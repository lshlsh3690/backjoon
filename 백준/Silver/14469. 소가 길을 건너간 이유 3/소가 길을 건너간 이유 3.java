import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<int[]> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int arrive = Integer.parseInt(st.nextToken());
            int checkTime = Integer.parseInt(st.nextToken());

            list.add(new int[]{arrive, checkTime});
        }

        list.sort((o1,o2) -> {
            return Integer.compare(o1[0], o2[0]);
        });

        int sum = 0;
        for (int i = 0; i < N; i++) {
            int[] e = list.get(i);
            int arrive = e[0];
            int checkTime = e[1];

            if (sum >= arrive) {

            }else{
                sum = arrive;
            }
            sum += checkTime;
        }

        System.out.println(sum);

    }
}
