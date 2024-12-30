import java.util.*;
import java.io.*;

public class Main {

    private static String str;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        str = br.readLine();

        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < str.length(); ++i) {
            char c = str.charAt(i);

            if (c != ')') {
                if (c == '(') {
                    stk.push(-1);
                } else if (i < str.length() - 1 && str.charAt(i + 1) == '(') {
                    stk.push(c - '0');
                } else {
                    stk.push(1);
                }
            } else {

                int count = 0;

                while (true) {

                    int cur = stk.pop();

                    if (cur == -1) {
                        break;
                    }

                    count += cur;
                }

                count *= stk.pop();

                stk.push(count);
            }
        }

        int answer = 0;
        while (!stk.isEmpty()) {
            answer += stk.pop();
        }

        System.out.print(answer);

        br.close();
    }
}