#include <iostream>
#include <vector>

using namespace std;

int N, K;
vector<vector<int>> board;
vector<vector<int>> unit; // {x, y, d}
vector<vector<vector<int>>> status; // [y][x][z]
int ans = 1;
bool finish;
int dx[] = {1, -1, 0, 0};
int dy[] = {0, 0, -1, 1};

void move(int k, bool isRed) {
    int x = unit[k][0];
    int y = unit[k][1];
    int nx = x + dx[unit[k][2]];
    int ny = y + dy[unit[k][2]];
    
    // 다음칸의 높이
    int nextLast = 0;
    while (nextLast < K && status[ny][nx][nextLast] != 0)
        ++nextLast;
    // 현재말의 높이
    int curFirst = 0;
    while (curFirst < K && status[y][x][curFirst] != k)
        ++curFirst;
    // 옮겨야하는 말들의 사이즈
    int curSize = 0;
    while (curSize < K && status[y][x][curSize] != 0)
        ++curSize;

    if (!isRed) {
        for (int i = curFirst; i < curSize; ++i) {
            int curK = status[y][x][i];
            status[ny][nx][nextLast] = curK;
            status[y][x][i] = 0;
            ++nextLast;

            unit[curK][0] = nx;
            unit[curK][1] = ny;
        }
    } else {
        for (int i = curSize - 1; i >= curFirst; --i) {
            int curK = status[y][x][i];
            status[ny][nx][nextLast] = curK;
            status[y][x][i] = 0;
            ++nextLast;

            unit[curK][0] = nx;
            unit[curK][1] = ny;
        }
    }

    int cnt = 0;
    while (cnt < K && status[ny][nx][cnt] != 0)
        ++cnt;
    if (cnt >= 4)
        finish = true;
}

void check(int k) {
    int nx = unit[k][0] + dx[unit[k][2]];
    int ny = unit[k][1] + dy[unit[k][2]];

    // blue
    if (nx < 0 || nx >= N || ny < 0 || ny >= N || board[ny][nx] == 2) {
        int d = unit[k][2];
        switch (d) {
            case 0:
                unit[k][2] = 1;
                break;
            case 1:
                unit[k][2] = 0;
                break;
            case 2:
                unit[k][2] = 3;
                break;
            case 3:
                unit[k][2] = 2;
                break;
        }
        nx = unit[k][0] + dx[unit[k][2]];
        ny = unit[k][1] + dy[unit[k][2]];

        if (nx < 0 || nx >= N || ny < 0 || ny >= N || board[ny][nx] == 2)
            return;

        check(k);
    // red
    } else if (board[ny][nx] == 1) {
        move(k, true);
    } else {
        move(k, false);
    }
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> N >> K;
    board.resize(N, vector<int>(N));
    unit.resize(K + 1, vector<int>(3));
    status.resize(N, vector<vector<int>>(N, vector<int>(K)));
    for (int i = 0; i < N; ++i)
        for (int j = 0; j < N; ++j)
            cin >> board[i][j];

    for (int i = 1; i <= K; ++i) {
        int x, y, d;
        cin >> y >> x >> d;
        unit[i][0] = x - 1;
        unit[i][1] = y - 1;
        unit[i][2] = d - 1;
        status[y - 1][x - 1][0] = i;
    }

    while (1) {
        if (ans > 1000) {
            cout << -1;
            return 0;
        }
        for (int k = 1; k <= K; ++k) {
            check(k);
            if (finish) {
                cout << ans;
                return 0;
            }
        }
        ++ans;
    }

    return 0;
}