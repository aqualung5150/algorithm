#include <iostream>
#include <vector>
#include <cmath>
#include <stack>
#include <algorithm>
#include <queue>

using namespace std;

int N, Q;
int len;
int total = 0;
int largest = 0;
vector<vector<int>> board;
int dx[] = {0, 1, 0, -1};
int dy[] = {1, 0, -1, 0};

int bfs(vector<vector<bool>> &visited, int sx, int sy) {
    int ret = 0;
    queue<pair<int, int>> q;
    q.push({sx, sy});
    visited[sy][sx] = true;

    while (!q.empty()) {
        int x = q.front().first;
        int y = q.front().second;
        q.pop();

        ++ret;

        for (int i = 0; i < 4; ++i) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= len || ny < 0 || ny >= len || visited[ny][nx] || !board[ny][nx])
                continue;

            q.push({nx, ny});
            visited[ny][nx] = true;
        }
    }

    return ret;
}

void largestIce() {
    vector<vector<bool>> visited(len, vector<bool>(len));
    for (int i = 0; i < len; ++i) {
        for (int j = 0; j < len; ++j) {
            if (visited[i][j] || !board[i][j])
                continue;
            
            largest = max(largest, bfs(visited, j, i));
        }
    }
}

void reduceIce() {
    stack<pair<int, int>> stk;

    for (int i = 0; i < len; ++i) {
        for (int j = 0; j < len; ++j) {
            if (!board[i][j])
                continue;

            int nonIce = 0;

            for (int d = 0; d < 4; ++d) {
                int nx = j + dx[d];
                int ny = i + dy[d];

                if (nx < 0 || nx >= len || ny < 0 || ny >= len || !board[ny][nx])
                    ++nonIce;
            }

            if (nonIce >= 2)
                stk.push({j, i});
        }
    }

    while (!stk.empty()) {
        --board[stk.top().second][stk.top().first];
        --total;
        stk.pop();
    }
}

void rotateSub(int x, int y, int subLen) {
    int size = subLen / 2;
    vector<vector<int>> temp(size, vector<int>(size));
    for (int i = 0; i < size; ++i)
        for (int j = 0; j < size; ++j)
            temp[i][j] = board[y + i][x + j];
    
    for (int d = 0; d < 3; ++d) {
        int nx = x + dx[d] * size;
        int ny = y + dy[d] * size;

        for (int i = 0; i < size; ++i)
            for (int j = 0; j < size; ++j)
                board[y + i][x + j] = board[ny + i][nx + j];

        x = x + dx[d] * size;
        y = y + dy[d] * size;
    }

    for (int i = 0; i < size; ++i) {
        for (int j = 0; j < size; ++j) {
            board[y + i][x + j] = temp[i][j];
        }
    }
}

void solve(int L) {
    for (int k = 1; k <= L; ++k) { // 단계
        int subLen = pow(2, k); // 부분 격자의 크기
        for (int i = 0; i < len; i += subLen) {
            for (int j = 0; j < len; j += subLen) {
                rotateSub(j, i, subLen);
            }
        }
    }
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> N >> Q;
    len = pow(2, N);
    board.resize(len, vector<int>(len));

    for (int i = 0; i < len; ++i)
        for (int j = 0; j < len; ++j) {
            cin >> board[i][j];
            total += board[i][j];
        }
    
    while (Q--) {
        int L;
        cin >> L;

        solve(L);
        reduceIce();
    }
    largestIce();

    cout << total << "\n" << largest;

    return 0;
}