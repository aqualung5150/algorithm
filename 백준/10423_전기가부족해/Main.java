import java.util.*;
import java.io.*;

public class Main {

    private static int N, M, K;
    private static int[] parents;
    private static Edge[] edges;

    public static void main(String[] args) throws IOException {

        init();

        Arrays.sort(edges, (o1, o2) -> o1.w - o2.w);

        int answer = 0;
        for (Edge e : edges) {
            int u = e.u;
            int v = e.v;
            int w = e.w;

            if (unionRoot(u, v)) {
                answer += w;
            }

            if (isFinish()) {
                break;
            }
        }

        System.out.print(answer);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        parents = new int[N + 1];
        edges = new Edge[M];

        for (int i = 1; i <= N; ++i) {
            parents[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; ++i) {
            int station = Integer.parseInt(st.nextToken());
            parents[station] = 0;
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(u, v, w);
        }

        br.close();
    }

    private static boolean isFinish() {
        for (int i = 1; i <= N; ++i) {
            if (parents[i] != 0) {
                return false;
            }
        }
        return true;
    }

    private static int findRoot(int x) {

        if (parents[x] == x) {
            return x;
        }
        return parents[x] = findRoot(parents[x]);
    }

    private static boolean unionRoot(int x, int y) {

        x = findRoot(x);
        y = findRoot(y);

        if (x == y) {
            return false;
        }

        if (x < y) {
            parents[y] = x;
        } else if (y < x) {
            parents[x] = y;
        }

        return true;
    }

    static class Edge {
        public int u;
        public int v;
        public int w;

        public Edge(int u, int v, int w) {
            this.u = u;
            this.v = v;
            this.w = w;
        }
    }
}