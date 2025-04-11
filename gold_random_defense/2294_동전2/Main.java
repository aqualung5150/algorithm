import java.util.*;
import java.io.*;

public class Main {

    private static final int INF = 10001;
    private static int n, k;
    private static int[] coins, dp;

    public static void main(String[] args) throws IOException {

        init();

        for (int coin : coins) {
            for (int i = coin; i <= k; ++i) {
                dp[i] = Math.min(dp[i], dp[i - coin] + 1);
            }
        }

        if (dp[k] == INF) {
            System.out.print("-1");
        } else {
            System.out.print(dp[k]);
        }
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        coins = new int[n];
        for (int i = 0; i < n; ++i) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        dp = new int[k + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;
        br.close();
    }
}