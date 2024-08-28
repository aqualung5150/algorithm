#include <iostream>
#include <vector>

#define MAX 100000

using namespace std;

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    int n, k;
    cin >> n >> k;
    vector<int> coins(n);
    vector<int> dp(k + 1, MAX);

    for (int i = 0; i < n; ++i)
        cin >> coins[i];

    dp[0] = 0;
    for (auto c : coins) {
        for (int i = c; i <= k; ++i)
            dp[i] = min(dp[i], dp[i - c] + 1);
    }

    if (dp[k] == MAX)
        cout << -1;
    else
        cout << dp[k];

    return 0;
}