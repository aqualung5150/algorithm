import java.util.*;
import java.io.*;

public class Main {

    private static final int INF = 200001;
    private static int N, K;
    private static int[] dist, count;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dist = new int[INF];
        Arrays.fill(dist, INF);
        count = new int[INF];
        Arrays.fill(count, 1);

        bfs();

        System.out.println(dist[K]);
        System.out.print(count[K]);

        br.close();
    }

    private static void bfs() {

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(N);
        dist[N] = 0;

        while (!q.isEmpty()) {

            int here = q.poll();

            int nextDist = dist[here] + 1;
            int there = here + 1;
            if (there < INF) {
                countAndDistance(q, here, there);
            }

            there = here - 1;
            if (there >= 0) {
                countAndDistance(q, here, there);
            }

            there = here * 2;
            if (there < INF) {
                countAndDistance(q, here, there);
            }
        }

    }

    private static void countAndDistance(Queue<Integer> q, int here, int there) {

        int nextDist = dist[here] + 1;

        if (nextDist < dist[there]) {
            count[there] = count[here];
            dist[there] = nextDist;
            q.offer(there);
        } else if (nextDist == dist[there]) {
            count[there] += count[here];
        }
    }
}