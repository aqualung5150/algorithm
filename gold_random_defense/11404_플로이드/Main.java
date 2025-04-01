import java.util.*;
import java.io.*;

public class Main {

    private static final int INF = 1000000000;

    private static int n, m;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {

        init();

        for (int k = 1; k <= n; ++k) {
            for (int i = 1; i <= n; ++i) {
                for (int j = 1; j <= n; ++j) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (dp[i][j] == INF) {
                    sb.append("0");
                } else {
                    sb.append(dp[i][j]);
                }
                sb.append(" ");
            }
            sb.append("\n");
        }

        System.out.print(sb);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        dp = new int[n + 1][n + 1];
        for (int i = 1; i <= n; ++i) {
            Arrays.fill(dp[i], INF);
        }
        for (int i = 1; i <= n; ++i) {
            dp[i][i] = 0;
        }

        for (int i = 0; i < m; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            dp[a][b] = Math.min(dp[a][b], c);
        }

        br.close();
    }
}