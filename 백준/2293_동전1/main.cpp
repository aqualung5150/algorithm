#include <iostream>
#include <vector>

using namespace std;

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    int n, k;
    cin >> n >> k;

    vector<int> dp(k + 1);
    dp[0] = 1;

    while (n--) {
        int coin;
        cin >> coin;
        for (int i = 1; i <= k; ++i) {
            if (coin > i)
                continue;
            dp[i] += dp[i - coin];
        }
    }

    cout << dp[k];

    return 0;
}