#include <iostream>
#include <vector>

using namespace std;

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    int N, M, x, y, d;
    cin >> N >> M >> y >> x >> d;

    vector<vector<int>> board(N, vector<int>(M));

    for (int i = 0; i < N; ++i)
        for (int j = 0; j < M; ++j)
            cin >> board[i][j];
    
    int dx[] = {0, 1, 0, -1};
    int dy[] = {-1, 0, 1, 0};

    int ans = 1;
    board[y][x] = 2;
    while (1) {
        bool flag = false; // 주변에 빈칸이 있음
        for (int i = 3; i >= 0; --i) {
            int nx = x + dx[(d + i) % 4];
            int ny = y + dy[(d + i) % 4];

            if (nx < 0 || nx >= M || ny < 0 || ny >= N || board[ny][nx])
                continue;

            flag = true;

            ++ans;
            x = nx;
            y = ny;
            d = (d + i) % 4;

            board[y][x] = 2;
            break;
        }

        if (!flag) {
            int nx = x - dx[d];
            int ny = y - dy[d];

            if (nx < 0 || nx >= M || ny < 0 || ny >= N || board[ny][nx] == 1)
                break;
            
            x = nx;
            y = ny;
        }
    }

    cout << ans;

    return 0;
}

/*
1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우,
    바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
    바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
    반시계 방향으로 90도 회전한다.
    바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
    1번으로 돌아간다.
*/