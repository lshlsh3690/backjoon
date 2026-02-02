
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int N, K;
	public static Node[] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		arr = new Node[N * 2];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 2 * N; i++) {
			int health = Integer.parseInt(st.nextToken());
			arr[i] = new Node(health, false);
		}

		int k = 1;
		for (k = 1; true; k++) {
			turn();
			move();
			out();
			// 태우기
			if (arr[0].health > 0 && !arr[0].isThere) {
				arr[0].isThere = true;
				arr[0].health -= 1;
			}

			int kSum = 0;
			for (int i = 0; i < 2 * N; i++) {
				if (arr[i].health <= 0) {
					kSum++;
				}

			}
			if (kSum >= K) {
				break;
			}
		}
		System.out.println(k);
	}

	public static void turn() {
		Node temp = arr[2*N-1];
		for(int i = 2*N-1;i>0;i--) {
			arr[i] = arr[i-1];
			if (i == N-1) {
				arr[i].isThere = false;
			}
		}
		arr[0] = temp;
	}

	public static void move() {
		Node temp = arr[2*N-1];
		Node temp2 = arr[0];
		
		for(int i = 2*N-1;i>1;i--) {
			if (i == N-1) {
				if(arr[i-1].isThere && arr[i].health > 0 && !arr[i].isThere) {
					arr[i-1].isThere = false;
					arr[i].isThere = false;
					arr[i].health -= 1;
				}
			}else 
				if (arr[i-1].isThere && !arr[i].isThere && arr[i].health>0) {
				arr[i-1].isThere = false;
				arr[i].isThere = true;
				arr[i].health -= 1;
			}
		}
		if(!temp2.isThere && temp2.health > 0 && temp.isThere) {
			arr[0].isThere = true;
			arr[0].health -= 1;
			arr[2*N-1].isThere = false;
		}
	}

	public static void out() {
		if (arr[N - 1].isThere) {
			arr[N - 1].isThere = false;
		}
	}
}

class Node {
	boolean isThere;
	int health;

	public Node(int health, boolean isThere) {
		this.health = health;
		this.isThere = isThere;
	}
}
