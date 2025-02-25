import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int[] arr;
    private static int[][] dp; //0: asc, 1: desc
    private static int[][] temp;

    public static void main(String[] args) throws IOException {

        init();

        int idx = 0;
        dp[0][0] = 1;
        temp[0][0] = arr[0];
        for (int i = 1; i < N; ++i) {

            int cur = arr[i];
            if (cur > temp[0][idx]) {
                ++idx;
                temp[0][idx] = cur;
                dp[0][i] = idx + 1;
            } else {
                int insertTo = lowerBound(temp[0], cur, 0, idx);
                temp[0][insertTo] = cur;
                dp[0][i] = insertTo + 1;
            }
        }

        idx = 0;
        dp[1][N - 1] = 1;
        temp[1][0] = arr[N - 1];
        for (int i = N - 2; i >= 0; --i) {

            int cur = arr[i];
            if (cur > temp[1][idx]) {
                ++idx;
                temp[1][idx] = cur;
                dp[1][i] = idx + 1;
            } else {
                int insertTo = lowerBound(temp[1], cur, 0, idx);
                temp[1][insertTo] = cur;
                dp[1][i] = insertTo + 1;
            }
        }

        int answer = 1;
        for (int i = 0; i < N - 1; ++i) {

            int curMax = 0;
            for (int j = i + 1; j < N; ++j) {
                if (arr[i] != arr[j]) {
                    curMax = Math.max(curMax, dp[0][i] + dp[1][j]);
                }
            }

            answer = Math.max(answer, curMax);
        }
        System.out.print(answer);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new int[2][N];
        temp = new int[2][N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        br.close();
    }

    private static int lowerBound(int[] arr, int n, int left, int right) {

        while (left < right) {
            int mid = (left + right) / 2;

            if (arr[mid] < n) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }
}