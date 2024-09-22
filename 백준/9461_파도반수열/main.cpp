#include <iostream>

using namespace std;

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    long long dp[101];
    dp[1] = 1;
    dp[2] = 1;
    dp[3] = 1;

    for (int i = 4; i <= 100; ++i)
        dp[i] = dp[i - 3] + dp[i - 2];

    int T;
    cin >> T;
    while (T--) {
        int N;
        cin >> N;
        cout << dp[N] << "\n";
    }

    return 0;
}