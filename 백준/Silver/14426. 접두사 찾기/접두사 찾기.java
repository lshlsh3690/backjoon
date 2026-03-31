import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int N, M;
	static Node root = new Node();

	static class Node {
		Node[] child = new Node[26];
	}

	static void insert(String s) {
		Node now = root;

//		System.out.println(s);
		for (int i = 0; i < s.length(); i++) {
			int idx = s.charAt(i) - 'a';

			if (now.child[idx] == null) {
				now.child[idx] = new Node();
			}

			now = now.child[idx];
		}
	}

	static boolean findPrefix(String s) {
		Node now = root;

		for (int i = 0; i < s.length(); i++) {
			int idx = s.charAt(i) - 'a';

			if (now.child[idx] == null) {
				return false;
			}

			now = now.child[idx];
		}

		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			insert(s);
		}

		int ret = 0;

		for (int i = 0; i < M; i++) {
			String s = br.readLine();

			if (findPrefix(s)) {
				ret++;
			}
		}

		System.out.println(ret);
	}
}
