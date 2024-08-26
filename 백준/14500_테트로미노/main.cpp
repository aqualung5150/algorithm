#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N, M;
vector<vector<int>> board;
vector<vector<bool>> visited;
int ans = 0;
int dx[] = {1, -1, 0, 0};
int dy[] = {0, 0, 1, -1};

void dfs(int idx, int x, int y, int sum) {
    if (idx == 3) {
        ans = max(ans, sum);
        return;
    }

    for (int i = 0; i < 4; ++i) {
        // one step
        int nx = x + dx[i];
        int ny = y + dy[i];

        if (nx < 0 || nx >= M || ny < 0 || ny >= N || visited[ny][nx])
            continue;

        visited[ny][nx] = true;
        dfs(idx + 1, nx, ny, sum + board[ny][nx]);
        visited[ny][nx] = false;

        // two step
        int nnx = nx + dx[i];
        int nny = ny + dy[i];

        if (idx == 2 || nnx < 0 || nnx >= M || nny < 0 || nny >= N || visited[nny][nnx])
            continue;

        visited[nny][nnx] = true;
        dfs(idx + 2, nx, ny, sum + board[ny][nx] + board[nny][nnx]);
        visited[nny][nnx] = false;
    }
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> N >> M;

    board.resize(N, vector<int>(M));
    visited.resize(N, vector<bool>(M));

    for (int i = 0; i < N; ++i)
        for (int j = 0; j < M; ++j)
            cin >> board[i][j];

    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < M; ++j) {
            visited[i][j] = true;
            dfs(0, j, i, board[i][j]);
            visited[i][j] = false;
        }
    }

    cout << ans;

    return 0;
}