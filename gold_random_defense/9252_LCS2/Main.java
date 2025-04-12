import java.util.*;
import java.io.*;

public class Main {

    private static String strA, strB;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        strA = br.readLine();
        strB = br.readLine();

        int sizeA = strA.length();
        int sizeB = strB.length();
        dp = new int[sizeA + 1][sizeB + 1];

        for (int i = 1; i <= sizeA; ++i) {
            for (int j = 1; j <= sizeB; ++j) {
                char a = strA.charAt(i - 1);
                char b = strB.charAt(j - 1);
                if (a == b) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j]);
                }
            }
        }

        Stack<Character> stk = new Stack<>();
        int i = sizeA;
        int j = sizeB;
        while (i > 0 && j > 0) {
            char a = strA.charAt(i - 1);
            char b = strB.charAt(j - 1);
            if (a == b) {
                stk.push(a);
                --i;
                --j;
            } else if (dp[i][j - 1] > dp[i - 1][j]) {
                --j;
            } else {
                --i;
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stk.isEmpty()) {
            sb.append(stk.pop());
        }
        System.out.println(dp[sizeA][sizeB]);
        System.out.print(sb);

        br.close();        
    }
}