import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static PriorityQueue<Integer>[] edges;
    private static int[][] edgeTemp;
    private static boolean[] visited;
    private static int[] order;
    private static int[] path;
    private static int idx = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        edgeTemp = new int[N - 1][2];
        edges = new PriorityQueue[N + 1];
        for (int i = 0; i <= N; ++i) {
            edges[i] = new PriorityQueue<>((o1, o2) -> order[o1] - order[o2]);
        }
        visited = new boolean[N + 1];
        path = new int[N];
        order = new int[N + 1];

        for (int i = 0; i < N - 1; ++i) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            edgeTemp[i][0] = v1;
            edgeTemp[i][1] = v2;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            int node = Integer.parseInt(st.nextToken());
            order[node] = i;
            path[i] = node;
        }

        for (int[] e : edgeTemp) {
            int v1 = e[0];
            int v2 = e[1];
            edges[v1].offer(v2);
            edges[v2].offer(v1);
        }

        dfs(1);

        br.close();
    }

    private static void dfs(int n) {

        visited[n] = true;

        ++idx;

        if (idx == N) {
            System.out.print("1");
            return;
        }

        while (!edges[n].isEmpty()) {
            int e = edges[n].poll();

            if (visited[e]) {
                continue;
            }

            if (e != path[idx]) {
                System.out.print("0");
                return;
            }

            dfs(e);
        }
    }
}