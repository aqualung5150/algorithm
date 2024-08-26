#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N, M;
vector<vector<int>> board;
vector<pair<int,int>> virus;
int zero = 0;
int dx[] = {1, -1, 0, 0};
int dy[] = {0, 0, 1, -1};
int ans = 0;

void dfs(vector<vector<bool>> &visited, int &count, int x, int y) {
    for (int i = 0; i < 4; ++i) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        if (nx < 0 || nx >= M || ny < 0 || ny >= N || board[ny][nx] || visited[ny][nx])
            continue;
        
        visited[ny][nx] = true;
        --count;
        dfs(visited, count, nx, ny);
    }
}

void wall(int idx, int i) {
    if (idx == 3) {
        vector<vector<bool>> visited(N, vector<bool>(M));
        int count = zero - 3;
        for (int j = 0; j < virus.size(); ++j) {
            visited[virus[j].second][virus[j].first] = true;
            dfs(visited, count, virus[j].first, virus[j].second);
        }
        ans = max(ans, count);
        return;
    }

    for (; i < N * M; ++i) {
        int x = i % M;
        int y = i / M;

        if (board[y][x])
            continue;

        board[y][x] = 1;
        wall(idx + 1, i + 1);
        board[y][x] = 0;
    }
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> N >> M;
    board.resize(N, vector<int>(M));
    for (int i = 0; i < N; ++i)
        for (int j = 0; j < M; ++j) {
            int input;
            cin >> input;
            board[i][j] = input;
            if (input == 0)
                ++zero;
            if (input == 2)
                virus.push_back({j, i});
        }

    wall(0, 0);

    cout << ans;

    return 0;
}