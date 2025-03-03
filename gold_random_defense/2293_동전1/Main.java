import java.util.*;
import java.io.*;

public class Main {

    private static int n, k;
    private static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        dp = new int[k + 1];
        dp[0] = 1;
        for (int i = 0; i < n; ++i) {

            int coin = Integer.parseInt(br.readLine());

            for (int j = coin; j <= k; ++j) {
                dp[j] += dp[j - coin];
            }
        }

        System.out.print(dp[k]);

        br.close();
    }
}