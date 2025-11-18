import java.util.Scanner;

public class Solution {
    private static int[][]arr;
    private static boolean[][] visited;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        for(int test_case = 1; test_case <= 10; test_case++)
        {
            int T=sc.nextInt();
            sc.nextLine();
            arr = new int[100][100];

            for (int i = 0; i < 100; i++) {
                String[] sarr = sc.nextLine().split(" ");
                for (int j = 0; j < 100; j++) {
                    arr[i][j] = Integer.parseInt(sarr[j]);
                }
            }

            for (int i = 0; i < 100; i++) {
                if (arr[99][i] == 2) {
                    search(T,i);
                    break;
                }
            }
        }
    }

    private static void search(int tc, int col) {
        visited = new boolean[100][100];
        int depth = 99;
        while (depth > 0){
//            System.out.println("depth="+ depth + " " + "col="+col);
            visited[depth][col] = true;
            if (col - 1 >= 0 && arr[depth][col - 1] == 1 && !visited[depth][col-1]) {
                col -= 1;
            } else if (col + 1 < 100 && arr[depth][col + 1] == 1 && !visited[depth][col+1]) {
                col += 1;
            } else{
                depth--;
            }
        }
        System.out.println("#"+tc+" "+col);
    }
}
