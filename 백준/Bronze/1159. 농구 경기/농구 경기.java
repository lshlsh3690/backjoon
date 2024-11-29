import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    static int N;
    static int[] arr = new int[N];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        HashMap<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            char key = br.readLine().charAt(0);
            if (map.containsKey(key)){
                Integer value = map.get(key);
                map.put(key, value + 1);
            }else{
                map.put(key, 1);
            }
        }
//
//        Stream<Map.Entry<Character, Integer>> sorted =
//                map.entrySet().stream()
//                        .filter(e -> e.getValue() >= 5)
//                        .sorted();

        // 조건에 맞는 문자 필터링 및 정렬
        String result = map.entrySet().stream()
                .filter(e -> e.getValue() >= 5) 
                .map(Map.Entry::getKey) 
                .sorted() 
                .map(String::valueOf) 
                .collect(Collectors.joining()); 

        if (result.isEmpty()) {
            System.out.println("PREDAJA");
        } else {
            System.out.println(result);
        }
    }
}
