#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int N;
vector<vector<int>> board;
int sx, sy;
int shark = 2;
int eat = 0;
int ans = 0;
int dx[] = {1, -1, 0, 0};
int dy[] = {0, 0, 1, -1};

bool bfs(int start_x, int start_y) {
    vector<vector<int>> dist(N, vector<int>(N, -1));
    queue<pair<int, int>> q;
    q.push({start_x, start_y});
    dist[start_y][start_x] = 0;

    int minDist = 500;
    int fx = N;
    int fy = N;
    int valid = false;

    while (!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();

        if (minDist <= dist[y][x])
            continue;

        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N || dist[ny][nx] != -1 || board[ny][nx] > shark)
                continue;

            dist[ny][nx] = dist[y][x] + 1;
            q.push({nx, ny});

            if (board[ny][nx] && board[ny][nx] < shark) {
                valid = true;
                
                minDist = dist[ny][nx];
                if (fy == ny && fx > nx) {
                    fx = nx;
                    fy = ny;
                } else if (fy > ny) {
                    fx = nx;
                    fy = ny;
                }
            }
        }
    }

    if (valid) {
        ans += minDist;
        sx = fx;
        sy = fy;
        board[fy][fx] = 0;
    }

    return valid;
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> N;
    board.resize(N, vector<int>(N));

    for (int i = 0; i < N; ++i)
        for (int j = 0; j < N; ++j) {
            cin >> board[i][j];
            if (board[i][j] == 9) {
                board[i][j] = 0;
                sx = j;
                sy = i;
            }
        }

    while (bfs(sx, sy)) {
        ++eat;
        if (eat == shark) {
            eat = 0;
            ++shark;
        }
    }

    cout << ans;   

    return 0;
}