#include <iostream>
#include <vector>
#include <queue>

using namespace std;

struct Point {
    int x;
    int y;
};

int R, C;
Point start;
Point dest;
vector<vector<int>> dist;
queue<Point> flood;
int dx[] = {1, -1, 0, 0};
int dy[] = {0, 0, 1, -1};

void spreadFlood() {
    int size = flood.size();

    for (int i = 0; i < size; ++i) {
        int x = flood.front().x;
        int y = flood.front().y;
        flood.pop();

        for (int j = 0; j < 4; ++j) {
            int nx = x + dx[j];
            int ny = y + dy[j];

            if (nx < 0 || nx >= C || ny < 0 || ny >= R || dist[ny][nx] != -1 || (dest.x == nx && dest.y == ny))
                continue;
            
            flood.push({nx, ny});
            dist[ny][nx] = 0;
        }
    }
}

void bfs() {
    int depth = 0;
    spreadFlood();
    queue<Point> q;
    q.push(start);

    while (!q.empty()) {
        int x = q.front().x;
        int y = q.front().y;
        int curDist = dist[y][x];
        q.pop();

        if (x == dest.x && y == dest.y)
            break;

        if (curDist > depth) {
            spreadFlood();
            ++depth;
        }

        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= C || ny < 0 || ny >= R || dist[ny][nx] != -1)
                continue;

            q.push({nx, ny});
            dist[ny][nx] = curDist + 1;
        }
    }
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> R >> C;
    dist.resize(R, vector<int>(C, -1));

    for (int i = 0; i < R; ++i) {
        for (int j = 0; j < C; ++j) {
            char input;
            cin >> input;

            if (input == 'D')
                dest = {j, i};
            else if (input == '*') {
                flood.push({j, i});
                dist[i][j] = 0;
            } else if (input == 'X') {
                dist[i][j] = 0;
            } else if (input == 'S') {
                start = {j, i};
                dist[i][j] = 0;
            }
        }
    }

    bfs();

    int ans = dist[dest.y][dest.x];
    if (ans == -1)
        cout << "KAKTUS";
    else
        cout << ans;

    return 0;
}