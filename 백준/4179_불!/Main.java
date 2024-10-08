import java.util.*;
import java.io.*;

public class Main {

    private static int R;
    private static int C;
    private static Point start;
    private static List<Point> fires = new ArrayList<>();
    private static int[][] dist;
    private static final int dx[] = {1, -1, 0, 0};
    private static final int dy[] = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        dist = new int[R][C];
        for (int i = 0; i < R; ++i) {
            Arrays.fill(dist[i], -1);
        }

        for (int i = 0; i < R; ++i) {
            String str = br.readLine();
            for (int j = 0; j < C; ++j) {
                char c = str.charAt(j);


                if (c == '.') {
                    continue;
                } else if (c == 'F') {
                    fires.add(new Point(j, i));
                } else if (c == 'J') {
                    start = new Point(j, i);
                }

                dist[i][j] = 0;
            }
        }

        int ans = bfs();
        if (ans != -1)
            System.out.print(ans);
        else
            System.out.print("IMPOSSIBLE");
    }

    static boolean isEdge(int x, int y) {
        return (x == 0 || x == C - 1 || y == 0 || y == R - 1);
    }

    static void spread() {
        List<Point> newFires = new ArrayList<>();

        for (Point f : fires) {
            for (int i = 0; i < 4; ++i) {
                int nx = f.x + dx[i];
                int ny = f.y + dy[i];

                if (nx < 0 || nx >= C || ny < 0 || ny >= R || dist[ny][nx] != -1)
                    continue;
                
                dist[ny][nx] = 0;
                newFires.add(new Point(nx, ny));
            }
        }

        fires = newFires;
    }


    static int bfs() {
        Queue<Point> q = new LinkedList<>();
        q.offer(start);

        int depth = 0;
        while (!q.isEmpty()) {
            Point here = q.poll();
            int x = here.x;
            int y = here.y;
            int curDist = dist[y][x];

            if (curDist == depth) {
                ++depth;
                spread();
            }

            if (isEdge(x, y)) {
                return depth;
            }

            for (int i = 0; i < 4; ++i) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= C || ny < 0 || ny >= R || dist[ny][nx] != -1)
                    continue;
                
                q.offer(new Point(nx, ny));
                dist[ny][nx] = curDist + 1;
            }
        }

        return -1;
    }

    static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}