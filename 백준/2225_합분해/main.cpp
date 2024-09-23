#include <iostream>
#include <cstring>

using namespace std;

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    int dp[201][201];

    memset(dp, 0, sizeof(dp));

    int N, K;
    cin >> N >> K;

    for (int i = 0; i <= N; ++i)
        dp[1][i] = 1;

    for (int i = 2; i <= K; ++i) {
        for (int j = 0; j <= N; ++j) {
            for (int k = 0; k <= j; ++k) {
                dp[i][j] = (dp[i][j] + dp[i - 1][k]) % 1000000000;
            }
        }
    }

    cout << dp[K][N];

    return 0;
}