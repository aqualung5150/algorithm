import java.util.*;
import java.io.*;

public class Main {

    private static int N, M;
    private static int[] parents;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        parents = new int[N + 1];

        for (int i = 1; i <= N; ++i) {
            parents[i] = i;
        }

        for (int i = 1; i <= N; ++i) {

            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; ++j) {

                if (st.nextToken().equals("1")) {
                    unionRoot(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int parent = findRoot(Integer.parseInt(st.nextToken()));
        for (int i = 1; i < M; ++i) {
            if (findRoot(Integer.parseInt(st.nextToken())) != parent) {
                System.out.print("NO");
                return;
            }
        }

        System.out.print("YES");

        br.close();
    }

    private static int findRoot(int x) {
        if (parents[x] == x) {
            return x;
        }

        return parents[x] = findRoot(parents[x]);
    }

    private static void unionRoot(int x, int y) {
        x = findRoot(x);
        y = findRoot(y);

        if (x != y) {
            parents[y] = x;
        }
    }
}