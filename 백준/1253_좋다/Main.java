import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int[] arr;
    private static Map<Integer, Integer> m = new HashMap<>();
    private static Set<Integer> check = new HashSet<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;
            
            if (m.containsKey(n)) {
                m.put(n, m.get(n) + 1);
            } else {
                m.put(n, 1);
            }
        }

        for (int i = 0; i < N - 1; ++i) {
            for (int j = i + 1; j < N; ++j) {
                int a = arr[i];
                int b = arr[j];
                int sum = arr[i] + arr[j];
                // 0과 n을 더하면 n이 나온다.
                // 0과 0을 더하면 0이 나온다.
                if (a == 0 || b == 0) {
                    if (a == 0 && b == 0) {
                        if (m.get(0) >= 3) {
                            check.add(0);
                        }
                    } else {
                        if (m.get(sum) >= 2) {
                            check.add(sum);
                        }
                    }
                } else if (m.containsKey(sum)) {
                    check.add(sum);
                }
            }
        }

        int ans = 0;
        for (Integer n : check) {
            ans += m.get(n);
        }
        System.out.print(ans);       
    }
}