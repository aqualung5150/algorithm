import java.util.*;
import java.io.*;

public class Main {

    private static List<Integer> op = new ArrayList<>();
    private static int size;
    private static int[][][] dp;
    private static final int INF = 1000000000;

    public static void main(String[] args) throws IOException {

        //init
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        op.add(0);
        while (true) {
            int n = Integer.parseInt(st.nextToken());

            if (n == 0) {
                break;
            }
            op.add(n);
        }
        size = op.size();
        dp = new int[size][5][5];
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < 5; ++j) {
                Arrays.fill(dp[i][j], INF);
            }
        }

        dp[0][0][0] = 0;
        for (int i = 1; i < size; ++i) {
            int nextStep = op.get(i);

            for (int left = 0; left < 5; ++left) {
                for (int right = 0; right < 5; ++right) {
                    if (dp[i - 1][left][right] >= INF) {
                        continue;
                    }
                    //left step
                    int stepWeight = getStepWeight(left, nextStep);
                    dp[i][nextStep][right] = Math.min(dp[i][nextStep][right], dp[i - 1][left][right] + stepWeight);
                    //right step
                    stepWeight = getStepWeight(right, nextStep);
                    dp[i][left][nextStep] = Math.min(dp[i][left][nextStep], dp[i - 1][left][right] + stepWeight);
                }
            }
        }

        int answer = INF;
        for (int left = 0; left < 5; ++left) {
            for (int right = 0; right < 5; ++right) {
                answer = Math.min(answer, dp[size - 1][left][right]);
            }
        }
        System.out.print(answer);

        br.close();
    }

    private static int getStepWeight(int from, int to) {

        if (from == 0) {
            return 2;
        } else if (from == to) {
            return 1;
        } else if ((from + to) % 2 == 0) {
            return 4;
        } else {
            return 3;
        }
    }
}