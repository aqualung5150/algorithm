#include <iostream>
#include <vector>

using namespace std;

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    int N;
    cin >> N;

    vector<int> dp(N + 1);
    dp[2] = 3;
    dp[4] = 11;

    for (int i = 6; i <= N; i += 2) {
        dp[i] += dp[i - 2] * 3;
        for (int j = i - 4; j >= 2; j -= 2) {
            dp[i] += dp[j] * 2;
        }
        dp[i] += 2;
    }
    
    cout << dp[N];

    return 0;
}