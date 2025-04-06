import java.util.*;
import java.io.*;

public class Main {

    private static int N = 0;
    private static int[] edges = new int[101];

    public static void main(String[] args) throws IOException {

        init();
        bfs();
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N += Integer.parseInt(st.nextToken());
        N += Integer.parseInt(st.nextToken());

        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            edges[from] = to;
        }
    }

    private static void bfs() {

        int[] dist = new int[101];
        Arrays.fill(dist, -1);

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(1);
        dist[1] = 0;

        while (!q.isEmpty()) {
            int here = q.poll();
            int curDist = dist[here];

            if (here == 100) {
                System.out.print(curDist);
                break;
            }

            for (int i = 1; i <= 6; ++i) {

                int there = here + i;

                if (there > 100) {
                    continue;
                }

                while (edges[there] != 0) {
                    there = edges[there];
                }

                if (dist[there] != -1) {
                    continue;
                }

                dist[there] = curDist + 1;
                q.offer(there);
            }
        }
    }
}