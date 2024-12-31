import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int T, N, M;
    private static int[] coins;
    private static int[] dp;

    public static void main(String[] args) throws IOException {

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            init();

            dp[0] = 1;
            for (int coin : coins) {
                for (int i = coin; i <= M; ++i) {
                    dp[i] += dp[i - coin];
                }
            }

            System.out.println(dp[M]);
        }

        br.close();
    }

    private static void init() throws IOException {

        N = Integer.parseInt(br.readLine());
        coins = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            coins[i] = Integer.parseInt(st.nextToken());
        }
        
        M = Integer.parseInt(br.readLine());
        dp = new int[M + 1];
    }
}