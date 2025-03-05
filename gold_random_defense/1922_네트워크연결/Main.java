import java.util.*;
import java.io.*;

public class Main {

    private static int N, M;
    private static int[] parent;
    private static PriorityQueue<Edge> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {

        init();

        int totalCost = 0;
        int edgeCount = 0;
        while (edgeCount < N - 1) {

            Edge cur = pq.poll();
            int v1 = cur.v1;
            int v2 = cur.v2;
            int cost = cur.cost;

            if (findRoot(v1) == findRoot(v2)) {
                continue;
            }

            unionRoot(v1, v2);
            totalCost += cost;
            ++edgeCount;
        }

        System.out.print(totalCost);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        parent = new int[N + 1];
        for (int i = 1; i <= N; ++i) {
            parent[i] = i;
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            pq.offer(new Edge(
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
            ));
        }

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

    static class Edge implements Comparable<Edge> {

        public int v1;
        public int v2;
        public int cost;

        public Edge(int v1, int v2, int cost) {
            this.v1 = v1;
            this.v2 = v2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge e) {
            return this.cost - e.cost;
        }
    }
}