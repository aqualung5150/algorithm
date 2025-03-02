import java.util.*;
import java.io.*;

public class Main {

    private static int V, E;
    private static int[] parent;
    private static PriorityQueue<Edge> pq = new PriorityQueue<>();

    public static void main(String[] args) throws IOException {

        init();

        int count = 0;
        int answer = 0;
        while (count < V - 1) {

            Edge here = pq.poll();
            int v1 = here.v1;
            int v2 = here.v2;
            int w = here.w;

            if (findRoot(v1) != findRoot(v2)) {
                ++count;
                answer += w;

                unionRoot(v1, v2);
            }
        }

        System.out.print(answer);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parent = new int[V + 1];

        for (int i = 1; i <= E; ++i) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            pq.offer(new Edge(A, B, C));
        }

        br.close();

        for (int i = 1; i <= V; ++i) {
            parent[i] = i;
        }
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
        public int w;

        public Edge(int v1, int v2, int w) {
            this.v1 = v1;
            this.v2 = v2;
            this.w = w;
        }

        @Override
        public int compareTo(Edge e) {
            return this.w - e.w;
        }
    }
}