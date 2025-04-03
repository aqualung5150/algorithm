import java.util.*;
import java.io.*;

public class Main {

    private static final int INF = 1000000000;
    private static int N, M;
    private static int[][] edges;
    private static int start, end;

    public static void main(String[] args) throws IOException {

        init();
        bfs();
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        edges = new int[N + 1][N + 1];
        for (int[] edge: edges) {
            Arrays.fill(edge, INF);
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges[v1][v2] = Math.min(edges[v1][v2], w);
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        br.close();
    }

    private static void bfs() {

        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);

        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Pos cur = pq.poll();
            int here = cur.n;
            int curDist = cur.dist;

            if (here == end) {
                System.out.print(dist[end]);
                return;
            }
            
            if (curDist > dist[here]) {
                continue;
            }

            for (int there = 1; there <= N; ++there) {
                int w = edges[here][there];
                if (w == INF) {
                    continue;
                }

                int nextDist = curDist + w;

                if (nextDist < dist[there]) {
                    pq.offer(new Pos(there, nextDist));
                    dist[there] = nextDist;
                }
            }
        }
    }

    static class Edge {
        public int to;
        public int w;

        public Edge(int to, int w) {
            this.to = to;
            this.w = w;
        }
    }

    static class Pos implements Comparable<Pos> {
        public int n;
        public int dist;

        public Pos(int n, int dist) {
            this.n = n;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pos p) {
            return this.dist - p.dist;
        }
    }
}