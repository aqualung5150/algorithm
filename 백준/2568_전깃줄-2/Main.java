import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static Line[] lines;
    private static Line[] dp;
    private static int[] count;
    private static List<Integer> removed = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        init();

        Arrays.sort(lines, (o1, o2) -> o1.A - o2.A);

        dp[0] = lines[0];
        count[0] = 1;
        int idx = 0;
        for (int i = 1; i < N; ++i) {

            if (lines[i].B > dp[idx].B) {
                dp[++idx] = lines[i];
                count[i] = idx + 1;
            } else {
                int removeAt = lowerBound(lines[i], 0, idx);
                dp[removeAt] = lines[i];
                count[i] = removeAt + 1;
            }
        }

        int maxCount = idx + 1;
        for (int i = N - 1; i >= 0; --i) {
            if (count[i] != maxCount) {
                removed.add(lines[i].A);
            } else {
                --maxCount;
            }
        }
        
        Collections.sort(removed);

        System.out.println(removed.size());
        for (int r : removed) {
            System.out.println(r);
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        lines = new Line[N];
        dp = new Line[N];
        count = new int[N];

        for (int i = 0; i < N; ++i) {

            StringTokenizer st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            lines[i] = new Line(A, B);
        }

        br.close();
    }

    private static int lowerBound(Line target, int left, int right) {

        while (left < right) {
            int mid = (left + right) / 2;

            if (dp[mid].B < target.B) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }

    static class Line {
        public int A;
        public int B;

        public Line(int A, int B) {
            this.A = A;
            this.B = B;
        }
    }
}