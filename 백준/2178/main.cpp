#include <iostream>
#include <queue>
#include <vector>
#include <string>

using namespace std;

typedef pair<int, int> coord;

int dx[] = {1, -1, 0, 0};
int dy[] = {0, 0, 1, -1};
int N, M;
vector<string> board(100);
vector<vector<int>> dist(100, vector<int>(100));

void bfs() {
    queue<coord> q;
    dist[0][0] = 1;
    q.push({0, 0});

    while (!q.empty()) {
        coord here = q.front();
        q.pop();

        int nextDist = dist[here.second][here.first] + 1;
        for (int i = 0; i < 4; ++i) {
            int nx = here.first + dx[i];
            int ny = here.second + dy[i];

            if (nx < 0 || nx >= M || ny < 0 || ny >= N || board[ny][nx] == '0' || dist[ny][nx])
                continue;
            
            dist[ny][nx] = nextDist;
            q.push({nx, ny});
        }
    }
}

int main() {
    cin >> N >> M;

    for (int i = 0; i < N; ++i)
        cin >> board[i];

    bfs();

    cout << dist[N - 1][M - 1];

    return 0;
}