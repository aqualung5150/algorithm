import java.util.*;
import java.io.*;

public class Main {

    private static final int INF = 200001;
    private static int N, K;
    private static int[] dist, prev;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dist = new int[INF];
        Arrays.fill(dist, INF);
        prev = new int[INF];
        Arrays.fill(prev, INF);

        bfs();

        br.close();
    }

    private static void bfs() {

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(N);
        dist[N] = 0;
        prev[N] = -1;

        while (!q.isEmpty()) {

            Integer here = q.poll();

            if (here == K) {
                System.out.println(dist[here]);
                Stack<Integer> stk = new Stack<>();
                while (here != -1) {
                    stk.push(here);
                    here = prev[here];
                }
                StringBuilder sb = new StringBuilder();
                while (!stk.isEmpty()) {
                    sb.append(stk.pop()).append(" ");
                }
                System.out.print(sb);
                return;
            }

            int nextDist = dist[here] + 1;
            int there = here + 1;
            if (there < INF && dist[there] == INF) {
                q.offer(there);
                dist[there] = nextDist;
                prev[there] = here;
            }

            there = here - 1;
            if (there >= 0 && dist[there] == INF) {
                q.offer(there);
                dist[there] = nextDist;
                prev[there] = here;
            }

            there = here * 2;
            if (there < INF && dist[there] == INF) {
                q.offer(there);
                dist[there] = nextDist;
                prev[there] = here;
            }
        }

    }
}