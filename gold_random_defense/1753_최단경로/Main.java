import java.util.*;
import java.io.*;

public class Main {

    private static final int INF = 1000000000;
    private static int V, E, K;
    private static List<Edge>[] edges;
    private static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(br.readLine());

        edges = new List[V + 1];
        for (int i = 1; i <= V; ++i) {
            edges[i] = new ArrayList<>();
        }

        dist = new int[V + 1];
        Arrays.fill(dist, INF);

        for (int i = 0; i < E; ++i) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            edges[u].add(new Edge(v, w));
        }

        br.close();

        bfs();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= V; ++i) {
            if (dist[i] != INF) {
            sb.append(dist[i]).append("\n");
            } else {
                sb.append("INF\n");
            }
        }

        System.out.print(sb);
    }

    private static void bfs() {
        
        PriorityQueue<Pos> pq = new PriorityQueue<>();
        pq.offer(new Pos(K, 0));
        dist[K] = 0;

        while (!pq.isEmpty()) {

            Pos pos = pq.poll();
            int here = pos.v;
            int curDist = pos.dist;

            if (curDist > dist[here]) {
                continue;
            }

            for (Edge edge: edges[here]) {

                int there = edge.to;
                int nextDist = curDist + edge.weight;

                if (nextDist >= dist[there]) {
                    continue;
                }

                pq.offer(new Pos(there, nextDist));
                dist[there] = nextDist;
            }
        }
    }

    static class Pos implements Comparable<Pos> {
        public int v;
        public int dist;

        public Pos(int v, int dist) {
            this.v = v;
            this.dist = dist;
        }

        @Override
        public int compareTo(Pos p) {
            return this.dist - p.dist;
        }
    }

    static class Edge {
        public int to;
        public int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}