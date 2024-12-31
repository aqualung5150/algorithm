import java.util.*;
import java.io.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int T, N, M;
    private static int[] coins;
    private static int[] dp;

    public static void main(String[] args) throws IOException {

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {

            init();

            dp[0] = 1;
            for (int i = 0; i < N; ++i) {
                int coin = Integer.parseInt(st.nextToken());
                for (int j = coin; j <= M; ++j) {
                    dp[j] += dp[j - coin];
                }
            }

            System.out.println(dp[M]);
        }

        br.close();
    }

    private static void init() throws IOException {

        N = Integer.parseInt(br.readLine());
        coins = new int[N];

        st = new StringTokenizer(br.readLine());
 
        M = Integer.parseInt(br.readLine());
        dp = new int[M + 1];
    }
}