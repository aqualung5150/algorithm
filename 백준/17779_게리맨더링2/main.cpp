#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N;
vector<vector<int>> board;
int total = 0;
int ans;

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> N;
    board.resize(N, vector<int>(N));

    for (int i = 0; i < N; ++i)
        for (int j = 0; j < N; ++j) {
            cin >> board[i][j];
            total += board[i][j];
        }
    ans = total;

    for (int i = 1; i < N - 1; ++i) {
        for (int j = 0; j < N - 2; ++j) {
            for (int d1 = 1; i - d1 >= 0; ++d1) {
                for (int d2 = 1; i + d2 < N && j + d1 + d2 < N; ++d2) {
                    vector<int> result(6);
                    result[5] = total;

                    // 1
                    int offset = 0;
                    for (int y = 0; y < i; ++y) {
                        if (y >= i - d1)
                            ++offset;
                        for (int x = 0; x <= j + d1 - offset; ++x) {
                            result[1] += board[y][x];
                            result[5] -= board[y][x];
                        }
                    }
                    // 2
                    offset = 0;
                    for (int y = 0; y <= i + d2 - d1; ++y) {
                        if (y > i - d1)
                            ++offset;
                        for (int x = j + d1 + 1 + offset; x < N; ++x) {
                            result[2] += board[y][x];
                            result[5] -= board[y][x];
                        }
                    }
                    // 3
                    offset = 0;
                    for (int y = i; y < N; ++y) {
                        if (y > i && y <= i + d2)
                            ++offset;
                        for (int x = 0; x < j + offset; ++x) {
                            result[3] += board[y][x];
                            result[5] -= board[y][x];
                        }
                    }
                    // 4
                    offset = 0;
                    for (int y = i - d1 + d2 + 1; y < N; ++y) {
                        if (y > i - d1 + d2 + 1 && y <= i + d2 + 1)
                            ++offset;
                        for (int x = j + d1 + d2 - offset; x < N; ++x) {
                            result[4] += board[y][x];
                            result[5] -= board[y][x];
                        }
                    }
                    // answer
                    int curMax = 0;
                    int curMin = total;
                    for (int a = 1; a <= 5; ++a) {
                        curMax = max(curMax, result[a]);
                        curMin = min(curMin, result[a]);
                    }
                    ans = min(ans, curMax - curMin);
                }
            }
        }
    }

    cout << ans;

    return 0;
}