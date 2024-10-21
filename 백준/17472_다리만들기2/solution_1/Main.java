/**
1. 각 땅에 번호 매기기
2. 땅에서 사방으로 다리를 놓는다. (백트래킹)
    * 놓으려는 좌표가 1.땅이 아니고 2.같은 방향의 다리가 없다면 진행
    * 다리를 끝까지 놓아 다른 땅에 닿는다면 go (양쪽 땅의 번호를 그래프로 연결)
    * 아니면 back (그래프에서 edge 제거)
3. 다리의 개수가 땅 - 1개면 모든 땅이 연결되는지 그래프탐색 -> true: answer업데이트 / false: back
4. if (ans = MAX) 0, else print(ans)
*/

import java.util.*;
import java.io.*;

public class Main {

    private static int N;
    private static int M;
    private static int[][] board;
    private static int[][] bridgeInfo; // 이미 다리가 놓여진 좌표인지 체크 (가로 1, 세로 2, 둘다 3)
    private static List<Point> lands = new ArrayList<>();
    private static int totalNumberOfLand;
    private static ArrayList<Integer>[] edgeOfLand;
    private static int answer = 101;
    private static final int dx[] = {1, 0, -1, 0};
    private static final int dy[] = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        //init
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        bridgeInfo = new int[N][M];
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

        dfs(0, 0);

        if (answer == 101)
            System.out.print(-1);
        else
            System.out.print(answer);
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
        edgeOfLand = new ArrayList[totalNumberOfLand + 1];
        for (int i = 0; i <= totalNumberOfLand; ++i) {
            edgeOfLand[i] = new ArrayList<Integer>();
        }
    }

    private static void dfs(int idx, int sum) {

        if (idx == totalNumberOfLand - 1) {

            if (isLandConnected()) {
                answer = Math.min(answer, sum);
            }
            return;
        }

        for (int i = 0; i < lands.size(); ++i) {
            int x = lands.get(i).x;
            int y = lands.get(i).y;

            for (int d = 0; d < 4; ++d) {

                int bridgeDirection = d % 2 == 0 ? 1 : 2;

                //이미 같은 방향 다리가 놓여져 있는 땅
                if (bridgeInfo[y][x] == 3 || bridgeInfo[y][x] == bridgeDirection)
                    continue;

                //다리를 놓을 수 없는 좌표
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx < 0 || nx >= M || ny < 0 || ny >= N || board[ny][nx] != 0)
                    continue;

                //다리 start, end좌표 찾기
                int startX = x;
                int startY = y;
                int endX = -1;
                int endY = -1;
                int count = 0;

                while (true) {
                    if (nx < 0 || nx >= M || ny < 0 || ny >= N) {
                        break;
                    }

                    if (board[ny][nx] != 0) {
                        endX = nx;
                        endY = ny;
                        break;
                    }

                    nx += dx[d];
                    ny += dy[d];

                    ++count;
                }

                // 다리가 다른 땅에 닿지 않음
                if ((endX == -1 && endY == -1) || count < 2)
                    continue;

                int startLand = board[startY][startX];
                int endLand = board[endY][endX];

                bridgeInfo[startY][startX] += bridgeDirection;
                bridgeInfo[endY][endX] += bridgeDirection;
                edgeOfLand[startLand].add(endLand);
                edgeOfLand[endLand].add(startLand);
                dfs(idx + 1, sum + count);
                bridgeInfo[startY][startX] -= bridgeDirection;
                bridgeInfo[endY][endX] -= bridgeDirection;
                edgeOfLand[startLand].remove(edgeOfLand[startLand].size() - 1);
                edgeOfLand[endLand].remove(edgeOfLand[endLand].size() - 1);
            }
        }
    }

    private static boolean isLandConnected() {

        int start = 0;
        for (int i = 1; i < edgeOfLand.length; ++i) {
            boolean done = false;
            for (int j = 0; j < edgeOfLand[i].size(); ++j) {
                start = edgeOfLand[i].get(j);
                done = true;
                break;
            }
            if (done)
                break;
        }

        int count = 0;

        boolean[] visited = new boolean[totalNumberOfLand + 1];

        Queue<Integer> q = new LinkedList<>();
        q.offer(start);
        visited[start] = true;

        while (!q.isEmpty()) {
            int here = q.poll();

            ++count;

            for (int i = 0; i < edgeOfLand[here].size(); ++i) {
                int there = edgeOfLand[here].get(i);

                if (visited[there])
                    continue;

                q.offer(there);
                visited[there] = true;
            }
        }

        if (count == totalNumberOfLand)
            return true;
        return false;
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