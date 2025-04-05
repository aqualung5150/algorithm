import java.util.*;
import java.io.*;

public class Main {

    private static final int[][][] shapes = {
        {{1, 1, 1, 1}},
        {
            {1, 1},
            {1, 1}
        },
        {
            {1, 0},
            {1, 0},
            {1, 1}
        },
        {
            {1, 0},
            {1, 1},
            {0, 1}
        },
        {
            {1, 1, 1},
            {0, 1, 0}
        },
        {
            {0, 1},
            {0, 1},
            {1, 1}
        },
        {
            
            {0, 1},
            {1, 1},
            {1, 0}
        }
    };

    private static int N, M, answer = 0;
    private static int[][] board;

    public static void main(String[] args) throws IOException {

        init();

        for (int[][] shape : shapes) {

            //rotate
            for (int r = 0; r < 4; ++r) {

                shape = rotate(shape);
                int w = shape[0].length;
                int h = shape.length;

                for (int i = 0; i <= N - h; ++i) {
                    for (int j = 0; j <= M - w; ++j) {

                        int count = 0;
                        for (int y = 0; y < h; ++y) {
                            for (int x = 0; x < w; ++x) {
                                if (shape[y][x] == 1) {
                                    count += board[i + y][j + x];
                                }
                            }
                        }
                        answer = Math.max(answer, count);
                    }
                }
            }
        }

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
            for (int j = 0 ; j < M; ++j) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();
    }

    private static int[][] rotate(int[][] origin) {
        int h = origin.length;
        int w = origin[0].length;

        int[][] result = new int[w][h];
        for (int i = 0; i < h; ++i) {
            for (int j = 0; j < w; ++j) {
                result[j][h - i - 1] = origin[i][j];
            }
        }

        return result;
    }
}