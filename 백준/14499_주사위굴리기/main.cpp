#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    int N, M, x, y, K;
    cin >> N >> M >> y >> x >> K;

    // board
    vector<vector<int>> board(N, vector<int>(M));
    // dice
    vector<int> horizon(4);
    vector<int> vertical(4);
    // direction
    int dx[] = {1, -1, 0, 0};
    int dy[] = {0, 0, -1, 1};

    for (int i = 0; i < N; ++i)
        for (int j = 0; j < M; ++j)
            cin >> board[i][j];

    while (K--) {
        int op;
        cin >> op;

        int nx = x + dx[op - 1];
        int ny = y + dy[op - 1];

        if (nx < 0 || nx >= M || ny < 0 || ny >= N)
            continue;

        x = nx;
        y = ny;

        switch (op) {
            case 1:
                rotate(horizon.begin(), horizon.end() - 1, horizon.end());
                vertical[1] = horizon[1];
                vertical[3] = horizon[3];
                break;
            case 2:
                rotate(horizon.begin(), horizon.begin() + 1, horizon.end());
                vertical[1] = horizon[1];
                vertical[3] = horizon[3];
                break;
            case 3:
                rotate(vertical.begin(), vertical.begin() + 1, vertical.end());
                horizon[1] = vertical[1];
                horizon[3] = vertical[3];
                break;
            case 4:
                rotate(vertical.begin(), vertical.end() - 1, vertical.end());
                horizon[1] = vertical[1];
                horizon[3] = vertical[3];
                break;
        }

        if (board[y][x] == 0)
            board[y][x] = horizon[3];
        else {
            horizon[3] = board[y][x];
            vertical[3] = board[y][x];
            board[y][x] = 0;
        }

        cout << horizon[1] << "\n";
    }

    return 0;
}