import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
	static int w, B;
	static String[] arr;
	static char[][] board;
	static boolean[][] visited;

	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };

	static Node root = new Node();

	static class Node {
		char c;
		boolean isLeaf = false;
		Node[] child = new Node[26];
	}

	static Set<String> findWord;

	static void insert(String s) {
		Node now = root;

		for (int i = 0; i < s.length(); i++) {
			int idx = s.charAt(i) - 'A';

			if (now.child[idx] == null) {
				now.child[idx] = new Node();
			}

			now = now.child[idx];
			now.c = s.charAt(i);
		}

		now.isLeaf = true;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		w = Integer.parseInt(br.readLine());
		arr = new String[w];
		for (int i = 0; i < w; i++) {
			arr[i] = br.readLine();
			insert(arr[i]);
		}

		br.readLine();

		B = Integer.parseInt(br.readLine());
		StringBuilder sb = new StringBuilder();
		for (int b = 0; b < B; b++) {
			board = new char[4][4];
			visited = new boolean[4][4];
			findWord = new HashSet<>();

			for (int i = 0; i < 4; i++) {
				String s = br.readLine();
				for (int j = 0; j < 4; j++) {
					board[i][j] = s.charAt(j);
				}
			}
			if (b != B - 1) {
				br.readLine();
			}
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					visited[i][j] = true;
					go(i, j, String.valueOf(board[i][j]));
					visited[i][j] = false;
				}
			}

			int score = 0;
			for (String s : findWord) {
				if (s.length() >= 8) {
					score += 11;
				} else if (s.length() >= 7) {
					score += 5;
				} else if (s.length() >= 6) {
					score += 3;
				} else if (s.length() >= 5) {
					score += 2;
				} else if (s.length() >= 3) {
					score += 1;
				}
			}

			List<String> list = new ArrayList<>(findWord);
			Collections.sort(list, (o1, o2) -> {
				if (o1.length() == o2.length()) {
					return o1.compareTo(o2);
				}
				return o2.length() - o1.length();
			});

			String longest = "";
			if (!list.isEmpty()) {
				longest = list.get(0);
			}

			sb.append(score).append(" ").append(longest).append(" ").append(findWord.size()).append(" \n");

		}
		System.out.println(sb);
	}

	static void go(int y, int x, String word) {
		if (word.length() > 8) {
			return;
		}

		// 트라이에서 단어 탐색하는 로직
		// 단어가 없으면 리턴
		if (!search(word)) {
			return;
		}

		// 다음 단어 탐색
		for (int k = 0; k < 8; k++) {
			int ny = y + dy[k];
			int nx = x + dx[k];
			if (out(ny, nx) || visited[ny][nx]) {
				continue;
			}

			visited[ny][nx] = true;
			go(ny, nx, word + board[ny][nx]);
			visited[ny][nx] = false;
		}
	}

	static boolean search(String s) {
		Node now = root;
		for (int i = 0; i < s.length(); i++) {
			int idx = s.charAt(i) - 'A';

			if (now.child[idx] == null) {
				return false;
			}

			now = now.child[idx];
		}

		if (now.isLeaf) {
			findWord.add(s);
		}

		return true;
	}

	static boolean out(int y, int x) {
		return y < 0 || y >= 4 || x < 0 || x >= 4;
	}
}
