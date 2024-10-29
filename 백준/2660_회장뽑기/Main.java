import java.util.*;
import java.io.*;

public class Main {

    private static final int INF = 100;
    private static int N;
    private static int dp[][];

    public static void main(String[] args) throws IOException {

        //init
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        dp = new int[N + 1][N + 1];
        for (int i = 0; i <= N; ++i) {
            Arrays.fill(dp[i], 100);
        }
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (a == -1 && b == -1) {
                break;
            }

            dp[a][b] = 1;
            dp[b][a] = 1;
        }

        //floyd-warshall
        for (int k = 1; k <= N; ++k) {
            for (int i = 1; i <= N; ++i) {
                for (int j = 1; j <= N; ++j) {
                    if (i == j) {
                        dp[i][j] = 0;
                        continue;
                    }
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }

        int minScore = 100;
        Map<Integer, List<Integer>> answer = new HashMap<>();
        for (int i = 1; i <= N; ++i) {
            int curScore = 0;
            for (int j = 1; j <= N; ++j) {
                    curScore = Math.max(curScore, dp[i][j]);
            }
            if (!answer.containsKey(curScore)) {
                answer.put(curScore, new ArrayList<Integer>());
            }
            answer.get(curScore).add(i);

            minScore = Math.min(minScore, curScore);
        }

        System.out.println(minScore + " " + answer.get(minScore).size());
        List<Integer> list = answer.get(minScore);
        list.sort(Comparator.naturalOrder());
        list.forEach(i -> System.out.print(i + " "));
    }
}