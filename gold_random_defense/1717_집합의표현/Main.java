import java.util.*;
import java.io.*;

public class Main {

    private static int n, m;
    private static int[] parent;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        parent = new int[n + 1];
        for (int i = 0; i <= n; ++i) {
            parent[i] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; ++i) {
            st = new StringTokenizer(br.readLine());
            String op = st.nextToken();
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if (op.equals("0")) {
                unionRoot(a, b);
            } else {
                if (findRoot(a) == findRoot(b)) {
                    sb.append("YES\n");
                } else {
                    sb.append("NO\n");
                }
            }
        }

        System.out.print(sb);

        br.close();
    }

    private static int findRoot(int x) {
        if (parent[x] == x) {
            return x;
        }

        return parent[x] = findRoot(parent[x]);
    }

    private static void unionRoot(int x, int y) {
        x = findRoot(x);
        y = findRoot(y);

        if (x != y) {
            parent[y] = x;
        }
    }
}