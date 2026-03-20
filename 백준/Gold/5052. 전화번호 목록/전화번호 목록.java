import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
	static int T, N;
	static Node root;

	static class Node {
		Node[] child = new Node[10];
		boolean isLeaf = false;
	}

	static boolean insert(String s) {
		Node now = root;

		for (int i = 0; i < s.length(); i++) {
			int idx = s.charAt(i) - '0';

			if (now.child[idx] == null) {
				now.child[idx] = new Node();
			}

			now = now.child[idx];

			if (i == s.length() - 1) {
				now.isLeaf = true;
			} else if (now.isLeaf) {
				return false;
			}
		}

		return true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			root = new Node(); // 테스트케이스마다 초기화

			N = Integer.parseInt(br.readLine());
			List<String> list = new ArrayList<>();

			for (int i = 0; i < N; i++) {
				String s = br.readLine();
				list.add(s);
			}

			list.sort((o1, o2) -> o1.length() - o2.length());

			boolean flag = true;
			for (String s : list) {
				flag = insert(s);
				if (!flag) {
					break;
				}
			}

			System.out.println(flag ? "YES" : "NO");
		}
	}
}