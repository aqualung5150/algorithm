import java.util.*;
import java.io.*;

public class Main {

    private static int dx[] = {1, 0, -1, 0};
    private static int dy[] = {0, 1, 0, -1};

    private static int N, M, answer = 64;
    private static int[][] board;
    private static List<CCTV> cctvs = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        init();

        dfs(0);

        System.out.print(answer);
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] > 0 && board[i][j] <= 5) {
                    cctvs.add(new CCTV(j, i, board[i][j]));
                }
            }
        }

        br.close();
    }

    private static void dfs(int idx) {

        if (idx == cctvs.size()) {
            answer = Math.min(answer, countBlind());
            return;
        }

        int[][] origin = copyBoard(board);

        for (int d = 0; d < 4; ++d) {
            go(cctvs.get(idx), d);
            dfs(idx + 1);
            board = copyBoard(origin);
        }
    }

    private static int countBlind() {
        int count = 0;

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (board[i][j] == 0) {
                    ++count;
                }
            }
        }

        return count;
    }

    private static int[][] copyBoard(int[][] origin) {
        int[][] result = new int[N][M];

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
               result[i][j] = origin[i][j];
            }
        }

        return result;
    }

    private static void go(CCTV cctv, int d) {
        int x = cctv.x;
        int y = cctv.y;
        int type = cctv.type;

        List<Integer> directions = new ArrayList<>();
        directions.add(d);
        switch (type) {
            case 2:
                directions.add((d + 2) % 4);
                break;
            case 3:
                directions.add((d + 1) % 4);
                break;
            case 4:
                directions.add((d + 1) % 4);
                directions.add((d + 2) % 4);
                break;
            case 5:
                directions.add((d + 1) % 4);
                directions.add((d + 2) % 4);
                directions.add((d + 3) % 4);
                break;
        }

        for (Integer direction : directions) {
            int nx = x + dx[direction];
            int ny = y + dy[direction];

            while (nx >= 0 && nx < M && ny >= 0 && ny < N && board[ny][nx] != 6) {
                if (board[ny][nx] == 0) {
                    board[ny][nx] = 7;
                }

                nx = nx + dx[direction];
                ny = ny + dy[direction];
            }
        }
    }

    static class CCTV {
        public int x;
        public int y;
        public int type;

        public CCTV(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
}