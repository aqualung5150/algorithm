#include <iostream>
#include <vector>

using namespace std;

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    int T;
    cin >> T;

    while (T--) {
        int N, M;
        cin >> N;

        vector<int> coins(N);
        for (int i = 0; i < N; ++i)
            cin >> coins[i];

        cin >> M;

        vector<int> dp(M + 1);
        dp[0] = 1;

        for (int c : coins) {
            for (int i = 1; i <= M; ++i) {
                if (c > i)
                    continue;
                
                dp[i] += dp[i - c];
            }
        }

        cout << dp[M] << "\n";
    }


    return 0;
}