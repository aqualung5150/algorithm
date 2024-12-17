import java.util.*;
import java.io.*;

public class Main {

    private static int N, S;
    private static Picture[] pictures;
    private static int[] dp;

    public static void main(String[] args) throws IOException {

        init();

        Arrays.sort(pictures, (o1, o2) -> o1.h - o2.h);

        dp[0] = pictures[0].c;
        for (int i = 1; i < N; ++i) {

            int prevDp = 0;
            int target = pictures[i].h - S;
            if (target >= pictures[0].h) {
                prevDp = dp[upperBound(target, 0, i) - 1];
            }

            dp[i] = Math.max(dp[i - 1], prevDp + pictures[i].c);
        }

        System.out.print(dp[N - 1]);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        pictures = new Picture[N];
        dp = new int[N];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            pictures[i] = new Picture(H, C);
        }

        br.close();
    }

    private static int upperBound(int target, int left, int right) {
        
        while (left < right) {
            int mid = (left + right) / 2;

            if (pictures[mid].h <= target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }

    static class Picture {
        int h;
        int c;

        public Picture(int h, int c) {
            this.h = h;
            this.c = c;
        }
    }
}