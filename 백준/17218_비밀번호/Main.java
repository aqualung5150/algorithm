import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] a = br.readLine().toCharArray();
        char[] b = br.readLine().toCharArray();

        br.close();

        int[][] dp = new int[a.length + 1][b.length + 1];

        for (int i = 1; i <= a.length; ++i) {
            char curA = a[i - 1];
            for (int j = 1; j <= b.length; ++j) {
                char curB = b[j - 1];

                if (curA == curB) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        Stack<Character> stack = new Stack<>();

        int i = a.length;
        int j = b.length;
        while (i > 0 && j > 0) {
            int cur = dp[i][j];

            if (dp[i - 1][j] != cur && dp[i][j - 1] != cur) {
                stack.push(a[i - 1]);
                --i;
                --j;
            } else if (dp[i - 1][j] == cur) {
                --i;
            } else {
                --j;
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }

        System.out.print(sb);
    }
}