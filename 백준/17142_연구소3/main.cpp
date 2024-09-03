#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

#define MAX_DIST 1000000000

using namespace std;

int N, M;
vector<vector<int>> board;
vector<pair<int, int>> virus;
vector<pair<int, int>> active;
int emptyCnt = 0;
int dx[] = {1, -1, 0, 0};
int dy[] = {0, 0, 1, -1};
int ans = MAX_DIST;

void bfs() {
    int emptyCpy = emptyCnt;
    vector<vector<int>> dist(N, vector<int>(N, -1));
    queue<pair<int, int>> q;
    for (auto a : active) {
        q.push(a);
        dist[a.second][a.first] = 0;
    }

    while (!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();

        if (board[y][x] == 0)
            --emptyCpy;
        if (emptyCpy == 0) {
            ans = min(ans, dist[y][x]);
            break;
        }

        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= N || dist[ny][nx] != -1 || board[ny][nx] == 1)
                continue;
            
            q.push({nx, ny});
            dist[ny][nx] = dist[y][x] + 1;

        }
    }
}

void dfs(int prev) {
    if (active.size() == M) {
        bfs();
    } else {
        for (int i = prev + 1; i < virus.size(); ++i) {
            active.push_back(virus[i]);
            dfs(i);
            active.pop_back();
        }
    }
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> N >> M;
    board.resize(N, vector<int>(N));

    for (int i = 0; i < N; ++i)
        for (int j = 0; j < N; ++j) {
            cin >> board[i][j];
            if (board[i][j] == 0)
                ++emptyCnt;
            else if (board[i][j] == 2)
                virus.push_back({j, i});
        }

    dfs(-1);
    
    if (ans == MAX_DIST)
        cout << -1;
    else
        cout << ans;

    return 0;
}