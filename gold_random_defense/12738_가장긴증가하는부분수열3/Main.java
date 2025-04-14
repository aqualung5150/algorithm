import java.util.*;
import java.io.*;

public class Main {

    private static int N, M;
    private static int[] arr, dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N];
        dp[0] = arr[0];
        int lastIdx = 0;
        for (int i = 1; i < N; ++i) {
            int cur = arr[i];
            if (cur > dp[lastIdx]) {
                ++lastIdx;
                dp[lastIdx] = cur;
            } else {
                int insertIdx = lowerBound(cur, 0, lastIdx);
                dp[insertIdx] = cur;
            }
        }

        System.out.print(lastIdx + 1);
    }

    private static int lowerBound(int target, int left, int right) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (target > dp[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return right;
    }
}
