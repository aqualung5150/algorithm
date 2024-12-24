import java.util.*;
import java.io.*;

public class Main {

    private static final int INF = 100000;

    private static int C, N;
    private static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        dp = new int[INF + 1];

        int answer = INF;

        for (int i = 1; i <= N; ++i) {
            st = new StringTokenizer(br.readLine());
            int cost = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            for (int j = cost; j <= 100000; ++j) {

                dp[j] = Math.max(dp[j], dp[j - cost] + value);

                if (dp[j] >= C) {
                    answer = Math.min(answer, j);
                    break;
                }
            }
        }

        System.out.print(answer);
    }
}