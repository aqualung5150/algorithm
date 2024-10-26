import java.util.*;
import java.io.*;

public class Main {

    private static int T;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; ++t) {
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            int[] arr = new int[n];
            StringTokenizer st = new StringTokenizer(br.readLine(), "[],");
            for (int i = 0; i < n; ++i) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            System.out.println(ac(p, n, arr));
        }
    }

    private static String ac(String p, int size, int[] arr) {
        int left = 0;
        int right = size - 1;
        boolean toggle = false; //false: left, true: right

        for (int i = 0; i < p.length(); ++i) {
            char op = p.charAt(i);

            if (op == 'R') {
                toggle = !toggle;
            } else {
                if (left > right) {
                    return "error";
                }
                
                if (!toggle) {
                    ++left;
                } else {
                    --right;
                }
            }
        }

        if (left > right) {
            return "[]";
        }

        StringBuilder sb = new StringBuilder("[");

        if (!toggle) {
            while (left <= right) {
                sb.append(arr[left]);
                if (left != right)
                    sb.append(",");
                ++left;
            }
        } else {
            while (right >= left) {
                sb.append(arr[right]);
                if (left != right)
                    sb.append(",");
                --right;
            }
        }
        sb.append("]");
        return sb.toString();
    }
}