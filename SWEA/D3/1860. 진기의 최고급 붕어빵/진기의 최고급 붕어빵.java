

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class Solution {
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for (int t = 1; t <= T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int n = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());
			boolean possible = true;
			
			st = new StringTokenizer(br.readLine()," ");
			int[] arr = new int[n];
			for(int i = 0;i<n;i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(arr);
			
			for(int i= 0;i<n;i++) {				
			    int baked = (arr[i] / m) * k;   // arr[i]분까지 총 구워진 빵 개수
			    int available = baked - i;       // i명(0~i-1번째 손님)에게 이미 팔았으니 그만큼 차감
			    if (available <= 0) {
			    	possible = false;
			        break;
			    }
			}
			
			
			System.out.println(possible ? "#"+t+" Possible" : "#"+t+" Impossible");

		}
		
	}
}
