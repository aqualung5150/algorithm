import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static Line[] lines;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        init();
        Arrays.sort(lines);

        int lastIdx = 0;
        dp[0] = lines[0].b;
        for (int i = 1; i < N; ++i) {
            int here = lines[i].b;
            if (here > dp[lastIdx]) {
                lastIdx++;
                dp[lastIdx] = here;
            } else {
                int insertIdx = binarySearch(lastIdx, here);
                dp[insertIdx] = here;
            }
        }

        System.out.print(N - lastIdx - 1);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        lines = new Line[N];
        dp = new int[N];
        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            lines[i] = new Line(a, b);
        }
        br.close();
    }

    private static int binarySearch(int lastIdx, int target) {
        
        int left = 0;
        int right = lastIdx;

        while (left < right) {
            int mid = (left + right) / 2;

            int midValue = dp[mid];

            if (midValue < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }

    static class Line implements Comparable<Line> {

        int a;
        int b;

        public Line(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public int compareTo(Line o) {
            return this.a - o.a;
        }
    }
}