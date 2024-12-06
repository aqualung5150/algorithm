import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static long[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new long[N];

        dp[0] = 1;
        for (int i = 1; i < N; ++i) {
            dp[i] = dp[i - 1] + 1;
            for (int j = 3; i - j >= 0; ++j) {
                dp[i] = Math.max(dp[i], dp[i - j] * (j - 1));
            }
        }

        System.out.print(dp[N - 1]);
    }
}