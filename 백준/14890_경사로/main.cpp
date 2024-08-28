#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N, L;
vector<vector<int>> board;
vector<vector<bool>> visited;

vector<vector<int>> rotateBoard() {
    vector<vector<int>> ret(N, vector<int>(N));

    for (int i = 0; i < N; ++i)
        for (int j = 0; j < N; ++j)
            ret[N - 1 - j][N - 1 - i] = board[i][N - 1 - j];

    return ret;
}

bool slope(int x, int y) {
    if (x < 0 || x + L > N)
        return false;
    
    int b = board[y][x];
    for (int i = 0; i < L; ++i) {
        if (board[y][x + i] != b || visited[y][x + i])
            return false;
    }
    for (int i = 0; i < L; ++i)
        visited[y][x + i] = true;
    return true;
}

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> N >> L;
    board.resize(N, vector<int>(N));
    visited.resize(N, vector<bool>(N));

    for (int i = 0; i < N; ++i)
        for (int j = 0; j < N; ++j)
            cin >> board[i][j];

    int ans = 0;

    // horizon
    for (int i = 0; i < N; ++i) {
        bool valid = true;

        for (int j = 1; j < N; ++j) {
            if (board[i][j] == board[i][j - 1])
                continue;

            if (abs(board[i][j] - board[i][j - 1]) >  1) {
                valid = false;
                break;
            }

            if (board[i][j] > board[i][j - 1]) {
                if (!slope(j - L, i)) {
                    valid = false;
                    break;
                }
            }
            if (board[i][j] < board[i][j - 1]) {
                if (!slope(j, i)) {
                    valid = false;
                    break;
                }
            }
        }

        if (valid)
            ++ans;
    }

    board = rotateBoard();
    fill(visited.begin(), visited.end(), vector<bool>(N, false));

    // vertical
    for (int i = 0; i < N; ++i) {
        bool valid = true;

        for (int j = 1; j < N; ++j) {
            if (board[i][j] == board[i][j - 1])
                continue;

            if (abs(board[i][j] - board[i][j - 1]) >  1) {
                valid = false;
                break;
            }

            if (board[i][j] > board[i][j - 1]) {
                if (!slope(j - L, i)) {
                    valid = false;
                    break;
                }
            }
            if (board[i][j] < board[i][j - 1]) {
                if (!slope(j, i)) {
                    valid = false;
                    break;
                }
            }
        }

        if (valid)
            ++ans;
    }
    
    cout << ans;

    return 0;
}