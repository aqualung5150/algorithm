import java.util.*;
import java.io.*;

public class Main {

    private static StringTokenizer st;
    private static int N;
    private static int[] dp;

    public static void main(String[] args) throws IOException {

        init();

        int idx = 0;
        dp[0] = Integer.parseInt(st.nextToken());

        for (int i = 1; i < N; ++i) {
            int cur = Integer.parseInt(st.nextToken());

            if (cur > dp[idx]) {
                dp[++idx] = cur;
            } else {
                int insertIdx = lowerBound(cur, idx);
                dp[insertIdx] = cur;
            }
        }

        System.out.print(idx + 1);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        dp = new int[N];

        st = new StringTokenizer(br.readLine());

        br.close();
    }

    private static int lowerBound(int target, int right) {

        int left = 0;

        while (left < right) {
            int mid = (left + right) / 2;

            if (dp[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }
}