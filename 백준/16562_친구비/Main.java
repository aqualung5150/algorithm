import java.util.*;
import java.io.*;

public class Main {

    private static int N, M, k;
    private static int[] cost;
    private static boolean[] visited;
    private static List<Integer>[] edges;

    public static void main(String[] args) throws IOException {

        init();

        int totalCost = 0;
        for (int i = 0; i < N; ++i) {
            if (visited[i]) {
                continue;
            }

            totalCost += bfs(i);
        }

        if (totalCost <= k) {
            System.out.print(totalCost);
        } else {
            System.out.print("Oh no");
        }
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        visited = new boolean[N];
        cost = new int[N];
        
        edges = new ArrayList[N];
        for (int i = 0; i < N; ++i) {
            edges[i] = new ArrayList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            cost[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken()) - 1;
            int v2 = Integer.parseInt(st.nextToken()) - 1;
            edges[v1].add(v2);
            edges[v2].add(v1);
        }
    }

    private static int bfs(int start) {
        
        int minCost = 10000;
        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int here = q.poll();

            minCost = Math.min(minCost, cost[here]);

            for (int i = 0; i < edges[here].size(); ++i) {
                int there = edges[here].get(i);

                if (visited[there]) {
                    continue;
                }

                q.offer(there);
                visited[there] = true;
            }
        }

        return minCost;
    }
}