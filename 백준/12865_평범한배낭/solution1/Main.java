import java.util.*;
import java.io.*;

public class Main {

    private static int N, K;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[N + 1][K + 1];

        for (int i = 1; i <= N; ++i) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());
            for (int j = 1; j <= K; ++j) {

                if (j >= W) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - W] + V);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        br.close();

        // for (int i = 1; i <= N; ++i) {
        //     for (int j = 1; j <= K; ++j) {
        //         System.out.print(dp[i][j] + " ");
        //     }
        //     System.out.println();
        // }

        System.out.print(dp[N][K]);
    }
}