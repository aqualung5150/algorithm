#include <iostream>
#include <vector>
#include <algorithm>

#define MAX 11

using namespace std;

int N, M;
vector<vector<char>> board;
vector<vector<vector<vector<bool>>>> visited;
int ans = 11;
int dx[] = {1, -1, 0, 0};
int dy[] = {0, 0, 1, -1};

void dfs(int x1, int y1, int x2, int y2, int count) {
    if (count > 10)
        return;

    for (int i = 0; i < 4; ++i) {
        // blue
        int nx2 = x2;
        int ny2 = y2;
        while (board[ny2][nx2] == '.') {
            nx2 += dx[i];
            ny2 += dy[i];
        }
        if (board[ny2][nx2] == 'O')
            continue;
        else {
            nx2 -= dx[i];
            ny2 -= dy[i];
        }
        // red
        int nx1 = x1;
        int ny1 = y1;
        while (board[ny1][nx1] == '.') {
            nx1 += dx[i];
            ny1 += dy[i];
        }
        if (board[ny1][nx1] == 'O') {
            ans = min(ans, count);
            continue;
        } else {
            nx1 -= dx[i];
            ny1 -= dy[i];
        }

        // repos red-blue
        if (nx1 == nx2 && ny1 == ny2) {
            if (x1 < x2) {
                if (i == 0)
                    nx1 = nx2 - 1;
                else if (i == 1)
                    nx2 = nx1 + 1;
            } else if (x1 > x2) {
                if (i == 0)
                    nx2 = nx1 - 1;
                else if (i == 1)
                    nx1 = nx2 + 1;
            } else if (y1 < y2) {
                if (i == 2)
                    ny1 = ny2 - 1;
                else if (i == 3)
                    ny2 = ny1 + 1;
            } else if (y1 > y2) {
                if (i == 2)
                    ny2 = ny1 - 1;
                else if (i == 3)
                    ny1 = ny2 + 1;
            }
        }
        if (visited[ny1][nx1][ny2][nx2])
            continue;

        visited[ny1][nx1][ny2][nx2] = true;
        dfs(nx1, ny1, nx2, ny2, count + 1);
        visited[ny1][nx1][ny2][nx2] = false;
    }
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> N >> M;

    int sx1, sy1, sx2, sy2;
    board.resize(N, vector<char>(M));
    visited.resize(N, vector<vector<vector<bool>>>(M, vector<vector<bool>>(N, vector<bool>(M))));

    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < M; ++j) {
            char input;
            cin >> input;
            if (input == 'R') {
                sx1 = j;
                sy1 = i;
                board[i][j] = '.';
            } else if (input == 'B') {
                sx2 = j;
                sy2 = i;
                board[i][j] = '.';
            } else
                board[i][j] = input;
        }
    }

    visited[sy1][sx1][sy2][sx2] = true;

    dfs(sx1, sy1, sx2, sy2, 1);

    if (ans == MAX)
        cout << -1;
    else
        cout << ans;

    return 0;
}

/*
5 5
#####
#..B#
#.#.#
#RO.#
#####
*/