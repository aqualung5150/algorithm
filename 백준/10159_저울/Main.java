import java.util.*;
import java.io.*;

public class Main {

    private static int N, M;
    private static List<Integer>[] edges;
    private static List<Integer>[] r_edges;

    public static void main(String[] args) throws IOException {

        init();

        for (int i = 0; i < N; ++i) {
            bfs(i);
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        edges = new List[N];
        r_edges = new List[N];
        for (int i = 0; i < N; ++i) {
            edges[i] = new ArrayList<Integer>();
            r_edges[i] = new ArrayList<Integer>();
        }
        for (int i = 0; i < M; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken()) - 1;
            int b = Integer.parseInt(st.nextToken()) - 1;
            edges[a].add(b);
            r_edges[b].add(a);
        }
        br.close();
    }

    private static void bfs(int start) {

        int count = 1;
        boolean[] visited = new boolean[N];
        Queue<Integer> q = new LinkedList<>();
        
        visited[start] = true;

        q.offer(start);
        while (!q.isEmpty()) {
            int here = q.poll();

            for (int i = 0; i < edges[here].size(); ++i) {
                int there = edges[here].get(i);

                if (visited[there]) {
                    continue;
                }

                q.offer(there);
                visited[there] = true;
                count++;
            }
        }

        q.offer(start);
        while (!q.isEmpty()) {
            int here = q.poll();

            for (int i = 0; i < r_edges[here].size(); ++i) {
                int there = r_edges[here].get(i);

                if (visited[there]) {
                    continue;
                }
                
                q.offer(there);
                visited[there] = true;
                count++;
            }
        }

        System.out.println(N - count);
    }
}