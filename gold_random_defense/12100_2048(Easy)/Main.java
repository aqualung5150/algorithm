import java.util.*;
import java.io.*;

public class Main {

    private static int N, answer = 0;
    private static int[][] board;
    private static Map<String, Boolean> visited = new HashMap<>();

    public static void main(String[] args) throws IOException {

        init();

        move(0);

        System.out.print(answer);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }

    private static void move(int idx) {
        
        if (idx == 5) {
            return;
        }

        int[][] origin = getCopy(board);

        moveLeft();
        move(idx + 1);
        board = getCopy(origin);

        moveRight();
        move(idx + 1);
        board = getCopy(origin);

        moveDown();
        move(idx + 1);
        board = getCopy(origin);

        moveUp();
        move(idx + 1);
        board = getCopy(origin);
    }

    private static int[][] getCopy(int[][] from) {

        int[][] copy = new int[N][N];
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                copy[i][j] = from[i][j];
            }
        }

        return copy;
    }

    private static void moveRight() {

        boolean[][] isCombined = new boolean[N][N];

        for (int x = N - 1; x >= 0; --x) {
            for (int y = 0; y < N; ++y) {

                int value = board[y][x];
                board[y][x] = 0;
                int nx = x;
                while (true) {

                    ++nx;

                    if (nx >= N || board[y][nx] != 0) {

                        if (nx < N && !isCombined[y][nx] && value == board[y][nx]) {
                            board[y][nx] *= 2;
                            isCombined[y][nx] = true;
                        } else {
                            --nx;
                            board[y][nx] = value;
                        }
                        answer = Math.max(answer, board[y][nx]);
                        break;
                    }
                }
            }
        }
    }

    private static void moveLeft() {

        boolean[][] isCombined = new boolean[N][N];

        for (int x = 0; x < N; ++x) {
            for (int y = 0; y < N; ++y) {

                int value = board[y][x];
                board[y][x] = 0;
                int nx = x;
                while (true) {

                    --nx;

                    if (nx < 0 || board[y][nx] != 0) {

                        if (nx >= 0 && !isCombined[y][nx] && value == board[y][nx]) {
                            board[y][nx] *= 2;
                            isCombined[y][nx] = true;
                        } else {
                            ++nx;
                            board[y][nx] = value;
                        }
                        answer = Math.max(answer, board[y][nx]);
                        break;
                    }
                }
            }
        }
    }

    private static void moveDown() {

        boolean[][] isCombined = new boolean[N][N];

        for (int y = N - 1; y >= 0; --y) {
            for (int x = 0; x < N; ++x) {

                int value = board[y][x];
                board[y][x] = 0;
                int ny = y;
                while (true) {

                    ++ny;

                    if (ny >= N || board[ny][x] != 0) {

                        if (ny < N && !isCombined[ny][x] && value == board[ny][x]) {
                            board[ny][x] *= 2;
                            isCombined[ny][x] = true;
                        } else {
                            --ny;
                            board[ny][x] = value;
                        }
                        answer = Math.max(answer, board[ny][x]);
                        break;
                    }
                }
            }
        }
    }

    private static void moveUp() {

        boolean[][] isCombined = new boolean[N][N];

        for (int y = 0; y < N; ++y) {
            for (int x = 0; x < N; ++x) {

                int value = board[y][x];
                board[y][x] = 0;
                int ny = y;
                while (true) {

                    --ny;

                    if (ny < 0 || board[ny][x] != 0) {

                        if (ny >= 0 && !isCombined[ny][x] && value == board[ny][x]) {
                            board[ny][x] *= 2;
                            isCombined[ny][x] = true;
                        } else {
                            ++ny;
                            board[ny][x] = value;
                        }
                        answer = Math.max(answer, board[ny][x]);
                        break;
                    }
                }
            }
        }
    }
}