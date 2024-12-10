import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int[] arr;
    private static int[] dp;

    public static void main(String[] args) throws IOException {

        init();

        for (int i = 0; i < N; ++i) {
            int min = 10000;
            int max = 0;
            for (int j = i; j >= 0; --j) {
                min = Math.min(min, arr[j]);
                max = Math.max(max, arr[j]);

                dp[i + 1] = Math.max(dp[i + 1], dp[j] + max - min);
            }
        }

        System.out.print(dp[N]);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }
}