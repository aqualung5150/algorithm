#include <iostream>
#include <vector>

using namespace std;

int N, ans = 1000000;
vector<vector<int>> v(1000, vector<int>(3));
vector<vector<int>> dp(1000, vector<int>(3));

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> N;

    for (int i = 0; i < N; ++i)
        for (int j = 0; j < 3; ++j)
            cin >> v[i][j];

    for (int first = 0; first < 3; ++first) {
        // fix first rgb
        dp[0][first] = v[0][first];
        dp[0][(first + 1) % 3] = 1000000;
        dp[0][(first + 2) % 3] = 1000000;
        // dp
        for (int i = 1; i < N; ++i)
            for (int rgb = 0; rgb < 3; ++rgb)
                dp[i][rgb] = min(dp[i - 1][(rgb + 1) % 3], dp[i - 1][(rgb + 2) % 3]) + v[i][rgb];
        // if (last rgb != first rgb)
        for (int rgb = 0; rgb < 3; ++rgb) {
            if (rgb == first)
                continue;
            ans = min(ans, dp[N - 1][rgb]);
        }
    }

    cout << ans;

    return 0;
}