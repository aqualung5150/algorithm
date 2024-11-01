import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int K;
    private static String input;
    private static Stack<Character> stk = new Stack<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());        
        input = br.readLine();

        for (int i = 0; i < input.length(); ++i) {
            char cur = input.charAt(i);

            while (!stk.isEmpty() && K > 0 && stk.peek() < cur) {
                stk.pop();
                --K;
            }
            stk.push(cur);
        }

        while (K > 0) {
            --K;
            stk.pop();
        }

        StringBuilder sb = new StringBuilder();

        while (!stk.isEmpty()) {
            sb.append(stk.pop());
        }

        System.out.print(sb.reverse());
    }
}