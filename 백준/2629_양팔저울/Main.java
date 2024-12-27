import java.util.*;
import java.io.*;

public class Main {

    private static int N, M;
    private static int[] weights;
    private static boolean[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        weights = new int[N];
        dp = new boolean[N + 1][15001];

        StringTokenizer st = new StringTokenizer(br.readLine());

        int maxWeight = 0;
        for (int i = 1; i <= N; ++i) {
            int weight = Integer.parseInt(st.nextToken());
            maxWeight += weight;
            dp[i][weight] = true;
            for (int j = 0; j <= maxWeight; ++j) {
                if (dp[i - 1][j]) {
                    dp[i][j] = true;
                    dp[i][j + weight] = true;
                    dp[i][Math.abs(j - weight)] = true;
                }
            }
        }

        M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; ++i) {
            int weight = Integer.parseInt(st.nextToken());
            if (weight <= 15000 && dp[N][weight]) {
                System.out.print("Y ");
            } else {
                System.out.print("N ");
            }
        }

        br.close();
    }
}