import java.util.*;
import java.io.*;

public class Main {

    private static final int INF = 100000;

    private static int C, N;
    private static int[][] dp; // 가로: 비용, 세로: 인풋

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        dp = new int[N + 1][INF + 1];

        int answer = INF;

        for (int i = 1; i <= N; ++i) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            for (int j = 1; j <= 100000; ++j) {

                if (j >= cost) {
                    dp[i][j] = Math.max(dp[i][j - cost] + value, dp[i - 1][j]);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }

                if (dp[i][j] >= C) {
                    answer = Math.min(answer, j);
                    break;
                }                
            }
        }

        System.out.print(answer);
    }
}