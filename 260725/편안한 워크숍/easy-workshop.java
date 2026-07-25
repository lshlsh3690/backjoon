import java.util.Scanner;

public class Main {
    static final int INT_MAX = Integer.MAX_VALUE;

    static final int MAX_N = 100;
    static final int MAX_K = 100;
    static final int DIR_NUM = 4;

    // 변수 선언
    static int n, k;
    static int[][] grid = new int[MAX_N][MAX_N];

    // dp[i][j][l] :
    // (i, j) 위치에서 길이가 정확히 l인
    // 가능한 등산로들 중
    // 인접한 높이의 차들간의 최댓값 중 가능한 최소
    static int[][][] dp = new int[MAX_N][MAX_N][MAX_K + 1];

    // 격자 안에 들어오는지 확인합니다.
    static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    static int findMin(int i, int j, int l) {
        // 이미 계산해본 적이 있다면 바로 반환합니다.
        if (dp[i][j][l] != -1)
            return dp[i][j][l];

        // l이 1이라면 더 이상 진행할 필요가 없기 때문에
        // dp값은 0이 됩니다.
        if (l == 1) {
            dp[i][j][l] = 0;
            return dp[i][j][l];
        }

        // 인접한 4방향을 살펴봅니다.
        int best = INT_MAX;
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        for (int t = 0; t < 4; t++) {
            int nx = i + dx[t], ny = j + dy[t];
            if (inRange(nx, ny) && grid[nx][ny] > grid[i][j])
                best = Math.min(best, Math.max(findMin(nx, ny, l - 1), grid[nx][ny] - grid[i][j]));
        }

        dp[i][j][l] = best;
        return dp[i][j][l];
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력:
        n = scanner.nextInt();
        k = scanner.nextInt();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                grid[i][j] = scanner.nextInt();

        // dp값을 전부 -1로 초기화해줍니다.
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                for (int l = 0; l <= k; l++)
                    dp[i][j][l] = -1;

        // 각 위치를 시작으로 하는 dp를 진행합니다.
        int ans = INT_MAX;
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                ans = Math.min(ans, findMin(i, j, k));

        // 여전히 INT_MAX라면
        // 불가능하다는 뜻이므로 답은 -1이 됩니다.
        if (ans == INT_MAX)
            ans = -1;

        System.out.println(ans);
    }
}
