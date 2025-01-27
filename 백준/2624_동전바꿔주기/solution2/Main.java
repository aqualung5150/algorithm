import java.util.*;
import java.io.*;

public class Main {

    private static int T, k;
    private static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        dp = new int[T + 1];

        dp[0] = 1;
        for (int i = 0; i < k; ++i) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            
            for (int t = T; t >= 0; --t) {

                for (int j = 1; j <= n; ++j) {
                    if (j * p > t) {
                        continue;
                    }

                    dp[t] += dp[t - (j * p)];
                }
            }
        }

        System.out.print(dp[T]);

        br.close();
    }
}