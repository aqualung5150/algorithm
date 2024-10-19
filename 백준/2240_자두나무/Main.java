import java.util.*;
import java.io.*;

public class Main {

    private static int T;
    private static int W;
    private static int[] arr;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        arr = new int[T + 1];
        dp = new int[W + 1][T + 1];

        for (int i = 1; i <= T; ++i) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        // i: arr의 인덱스, j: 움직인 횟수
        for (int i = 1; i <= T; ++i) {
            for (int j = 0; j <= W && j <= i; ++j) {
                if (j == 0) {
                    dp[j][i] = dp[j][i - 1];
                } else {
                    dp[j][i] = Math.max(dp[j - 1][i - 1], dp[j][i - 1]);
                }
                // 움직인 횟수로 현재 위치가 1번인지 2번인지 알 수 있음
                int pos = j % 2 == 0 ? 1 : 2;
                if (pos == arr[i]) {
                    dp[j][i]++;
                }
            }
        }

        int answer = 0;
        for (int i = 0; i <= W; ++i) {
            answer = Math.max(answer, dp[i][T]);
        }
        System.out.print(answer);
    }
}