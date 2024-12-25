import java.util.*;
import java.io.*;

public class Main {

    private static int N, K;
    private static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[K + 1];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            for (int j = K; j >= W; --j) {
                dp[j] = Math.max(dp[j], dp[j - W] + V);
            }
        }

        br.close();

        System.out.print(dp[K]);
    }
}