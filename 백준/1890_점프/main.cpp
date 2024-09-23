#include <iostream>
#include <cstring>

using namespace std;

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    int N;
    cin >> N;

    int board[N][N];
    for (int i = 0; i < N; ++i)
        for (int j = 0; j < N; ++j)
            cin >> board[i][j];

    long long dp[100][100];
    memset(dp, 0, sizeof(dp));
    
    dp[0][0] = 1;

    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < N; ++j) {
            if (i == N - 1 && j == N - 1)
                break;

            if (!dp[i][j])
                continue;
            
            if (j + board[i][j] < N)
                dp[i][j + board[i][j]] += dp[i][j];
            if (i + board[i][j] < N)
                dp[i + board[i][j]][j] += dp[i][j];
        }
    }

    cout << dp[N - 1][N - 1];

    return 0;
}