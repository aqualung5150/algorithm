import java.util.*;
import java.io.*;

public class Main {

    private static int R, C;
    private static boolean[][] visited;

    private static final int[] dy = {-1, 0, 1};

    public static void main(String[] args) throws IOException {

        //init
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        visited = new boolean[R][C];
        for (int i = 0; i < R; ++i) {
            String read = br.readLine();
            for (int j = 0; j < C; ++j) {
                char c = read.charAt(j);
                if (c == 'x') {
                    visited[i][j] = true;
                }
            }
        }

        int answer = 0;
        for (int y = 0; y < R; ++y) {
            if (dfs(0, y)) {
                answer++;
            }
        }

        System.out.print(answer);
    }

    public static boolean dfs(int x, int y) {
        if (x == C - 1) {
            return true;
        }

        for (int d = 0; d < 3; ++d) {
            int nx = x + 1;
            int ny = y + dy[d];

            if (ny < 0 || ny >= R || visited[ny][nx]) {
                continue;
            }

            visited[ny][nx] = true;
            if (dfs(nx, ny)) {
                return true;
            }

            //이미 실패한 경로이기 때문에 visited=true로 내버려둔다.
            // visited[ny][nx] = false;
        }

        return false;
    }
}