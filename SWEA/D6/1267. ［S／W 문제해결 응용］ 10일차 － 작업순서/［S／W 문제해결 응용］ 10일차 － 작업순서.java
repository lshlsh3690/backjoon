import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static int V,E;
	static int[] degree;
	static List<ArrayList<Integer>>arr;
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int t=1;t<=10;t++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			degree = new int[V+1];
			arr = new ArrayList<>();
			for(int i = 0;i<=V;i++) {
				arr.add(new ArrayList<Integer>());
			}
			
			st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0;i<E;i++) {
				int start = Integer.parseInt(st.nextToken());
				int end = Integer.parseInt(st.nextToken());
				
				arr.get(start).add(end);
				degree[end]++;
			}
			
			Queue<Integer>q = new ArrayDeque<Integer>();
			Queue<Integer> result= new ArrayDeque();
			for(int i=1;i<=V;i++) {
				if (degree[i] == 0) {
					q.add(i);
				}
			}
			
			while(!q.isEmpty()) {
				int now = q.poll();
			
				result.offer(now);
				for(int i = 0;i<arr.get(now).size();i++) {
					degree[arr.get(now).get(i)]--;
					if (degree[arr.get(now).get(i)] == 0) {
						q.add(arr.get(now).get(i));
					}
				}
			}
			
			
			StringBuilder sb = new StringBuilder();
			sb.append("#"+t+ " ");
			while(!result.isEmpty()) {
				sb.append(result.poll()+ " ");
			}
			System.out.println(sb);
		}
	}
}
