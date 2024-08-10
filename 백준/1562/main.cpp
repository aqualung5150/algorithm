#include <iostream>
#include <vector>

using namespace std;

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    int N;
    cin >> N;

    vector<vector<vector<int>>> dp(N, vector<vector<int>>(10, vector<int>(1024)));

    for (int i = 1; i < 10; ++i)
        dp[0][i][1 << i] = 1;

    for (int i = 1; i < N; ++i) {
        for (int j = 0; j < 10; ++j) {
            for (int k = 0; k < 1024; ++k) {
                if (j - 1 >= 0)
                    dp[i][j][k | (1 << j)] += dp[i - 1][j - 1][k];
                if (j + 1 <= 9)
                    dp[i][j][k | (1 << j)] += dp[i - 1][j + 1][k];
                dp[i][j][k | (1 << j)] %= 1000000000;
            }
        }
    }

    long long ans = 0;
    for (int i = 0; i < 10; ++i)
        ans += dp[N - 1][i][1023];
    cout << ans % 1000000000;

    return 0;
}