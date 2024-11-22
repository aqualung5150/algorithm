import java.util.*;
import java.io.*;

public class Main {

    private static int W, H;
    private static int[][][] dist; //0 가로, 1 세로
    private static List<Point> target = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        init();

        bfs();
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        dist = new int[2][H][W];

        for (int i = 0; i < H; ++i) {
            String read = br.readLine();
            for (int j = 0; j < W; ++j) {
                char c = read.charAt(j);
                if (c == '*') {
                    dist[0][i][j] = 0;
                    dist[1][i][j] = 0;
                } else {
                    if (c == 'C') {
                        target.add(new Point(j, i, 0));
                    }
                    dist[0][i][j] = -1;
                    dist[1][i][j] = -1;
                }
            }
        }

        br.close();
    }

    private static void bfs() {
        Point start = target.get(0);
        Point end = target.get(1);
        Queue<Point> q = new LinkedList<>();
        q.offer(new Point(start.x, start.y, 0));
        q.offer(new Point(start.x, start.y, 1));
        dist[0][start.y][start.x] = 0;
        dist[1][start.y][start.x] = 0;
        
        while (!q.isEmpty()) {
            
            Point here = q.poll();
            int x = here.x;
            int y = here.y;
            int d = here.d;
            int curDist = dist[d][y][x];

            if (x == end.x && y == end.y) {
                System.out.print(curDist - 1);
                return;
            }

            //가로
            if (d == 1) {
                int nx = x + 1;
                while (nx >= 0 && nx < W && dist[0][y][nx] == -1) {
                    q.offer(new Point(nx, y, 0));
                    dist[0][y][nx] = curDist + 1;
                    nx++;
                }

                nx = x - 1;
                while (nx >= 0 && nx < W && dist[0][y][nx] == -1) {
                    q.offer(new Point(nx, y, 0));
                    dist[0][y][nx] = curDist + 1;
                    nx--;
                }
            }

            //세로
            if (d == 0) {
                int ny = y + 1;
                while (ny >= 0 && ny < H && dist[1][ny][x] == -1) {
                    q.offer(new Point(x, ny, 1));
                    dist[1][ny][x] = curDist + 1;
                    ny++;
                }

                ny = y - 1;
                while (ny >= 0 && ny < H && dist[1][ny][x] == -1) {
                    q.offer(new Point(x, ny, 1));
                    dist[1][ny][x] = curDist + 1;
                    ny--;
                }
            }
        }
    }

    static class Point {
        public int x;
        public int y;
        public int d;

        public Point(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }
}