import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr, goal;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int sum = 0;
        int one = 0;
        int two = 0;
        goal = new int[N];
        for (int i = 0; i < N; i++) {
            //높이 목표
            goal[i] = Integer.parseInt(st.nextToken());
            sum += goal[i];
            one += goal[i] % 2;
            two += goal[i] / 2;
        }

        //물 뿌리개는 동시에 사용해야되고 따로 사용한다면 한개는 1, 두개는 2, 동시에 한곳을 뿌리면 3을 증가시킴
        if (sum % 3 != 0) {
            System.out.println("NO");
        }else{
            if (one > two) {
                System.out.println("NO");
            } else {
                System.out.println("YES");
            }

//            System.out.println("one = " + one);
//            System.out.println("two = " + two);
        }

    }
}
