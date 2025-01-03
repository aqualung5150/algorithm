import java.util.*;
import java.io.*;

public class Main {

    private static final int INF = 1000000007;

    private static int N;
    private static int[][] dp; // 0: N, 1: 1, 2: 2

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[3][N];

        dp[0][0] = 1;
        dp[2][0] = 1;
        for (int i = 1; i < N; ++i) {
            dp[0][i] = ((dp[0][i - 1] + dp[1][i - 1]) % INF + (dp[2][i - 1] * 2) % INF) % INF;
            dp[1][i] = (dp[1][i - 1] + (dp[2][i - 1] * 2) % INF) % INF;
            dp[2][i] = ((dp[0][i - 1] + dp[1][i - 1]) % INF + dp[2][i - 1]) % INF;
        }

        int answer = 0;
        for (int i = 0; i < 3; ++i) {
            answer += dp[i][N - 1];
            answer %= INF;
        }

        System.out.print(answer);

        br.close();
    }
}