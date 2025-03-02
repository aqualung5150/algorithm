import java.util.*;
import java.io.*;

public class Main {

    private static int N, M;
    private static int[] parent;
    private static int[][] parties;
    private static List<Edge> edges = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        init();

        for (Edge edge : edges) {
            unionRoot(edge.v1, edge.v2);
        }

        for (int[] party : parties) {
            for (int p : party) {
                if (findRoot(p) == 0) {
                    --M;
                    break;
                }
            }
        }

        System.out.print(M);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        parent = new int[N + 1];
        for (int i = 1; i <= N; ++i) {
            parent[i] = i;
        }

        st = new StringTokenizer(br.readLine());
        int numberOfKnown = Integer.parseInt(st.nextToken());
        for (int i = 0; i < numberOfKnown; ++i) {
            int known = Integer.parseInt(st.nextToken());
            parent[known] = 0;
        }

        parties = new int[M][];
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int numberOfPeople = Integer.parseInt(st.nextToken());

            parties[i] = new int[numberOfPeople];

            int v1 = Integer.parseInt(st.nextToken());
            parties[i][0] = v1;
            for (int j = 1; j < numberOfPeople; ++j) {

                int v2 = Integer.parseInt(st.nextToken());
                edges.add(new Edge(v1, v2));
                v1 = v2;

                parties[i][j] = v1;
            }
        }

        br.close();
    }

    private static int findRoot(int x) {
        if (parent[x] == x || x == 0) {
            return x;
        }

        return parent[x] = findRoot(parent[x]);
    }

    private static void unionRoot(int x, int y) {
        x = findRoot(x);
        y = findRoot(y);

        if (x < y) {
            parent[y] = x;
        } else {
            parent[x] = y;
        }
    }

    static class Edge {

        public int v1;
        public int v2;

        public Edge(int v1, int v2) {
            this.v1 = v1;
            this.v2 = v2;
        }
    }
}