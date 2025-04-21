import java.io.*;
import java.util.*;

public class Main {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N, M;
    private static int[] arr;
    private static boolean[][] dp;

    public static void main(String[] args) throws IOException {

        init();

        for (int i = 1; i < N; ++i) {
            dp[i][i] = true;
            if (arr[i] == arr[i + 1]) {
                dp[i][i + 1] = true;
            }
        }
        dp[N][N] = true;

        for (int k = 2; k <= N - 1; ++k) {
            for (int i = 1; i + k <= N; ++i) {
                if (arr[i] == arr[i + k] && dp[i + 1][i + k - 1]) {
                    dp[i][i + k] = true;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            if (dp[s][e]) {
                sb.append("1\n");
            } else {
                sb.append("0\n");
            }
        }
        System.out.print(sb);
    }

    private static void init() throws IOException {

        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());

        arr = new int[N + 1];
        for (int i = 1; i <= N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());

        dp = new boolean[N + 1][N + 1];
    }
}