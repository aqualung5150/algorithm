import java.util.*;
import java.io.*;

public class Main {

    private static final int[] dx = {1, -1, 0, 0};
    private static final int[] dy = {0, 0, -1, 1};

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;
    private static StringBuilder sb = new StringBuilder();

    private static int N, M, x, y, K;
    private static int[][] board;
    private static int[][] dice = {
        {0, 0, 0, 0},
        {0, 0, 0, 0}
    }; // [0][]: horizontal [1][]: vertical

    public static void main(String[] args) throws IOException {

        init();

        st = new StringTokenizer(br.readLine());
        while (K-- > 0) {
            roll(Integer.parseInt(st.nextToken()));
        }

        System.out.print(sb);

        br.close();
    }

    private static void init() throws IOException {

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M ; ++j) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void roll(int d) {

        int nx = x + dx[d - 1];
        int ny = y + dy[d - 1];

        if (nx < 0 || nx >= M || ny < 0 || ny >= N) {
            return;
        }

        x = nx;
        y = ny;

        switch (d) {
            case 1:
                right();
                break;
            case 2:
                left();
                break;
            case 3:
                up();
                break;
            case 4:
                down();
                break;
        }

        swap();
        sb.append(dice[0][1]).append("\n");
    }

    private static void left() {

        int[] copy = new int[4];
        for (int i = 0; i < 4; ++i) {
            copy[(i + 3) % 4] = dice[0][i];
        }
        dice[0] = copy;
        dice[1][1] = copy[1];
        dice[1][3] = copy[3];
    }

    private static void right() {

        int[] copy = new int[4];
        for (int i = 0; i < 4; ++i) {
            copy[(i + 1) % 4] = dice[0][i];
        }
        dice[0] = copy;
        dice[1][1] = copy[1];
        dice[1][3] = copy[3];
    }

    private static void up() {

        int[] copy = new int[4];
        for (int i = 0; i < 4; ++i) {
            copy[(i + 3) % 4] = dice[1][i];
        }

        dice[1] = copy;
        dice[0][1] = copy[1];
        dice[0][3] = copy[3];
    }

    private static void down() {

        int[] copy = new int[4];
        for (int i = 0; i < 4; ++i) {
            copy[(i + 1) % 4] = dice[1][i];
        }

        dice[1] = copy;
        dice[0][1] = copy[1];
        dice[0][3] = copy[3];
    }

    private static void swap() {

        if (board[y][x] == 0) {
            board[y][x] = dice[0][3];
        } else {
            dice[0][3] = board[y][x];
            dice[1][3] = board[y][x];
            board[y][x] = 0;
        }
    }
}