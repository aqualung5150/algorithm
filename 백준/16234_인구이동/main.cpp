#include <iostream>
#include <queue>
#include <vector>
#include <cmath>
#include <algorithm>

using namespace std;

int dx[] = {1, -1, 0, 0};
int dy[] = {0, 0, 1, -1};
int N, L, R;
int ans = 0;
vector<vector<int>> board;
vector<vector<bool>> visited;

void bfs(int sx, int sy) {
    queue<pair<int, int>> q;
    vector<pair<int, int>> coord;
    q.push({sx, sy});
    visited[sy][sx] = true;

    int sum = 0;
    int count = 0;

    while (!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();

        coord.push_back({x, y});
        sum += board[y][x];
        ++count;

        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N || visited[ny][nx])
                continue;
            
            int gap = abs(board[y][x] - board[ny][nx]);
            if (L <= gap && gap <= R) {
                visited[ny][nx] = true;
                q.push({nx, ny});
            }
        }
    }

    int a = sum / count;
    for (auto c : coord)
        board[c.second][c.first] = a;
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> N >> L >> R;

    board.resize(N, vector<int>(N));
    visited.resize(N, vector<bool>(N));

    for (int i = 0; i < N; ++i)
        for (int j = 0; j < N; ++j)
            cin >> board[i][j];

    while (1) {
        fill(visited.begin(), visited.end(), vector<bool>(N, false));
        bool move = false;

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                // left
                if (j != N - 1) {
                    int gap = abs(board[i][j] - board[i][j + 1]);
                    if (!visited[i][j] && L <= gap && gap <= R) {
                        move = true;
                        bfs(j, i);
                        continue;
                    }
                }
                // down
                if (i != N - 1) {
                    int gap = abs(board[i][j] - board[i + 1][j]);
                    if (!visited[i][j] && L <= gap && gap <= R) {
                        move = true;
                        bfs(j, i);
                    }
                }
            }
        }

        if (move)
            ++ans;
        else
            break;
    }
    
    cout << ans;

    return 0;
}