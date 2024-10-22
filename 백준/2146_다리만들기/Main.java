import java.util.*;
import java.io.*;

public class Main {

    private static final int dx[] = {1, -1, 0, 0};
    private static final int dy[] = {0, 0, 1, -1};

    private static int N;
    private static int[][] board;
    private static ArrayList<ArrayList<Point>> lands = new ArrayList<ArrayList<Point>>();
    private static int answer = 10000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        applyNumberToLand();

        bfs();

        System.out.print(answer);
    }

    private static void applyNumberToLand() {

        boolean[][] visited = new boolean[N][N];

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (board[i][j] == 0 || visited[i][j])
                    continue;
                
                ArrayList<Point> newLand = new ArrayList<>();

                Queue<Point> q = new LinkedList<>();
                q.offer(new Point(j, i));
                visited[i][j] = true;

                while (!q.isEmpty()) {
                    Point here = q.poll();

                    newLand.add(here);

                    for (int d = 0; d < 4; ++d) {
                        int nx = here.x + dx[d];
                        int ny = here.y + dy[d];

                        if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[ny][nx] || board[ny][nx] != 1)
                            continue;

                        q.offer(new Point(nx, ny));
                        visited[ny][nx] = true;   
                    }
                }
                
                lands.add(newLand);
            }
        }
    }

    private static void bfs() {
        for (ArrayList<Point> curLand : lands) {
            //visited
            int[][] distance = new int[N][N];
            for (int i = 0; i < N; ++i) {
                Arrays.fill(distance[i], -1);
            }
            //init Queue
            Queue<Point> q = new LinkedList<>();
            for (Point p : curLand) {
                q.offer(p);
                distance[p.y][p.x] = 0;
            }
            //bfs
            while (!q.isEmpty()) {
                Point here = q.poll();
                int curDistance = distance[here.y][here.x];

                for (int i = 0; i < 4; ++i) {
                    int nx = here.x + dx[i];
                    int ny = here.y + dy[i];

                    if (nx < 0 || nx >= N || ny < 0 || ny >= N || distance[ny][nx] != -1)
                        continue;

                    if (board[ny][nx] != 0) {
                        answer = Math.min(answer, curDistance);
                        break;
                    }

                    q.offer(new Point(nx, ny));
                    distance[ny][nx] = curDistance + 1;
                }
            }
        }
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