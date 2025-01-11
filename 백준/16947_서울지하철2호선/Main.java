import java.util.*;
import java.io.*;

/*
간선과 정점의 갯수가 같으니까 사이클은 1개
*/

public class Main {

    private static int N;
    private static List<Integer>[] edges;
    private static int[] prev;
    private static boolean[] visited;
    private static boolean[] isCycle;
    private static int bfsStart;
    private static int[] dist;

    public static void main(String[] args) throws IOException {

        init();

        visited[1] = true;
        initCycle(1);

        bfs();

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= N; ++i) {
            sb.append(dist[i] + " ");
        }
        System.out.print(sb);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        edges = new List[N + 1];
        prev = new int[N + 1];
        visited = new boolean[N + 1];
        isCycle = new boolean[N + 1];
        dist = new int[N + 1];
        Arrays.fill(dist, -1);

        for (int i = 0; i <= N; ++i) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());

            edges[v1].add(v2);
            edges[v2].add(v1);
        }

        br.close();
    }

    private static boolean initCycle(int cur) {

        for (int next : edges[cur]) {

            if (next == prev[cur]) {
                continue;
            }

            if (visited[next]) {
                //cycle
                int end = prev[next];
                bfsStart = next;
                while (cur != end) {
                    isCycle[cur] = true;
                    cur = prev[cur];
                }

                return true;
            }

            prev[next] = cur;
            visited[next] = true;
            if (initCycle(next)) {
                return true;
            }
        }

        return false;
    }

    private static void bfs() {

        Queue<Integer> q = new LinkedList<>();
        q.offer(bfsStart);
        dist[bfsStart] = 0;

        while (!q.isEmpty()) {

            int here = q.poll();

            for (int there : edges[here]) {

                if (dist[there] != -1) {
                    continue;
                }

                int nextDist;
                if (isCycle[there]) {
                    nextDist = 0;
                } else {
                    nextDist = dist[here] + 1;
                }

                q.offer(there);
                dist[there] = nextDist;
            }
        }
    }
}