import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int MAX_POS = 1_000_000;
        int[] arr = new int[MAX_POS + 1];

        int maxIdx = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int g = Integer.parseInt(st.nextToken());
            int idx = Integer.parseInt(st.nextToken());
            arr[idx] += g;              // 같은 위치에 여러 번 들어올 수도 있으니 누적
            maxIdx = Math.max(maxIdx, idx);
        }

        // 곰이 볼 수 있는 최대 오른쪽 끝 위치
        int windowSize = 2 * K + 1;
        int right = Math.min(MAX_POS, K * 2);  // 처음 윈도우: [0 ~ right]

        int sum = 0;
        for (int i = 0; i <= right; i++) {     // 0 ~ right까지 합
            sum += arr[i];
        }

        int result = sum;
        int left = 0;

        // 오른쪽 끝이 MAX_POS까지 갈 때까지 한 칸씩 이동
        while (right < MAX_POS) {
            right++;
            sum += arr[right];   // 새로 들어온 오른쪽 값 추가
            sum -= arr[left];    // 빠져 나간 왼쪽 값 제거
            left++;
            result = Math.max(result, sum);
        }

        System.out.println(result);
    }
}
