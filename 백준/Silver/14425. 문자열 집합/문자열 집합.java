import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static Map<String, Boolean> map;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new HashMap<>();

		int ret = 0;

		for (int i = 0; i < N; i++) {
			String s = br.readLine();
			map.put(s, true);
		}

		for (int j = 0; j < M; j++) {
			String s = br.readLine();
			if (map.containsKey(s)) {
				ret++;
			}
		}

		System.out.println(ret);
	}
}
