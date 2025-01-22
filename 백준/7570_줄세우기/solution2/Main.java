import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int[] arr;
    private static int[] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int maxCount = 0;
        for (int i = 0; i < N; ++i) {
            int cur = arr[i];

            dp[cur] = dp[cur - 1] + 1;

            maxCount = Math.max(maxCount, dp[cur]);
        }

        System.out.print(N - maxCount);

        br.close();
    }
}