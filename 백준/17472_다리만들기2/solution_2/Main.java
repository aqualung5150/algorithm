import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int M;
    private static int[][] board;
    private static List<Point> lands = new ArrayList<>();
    private static int totalNumberOfLand;
    private static final int dx[] = {1, 0, -1, 0};
    private static final int dy[] = {0, 1, 0, -1};
    private static List<Edge> edges = new ArrayList<>();
    private static int[] parent;

    public static void main(String[] args) throws IOException {

        //init
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                int input = Integer.parseInt(st.nextToken());
                board[i][j] = input;
                if (input == 1) {
                    lands.add(new Point(j, i));
                }
            } 
        }

        // 땅 번호 매기기
        applyNumberToLand();

        makeEdges();

        System.out.print(kruskal());
    }

    private static void applyNumberToLand() {

        boolean[][] visited = new boolean[N][M];

        int number = 1;

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (board[i][j] == 0 || visited[i][j]) {
                    continue;
                }

                Queue<Point> q = new LinkedList<>();
                q.offer(new Point(j, i));
                visited[i][j] = true;
                board[i][j] = number;

                while (!q.isEmpty()) {
                    Point here = q.poll();
                    
                    for (int d = 0; d < 4; ++d) {
                        int nx = here.x + dx[d];
                        int ny = here.y + dy[d];

                        if (nx < 0 || nx >= M || ny < 0 || ny >= N || board[ny][nx] == 0 || visited[ny][nx])
                            continue;

                        q.offer(new Point(nx, ny));
                        visited[ny][nx] = true;

                        board[ny][nx] = number;
                    }
                }

                ++number;
            }
        }

        totalNumberOfLand = number - 1;

        parent = new int[totalNumberOfLand + 1];
        for (int i = 1; i <= totalNumberOfLand; ++i) {
            parent[i] = i;
        }
    }

    private static void makeEdges() {
        for (int i = 0; i < lands.size(); ++i) {
            int x = lands.get(i).x;
            int y = lands.get(i).y;
            int here = board[y][x];

            for (int d = 0; d < 4; ++d) {
                //다리를 놓을 수 없는 좌표
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx < 0 || nx >= M || ny < 0 || ny >= N || board[ny][nx] != 0)
                    continue;

                int count = 0;

                while (true) {
                    if (nx < 0 || nx >= M || ny < 0 || ny >= N) {
                        break;
                    }

                    if (board[ny][nx] != 0) {
                        if (count > 1) {
                            int there = board[ny][nx];
                            edges.add(new Edge(here, there, count));
                        }
                        break;
                    }

                    nx += dx[d];
                    ny += dy[d];

                    ++count;
                }
            }
        }
    }

    private static void unionRoot(int x, int y) {
        x = findRoot(x);
        y = findRoot(y);

        if (x != y) {
            parent[y] = x;
        }
    }

    private static int findRoot(int x) {
        if (parent[x] == x)
            return x;
        
        return parent[x] = findRoot(parent[x]);
    }

    private static int kruskal() {
        Collections.sort(edges);

        int count = 0;
        int result = 0;

        for (Edge e : edges) {
            int from = e.from;
            int to = e.to;
            int weight = e.weight;

            if (findRoot(from) != findRoot(to)) {
                ++count;
                result += weight;
                unionRoot(from, to);
            }

            if (count == totalNumberOfLand - 1)
                break;
        }

        if (count != totalNumberOfLand - 1)
            return -1;
        return result;
    }

    static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Edge implements Comparable<Edge> {
        public int from;
        public int to;
        public int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge e) {
            if (this.weight < e.weight)
                return -1;
            else if (this.weight == e.weight)
                return 0;
            else
                return 1;
        }
    }
}