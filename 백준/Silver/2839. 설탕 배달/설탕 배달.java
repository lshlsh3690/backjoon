import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws  Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int []arr = new int[5001];
        arr[0] = -1;
        arr[1] = -1;
        arr[2] = -1;
        arr[3] = 1;
        arr[4] = -1;
        arr[5] = 1;
        for (int i = 6; i <= N; i++) {
            int three = Integer.MAX_VALUE;
            int five = Integer.MAX_VALUE;
            if (arr[i-3] == -1 && arr[i-5] == -1){
                arr[i] = -1;
                continue;
            }
            if (arr[i - 3] != -1) {
                three = arr[i - 3] + 1;
            }

            if (arr[i - 5] != -1) {
                five = arr[i - 5] + 1;
            }
            if (three != Integer.MAX_VALUE && five == Integer.MAX_VALUE) {
                arr[i] = three;
            } else if (three == Integer.MAX_VALUE && five != Integer.MAX_VALUE) {
                arr[i] = five;
            }else if (three != Integer.MAX_VALUE && five != Integer.MAX_VALUE) {
                arr[i] = Math.min(three, five);
            }
        }

        System.out.println(arr[N]);
    }
}
