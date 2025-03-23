import java.util.*;
import java.io.*;

public class Main {

    private static final int[] dx = {1, 0, -1, 0};
    private static final int[] dy = {0, -1, 0, 1};

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static int N;
    private static boolean[][] board = new boolean[101][101];
    private static int startX;
    private static int startY;
    private static int endX;
    private static int endY;

    public static void main(String[] args) throws IOException {

        N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            dragonCurve();
        }

        System.out.print(countSquare());
    }

    private static void dragonCurve() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        startX = Integer.parseInt(st.nextToken());
        startY = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int g = Integer.parseInt(st.nextToken());
        boolean[][] origin = new boolean[101][101];

        endX = startX + dx[d];
        endY = startY + dy[d];

        origin[startY][startX] = true;
        origin[endY][endX] = true;

        while (g-- > 0) {
            nextGen(origin);
        }
        appendBoard(origin);
    }

    private static void nextGen(boolean[][] origin) {
        boolean[][] rotation = new boolean[101][101];

        int copyStartX = 0, copyStartY = 0;
        int copyEndX = 0, copyEndY = 0;
        for (int i = 0; i <= 100; ++i) {
            for (int j = 0; j <= 100; ++j) {
                rotation[j][100 - i] = origin[i][j];

                if (j == startX && i == startY) {
                    copyStartX = 100 - i;
                    copyStartY = j;
                }

                if (j == endX && i == endY) {
                    copyEndX = 100 - i;
                    copyEndY = j;
                }
            }
        }

        int diffX = endX - copyEndX;
        int diffY = endY - copyEndY;

        for (int i = 0; i <= 100; ++i) {
            for (int j = 0; j <= 100; ++j) {
                if (rotation[i][j]) {
                    origin[i + diffY][j + diffX] = true;
                }
            }
        }

        endX = copyStartX + diffX;
        endY = copyStartY + diffY;
    }

    private static void appendBoard(boolean[][] origin) {
        for (int i = 0; i <= 100; ++i) {
            for (int j = 0; j <= 100; ++j) {
                if (origin[i][j]) {
                    board[i][j] = true;
                }
            }
        }

    }

    private static int countSquare() {
        int count = 0;

        for (int i = 0; i < 100; ++i) {
            for (int j = 0; j < 100; ++j) {
                if (!board[i][j]) {
                    continue;
                }

                if (board[i][j + 1] && board[i + 1][j] && board[i + 1][j + 1]) {
                    ++count;
                }
            }
        }

        return count;
    }
}