#include <iostream>
#include <algorithm>

using namespace std;

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    int N;
    cin >> N;

    int arr[N];
    int dp[2][N];

    for (int i = 0; i < N; ++i)
        cin >> arr[i];

    dp[0][0] = arr[0];
    dp[1][0] = arr[0];
    int ans = arr[0];
    for (int i = 1; i < N; ++i) {
        dp[0][i] = max(dp[0][i - 1] + arr[i], arr[i]);
        dp[1][i] = max(dp[0][i - 1], dp[1][i - 1] + arr[i]);

        ans = max(ans, max(dp[0][i], dp[1][i]));
    }

    cout << ans;

    return 0;
}