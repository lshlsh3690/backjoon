import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        Node[] arr = new Node[N + 1];
        for (int i = 0; i < N + 1; i++) {
            arr[i] = new Node(Integer.MAX_VALUE, Integer.MAX_VALUE,-1);
        }
        arr[N] = new Node(N, 0,-1);
        for (int i = N; i >= 1; i--) {
            if (arr[i].value % 3 == 0) {
                //3으로 나눠떨어진다면
                if (arr[i].cnt + 1 < arr[i / 3].cnt) {
                    arr[i / 3] = new Node(arr[i].value / 3, arr[i].cnt+1, i);
                }
            }

            if (arr[i].value % 2 == 0) {
                //2으로 나눠떨어진다면
                if (arr[i].cnt + 1 < arr[i / 2].cnt) {
                    arr[i / 2] = new Node(arr[i].value  /2, arr[i].cnt+1, i);
                }
            }

            if (arr[i].cnt + 1 < arr[i - 1].cnt) {
                arr[i - 1] = new Node(arr[i].value -1, arr[i].cnt+1, i);
            }
        }

        System.out.println(arr[1].cnt);
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= N;) {
            if (arr[i].nextIdx == -1) {
                break;
            }
            list.add(arr[i].nextIdx);
            i = arr[i].nextIdx;
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.print(list.get(i)+ " ");
        }
        System.out.println(1);

    }
}

class Node{
    int value;
    int cnt;
    int nextIdx;


    public Node(int value, int cnt,int nextIdx) {
        this.value = value;
        this.cnt = cnt;
        this.nextIdx = nextIdx;
    }
}