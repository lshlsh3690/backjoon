

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int t = 1; t <= 10; t++) {
			boolean[] visited = new boolean[100];
			Integer[] pathA = new Integer[100];
			Integer[] pathB = new Integer[100];
			StringTokenizer st = new StringTokenizer(br.readLine());

			int tc = Integer.parseInt(st.nextToken());
			int n = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < n; i++) {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());

				if (pathA[start] == null) {
					pathA[start] = end;
				} else {
					pathB[start] = end;
				}
			}

			Queue<Integer> q = new LinkedList<Integer>();
			q.add(0);

			while (!q.isEmpty()) {
				Integer poll = q.poll();

				if (visited[poll]) {
					continue;
				}
				visited[poll] = true;

				boolean flag = false;

				if (pathA[poll] != null && !visited[pathA[poll]]) {
					q.add(pathA[poll]);
//					visited[pathA[poll]] = true;
				}

				if (pathB[poll] != null && !visited[pathB[poll]]) {
					q.add(pathB[poll]);
//					visited[pathB[poll]] = true;
				}
			}

			StringBuilder sb = new StringBuilder();

			sb.append("#").append(tc).append(" ").append(visited[99] ? 1 : 0);
			System.out.println(sb.toString());
		}
	}

}
