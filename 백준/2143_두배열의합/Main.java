import java.util.*;
import java.io.*;

public class Main {

    private static int T;
    private static int n, m;
    private static int[] arrN, arrM;
    private static Map<Integer, Integer> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        n = Integer.parseInt(br.readLine());
        arrN = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; ++i) {
            arrN[i] = Integer.parseInt(st.nextToken());
        }
        m = Integer.parseInt(br.readLine());
        arrM = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; ++i) {
            arrM[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; ++i) {
            int sum = 0;
            for (int j = i; j < n; ++j) {
                sum += arrN[j];

                if (!map.containsKey(sum)) {
                    map.put(sum, 1);
                } else {
                    map.put(sum, map.get(sum) + 1);
                }
            }
        }

        long answer = 0;
        for (int i = 0; i < m; ++i) {
            int sum = 0;
            for (int j = i; j < m; ++j) {
                sum += arrM[j];

                int target = T - sum;
                if (map.containsKey(target)) {
                    answer += map.get(target);
                }
            }
        }

        System.out.print(answer);

        br.close();
    }
}