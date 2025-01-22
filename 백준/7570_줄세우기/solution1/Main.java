import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int[] arr;
    private static int[] index;
    private static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N+ 1];
        index = new int[N + 1];
        dp = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
            index[arr[i]] = i;
        }

        int maxCount = 0;
        for (int i = 1; i <= N; ++i) {
            dp[i] = 1;

            int preIndex = index[arr[i] - 1];

            if (dp[preIndex] != 0) {
                dp[i] = dp[preIndex] + 1;
            }

            maxCount = Math.max(maxCount, dp[i]);
        }

        System.out.print(N - maxCount);

        br.close();
    }
}