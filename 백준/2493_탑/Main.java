import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int[] arr;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb = new StringBuilder();
        Stack<Integer> stk = new Stack<>();
        for (int i = 0; i < N; ++i) {
            int n = Integer.parseInt(st.nextToken());
            arr[i] = n;

            while (!stk.isEmpty() && arr[stk.peek()] < n) {
                stk.pop();
            }

            int hitIdx = stk.isEmpty() ? 0 : stk.peek() + 1;
            sb.append(hitIdx).append(" ");

            stk.push(i);
        }

        System.out.print(sb);
       
        br.close();
    }
}