import java.util.*;
import java.io.*;

public class Main {

    private static int N = 0;
    private static int[] warps;

    public static void main(String[] args) throws IOException {

        init();

        bfs();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N += Integer.parseInt(st.nextToken());
        N += Integer.parseInt(st.nextToken());

        warps = new int[101];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            warps[x] = y;
        }

        br.close();
    }

    private static void bfs() {

        int[] dist = new int[101];
        Arrays.fill(dist, -1);

        Queue<Integer> q = new LinkedList<>();
        q.offer(1);
        dist[1] = 0;

        while (!q.isEmpty()) {

            int here = q.poll();
            int curDist = dist[here];

            if (here == 100) {
                System.out.print(curDist);
                return;
            }

            for (int i = 1; i <= 6; ++i) {

                int there = here + i;

                if (there > 100) {
                    continue;
                }

                if (warps[there] != 0) {
                    there = warps[there];
                }

                if (dist[there] != -1) {
                    continue;
                }

                q.offer(there);
                dist[there] = curDist + 1;
            }
        }
    }
}