#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int dx[] = {1, -1, 0, 0};
int dy[] = {0, 0, 1, -1};
int N, M, gas;
int sx, sy;
vector<vector<int>> board;
vector<pair<int, int>> destination;
int remain = 0;


bool bfs() {
    vector<vector<int>> dist(N, vector<int>(N, -1));
    queue<pair<int, int>> q;
    q.push({sx, sy});
    dist[sy][sx] = 0;

    // 택시 -> 승객
    int targetDist = N * N;
    pair<int, int> target = {N, N};
    while (!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();

        if (dist[y][x] > targetDist)
            break;
        // 가장 가까운 승객 업데이트
        if (board[y][x] >= 2) {
            targetDist = dist[y][x];
            if (y < target.second)
                target = {x, y};
            else if (y == target.second && x < target.first)
                target = {x, y};
        }

        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N || board[ny][nx] == 1 || dist[ny][nx] != -1)
                continue;

            dist[ny][nx] = dist[y][x] + 1;
            q.push({nx, ny});
        }
    }
    // 승객이 태울 수 없는 위치에 있을 때
    if (target.first == N)
        return false;
    // 승객을 태우러 가는 도중 연료가 바닥남
    if (targetDist >= gas)
        return false;
    gas -= targetDist;

    // 승객 -> 목적지
    int tx = destination[board[target.second][target.first]].first;
    int ty = destination[board[target.second][target.first]].second;
    board[target.second][target.first] = 0;
    fill(dist.begin(), dist.end(), vector<int>(N, -1));
    while (!q.empty())
        q.pop();
    q.push({target.first, target.second});
    dist[target.second][target.first] = 0;

    targetDist = N * N;
    while (!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();

        if (x == tx && y == ty) {
            targetDist = dist[y][x];
            sx = x;
            sy = y;
            break;
        }

        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N || board[ny][nx] == 1 || dist[ny][nx] != -1)
                continue;

            dist[ny][nx] = dist[y][x] + 1;
            q.push({nx, ny});
        }
    }
    // 도달할 수 없는 목적지
    if (targetDist == N * N)
        return false;
    if (targetDist > gas)
        return false;
    
    gas += targetDist;
    --remain;
    return true;
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> N >> M >> gas;
    board.resize(N, vector<int>(N));
    destination.resize(N * N + 3);
    remain = M;

    for (int i = 0; i < N; ++i)
        for (int j = 0; j < N; ++j)
            cin >> board[i][j];

    cin >> sy >> sx;
    --sx; --sy;

    int idx = 2;
    while (M--) {
        int x1, y1, x2, y2;
        cin >> y1 >> x1 >> y2 >> x2;
        board[y1 - 1][x1 - 1] = idx;
        destination[idx] = {x2 - 1, y2 - 1};

        ++idx;
    }

    while (remain) {
        if (!bfs()) {
            cout << -1;
            return 0;
        }
    }

    cout << gas;

    return 0;
}