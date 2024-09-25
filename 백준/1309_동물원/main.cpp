#include <iostream>
#include <vector>

#define MOD 9901

using namespace std;

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    int N;
    cin >> N;

    vector<int> e(N + 1);
    vector<int> c(N + 1);

    e[1] = 1;
    c[1] = 2;

    for (int i = 2; i <= N; ++i) {
        e[i] = (e[i - 1] + c[i - 1]) % 9901;
        c[i] = (e[i - 1]*2 + c[i - 1]) % 9901;
    }

    cout << (e[N] + c[N]) % 9901;

    // 규칙으로 풀기
    // vector<int> dp(N);
    // dp[0] = 1;
    // dp[1] = 3;

    // for (int i = 2; i <= N; ++i)
    //     dp[i] = (dp[i - 1]*2 + dp[i - 2]) % 9901;

    // cout << dp[N];

    return 0;
}