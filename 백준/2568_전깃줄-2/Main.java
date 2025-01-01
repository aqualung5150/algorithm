import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int[] lines;
    private static List<Integer> validLines = new ArrayList<>();
    private static int[] dp;
    private static int[] count;

    public static void main(String[] args) throws IOException {

        init();
        
        int firstLineIdx = validLines.get(0);
        dp[0] = lines[firstLineIdx];
        count[firstLineIdx] = 1;
        int idx = 0;
        for (int i : validLines) {

            if (lines[i] > dp[idx]) {
                dp[++idx] = lines[i];
                count[i] = idx + 1;
            } else {
                int removeAt = lowerBound(lines[i], 0, idx);
                dp[removeAt] = lines[i];
                count[i] = removeAt + 1;
            }
        }

        Stack<Integer> removed = new Stack<>();
        int maxCount = idx + 1;
        for (int i = N - 1; i >= 0; --i) {
            int cur = validLines.get(i);

            if (count[cur] != maxCount) {
                removed.push(cur);
            } else {
                --maxCount;
            }
        }

        //print
        StringBuilder sb = new StringBuilder();
        sb.append(removed.size() + "\n");
        while (!removed.isEmpty()) {
            sb.append(removed.pop() + "\n");
        }
        System.out.print(sb);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        lines = new int[500001];
        dp = new int[N];
        count = new int[500001];

        for (int i = 0; i < N; ++i) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            lines[A] = B;
        }

        for (int i = 1; i <= 500000; ++i) {
            if (lines[i] != 0) {
                validLines.add(i);
            }
        }

        br.close();
    }

    private static int lowerBound(int target, int left, int right) {

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