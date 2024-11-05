import java.util.*;
import java.io.*;

public class Main {

    private static int T;
    private static int N;
    private static StringBuilder sb;
    private static final char[] ops = {' ', '+', '-'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            sb = new StringBuilder();
            sb.append('1');

            N = Integer.parseInt(br.readLine());

            dfs(1);
            System.out.println();
        }
    }

    private static void dfs(int n) {
        if (n == N) {
            if (isZero()) {
                System.out.println(sb);
            }
        } else {
            int nextN = n + 1;

            for (int i = 0; i < 3; ++i) {
                sb.append(ops[i]);
                sb.append(nextN);
                dfs(nextN);
                sb.delete(sb.length() - 2, sb.length());
            }
        }
    }

    private static boolean isZero() {
        int sum = 0;
        int toOp = 1;
        char op = '+';

        for (int i = 2; i < sb.length(); i += 2) {
            char nextOp = sb.charAt(i - 1);
            if (nextOp != ' ') {
                sum = operate(sum, toOp, op);
                toOp = sb.charAt(i) - '0';
                op = nextOp;
            } else {
                toOp = toOp * 10 + (sb.charAt(i) - '0');
            }
        }

        sum = operate(sum, toOp, op);

        if (sum == 0) {
            return true;
        }
        return false;
    }

    private static int operate(int prevSum, int prevNum, int prevOp) {
        if (prevOp == '+') {
            return prevSum + prevNum;
        } else {
            return prevSum - prevNum;
        }
    }
}