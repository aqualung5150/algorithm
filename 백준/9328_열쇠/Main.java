import java.util.*;
import java.io.*;

public class Main {

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, 1, -1};


    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    private static int T, N, M;
    private static char[][] board;
    private static List<Point> start;
    private static List<Point>[] waitList = new List[26];
    private static List<Integer> keys; // 'a' = 0;

    public static void main(String[] args) throws IOException {

        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            init();
            bfs();
        }
    }

    private static void init() throws IOException {

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        start = new ArrayList<>();
        for (int i = 0; i < 26; ++i) {
            waitList[i] = new ArrayList<>();
        }
        keys = new ArrayList<>();

        for (int i = 0; i < N; ++i) {
            String read = br.readLine();
            for (int j = 0; j < M; ++j) {
                char c = read.charAt(j);
                if ((i == 0 || j == 0 || i == N - 1 || j == M - 1) && c != '*') {
                    start.add(new Point(j, i));
                }
                board[i][j] = c;
            }
        }

        String read = br.readLine();
        if (!read.equals("0")) {
            for (char c : read.toCharArray()) {
                keys.add(c - 'a');
            }
        }
    }

    private static void bfs() {

        int result = 0;

        boolean[][] visited = new boolean[N][M];
        Queue<Point> q = new LinkedList<>();
        
        //시작점
        for (Point p : start) {
            char c = board[p.y][p.x];

            if (Character.isUpperCase(c)) {
                waitList[c - 'A'].add(p);
            } else {
                q.offer(p);
                visited[p.y][p.x] = true;
            }
        }

        for (int k : keys) {
            for (Point p : waitList[k]) {
                q.offer(p);
            }
            waitList[k] = new ArrayList<>();
        }

        //bfs
        while (!q.isEmpty()) {
            Point here = q.poll();
            char cur = board[here.y][here.x];

            if (cur == '$') {
                ++result;
            }

            //키 획득 시 막혔던 경로 진행
            if (Character.isLowerCase(cur)) {
                keys.add(cur - 'a');
                for (Point p : waitList[cur - 'a']) {
                    q.offer(p);
                    visited[p.y][p.x] = true;
                }
                waitList[cur - 'a'] = new ArrayList<>();
            }

            for (int i = 0; i < 4; ++i) {
                int nx = here.x + dx[i];
                int ny = here.y + dy[i];
                

                if (nx < 0 || nx >= M || ny < 0 || ny >= N || visited[ny][nx] || board[ny][nx] == '*') {
                    continue;
                }

                char nextChar = board[ny][nx];

                if (Character.isUpperCase(nextChar) && !keys.contains(nextChar - 'A')) {
                    waitList[nextChar - 'A'].add(new Point(nx, ny));
                    continue;
                }

                q.offer(new Point(nx, ny));
                visited[ny][nx] = true;
            }
        }

        System.out.println(result);
    }

    static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}