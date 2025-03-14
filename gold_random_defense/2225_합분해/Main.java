import java.util.*;
import java.io.*;

public class Main {

    private static final int MOD = 1000000000;
    private static int N, K;
    private static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        dp = new int[K + 1];

        Arrays.fill(dp, 1);
        dp[0] = 0;
        for (int i = 1; i <= N; ++i) {
            for (int j = 1; j <= K; ++j) {
                dp[j] = (dp[j - 1] + dp[j]) % MOD;
            }
        }

        System.out.print(dp[K]);
    }
}