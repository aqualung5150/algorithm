import java.util.*;
import java.io.*;

public class Main {

    private static int M, N, H;
    private static int totalSize;
    private static int[][][] dist;
    private static List<Point> startPoint = new ArrayList<>();

    private static final int dx[] = {1, -1, 0, 0, 0, 0};
    private static final int dy[] = {0, 0, 1, -1, 0, 0};
    private static final int dz[] = {0, 0, 0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        init();

        int answer = bfs();

        if (totalSize != 0) {
            System.out.print(-1);
        } else {
            System.out.print(answer);
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        dist = new int[H][N][M];
        totalSize = M * N * H;

        for (int z = 0; z < H; ++z) {
            for (int y = 0; y < N; ++y) {
                st = new StringTokenizer(br.readLine());
                for (int x = 0; x < M; ++x) {
                    int input = Integer.parseInt(st.nextToken());
                    if (input != 0) {
                        totalSize--;
                        dist[z][y][x] = 0;
                        if (input == 1) {
                            startPoint.add(new Point(x, y, z));
                        }
                    } else {
                        dist[z][y][x] = -1;
                    }
                }
            }
        }

        br.close();
    }

    private static int bfs() {
        int answer = 0;

        Queue<Point> q = new LinkedList<>();
        for (int i = 0; i < startPoint.size(); ++i) {
            q.offer(startPoint.get(i));
        }

        while (!q.isEmpty()) {
            Point here = q.poll();
            int x = here.x;
            int y = here.y;
            int z = here.z;
            int curDist = dist[z][y][x];

            answer = Math.max(answer, curDist);

            for (int i = 0; i < 6; ++i) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nz = z + dz[i];

                if (nx < 0 || nx >= M || ny < 0 || ny >= N || nz < 0 || nz >= H || dist[nz][ny][nx] != -1) {
                    continue;
                }

                totalSize--;
                q.offer(new Point(nx, ny, nz));
                dist[nz][ny][nx] = curDist + 1;
            }
        }

        return answer;
    }

    static class Point {
        public int x;
        public int y;
        public int z;

        public Point(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}