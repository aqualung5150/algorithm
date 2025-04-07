import java.util.*;
import java.io.*;

public class Main {

    private static int[][] board = new int[9][9];
    private static List<Pos> empty = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        init();
        dfs(0);
    }

    private static void init() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 9; ++i) {
            char[] read = br.readLine().toCharArray();
            for (int j = 0; j < 9; ++j) {
                board[i][j] = read[j] - '0';
                if (board[i][j] == 0) {
                    empty.add(new Pos(j, i));
                }
            }
        }

        br.close();
    }

    private static boolean dfs(int idx) {
        if (idx == empty.size()) {
            for (int i = 0; i < 9; ++i) {
                for (int j = 0; j < 9; ++j) {
                    System.out.print(board[i][j]);
                }
                System.out.println();
            }
            return true;
        }

        Pos cur = empty.get(idx);
        int x = cur.x;
        int y = cur.y;
        for (int i = 1; i <= 9; ++i) {
            board[y][x] = i;
            if (validate(x, y)) {
                if (dfs(idx + 1)) {
                    return true;
                }
            }
            board[y][x] = 0;
        }

        return false;
    }

    private static boolean validate(int x, int y) {

        int cur = board[y][x];

        for (int i = 0; i < 9; ++i) {
            if (i != x && board[y][i] == cur) {
                return false;
            }

            if (i != y && board[i][x] == cur) {
                return false;
            }
        }

        int sx = x / 3 * 3;
        int sy = y / 3 * 3;

        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                if (i + sy == y && j + sx == x) {
                    continue;
                }
                
                if (board[i + sy][j + sx] == cur) {
                    return false;
                }
            }
        }

        return true;
    }

    static class Pos {
        public int x;
        public int y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}