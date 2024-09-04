#include <iostream>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int N, M, T;
int totalSum = 0;
int totalNum;
vector<vector<int>> board;

bool bfs(vector<vector<bool>> &visited, int sx, int sy) {
    int dx[] = {1, M - 1, 0, 0};
    int dy[] = {0, 0, 1, -1};

    queue<pair<int, int>> q;
    q.push({sx, sy});
    visited[sy][sx] = true;

    int target = board[sy][sx];
    bool isMatched = false; // 인접한 같은 수가 있는가

    while (!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();

        for (int i = 0; i < 4; ++i) {
            int nx = (x + dx[i]) % M;
            int ny = y + dy[i];

            if (ny < 0 || ny >= N || board[ny][nx] != target || visited[ny][nx])
                continue;

            visited[ny][nx] = true;
            q.push({nx, ny});
            totalSum -= board[ny][nx];
            --totalNum;
            board[ny][nx] = 0;
            isMatched = true;
        }
    }

    if (isMatched) {
        totalSum -= board[sy][sx];
        --totalNum;
        board[sy][sx] = 0;
    }

    return isMatched;
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> N >> M >> T;
    board.resize(N, vector<int>(M));

    for (int i = 0; i < N; ++i)
        for (int j = 0; j < M; ++j) {
            cin >> board[i][j];
            totalSum += board[i][j];
        }
    totalNum = N * M;

    while (T-- && totalNum) {
        int x, d, k;
        cin >> x >> d >>k;
        for (int i = 1; x * i <= N; ++i) {
            // rotate 배수
            if (d == 0)
                rotate(board[x * i - 1].begin(), board[x * i - 1].end() - k, board[x * i - 1].end());
            else
                rotate(board[x * i - 1].begin(), board[x * i - 1].begin() + k, board[x * i - 1].end());
        }

        vector<vector<bool>> visited(N, vector<bool>(M));
        bool flag = false;

        //인접
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (visited[i][j] || !board[i][j])
                    continue;

                if (bfs(visited, j, i))
                    flag = true;
            }
        }

        //평균
        if (!flag) {
            int avg = totalSum / totalNum;
            int prec = totalSum % totalNum;

            for (int i = 0; i < N; ++i) {
                for (int j = 0; j < M; ++j) {
                    if (!board[i][j])
                        continue;
                    if (board[i][j] > avg) {
                        --totalSum;
                        --board[i][j];
                    } else if (board[i][j] < avg) {
                        ++totalSum;
                        ++board[i][j];
                    } else if (prec) {
                        ++totalSum;
                        ++board[i][j];
                    }
                }
            }
        }
    }

    cout << totalSum;

    return 0;
}