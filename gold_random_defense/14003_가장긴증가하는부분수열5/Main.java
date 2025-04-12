import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int[] arr, dp, dp2;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N];
        dp2 = new int[N];
        dp[0] = arr[0];
        dp2[0] = 0;
        int lastIdx = 0;
        for (int i = 1; i < N; ++i) {
            int cur = arr[i];
            if (cur > dp[lastIdx]) {
                ++lastIdx;
                dp[lastIdx] = cur;
                dp2[i] = lastIdx;
            } else {
                int insertIdx = lowerBound(cur, 0, lastIdx);
                dp[insertIdx] = cur;
                dp2[i] = insertIdx;
            }
        }

        System.out.println(lastIdx + 1);

        Stack<Integer> stk = new Stack<>();
        for (int i = N - 1; i >= 0; --i) {
            if (dp2[i] == lastIdx) {
                --lastIdx;
                stk.push(arr[i]);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stk.isEmpty()) {
            sb.append(stk.pop() + " ");
        }
        System.out.print(sb);
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