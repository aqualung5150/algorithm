#include <iostream>
#include <vector>

using namespace std;

int N, M;
vector<vector<bool>> dp;

int main() {
    ios::sync_with_stdio(false); cin.tie(nullptr); cout.tie(nullptr);

    cin >> N >> M;
    dp.resize(N, vector<bool>(N));

    for (int i = 0; i < M; ++i) {
        int a, b;
        cin >> a >> b;
        --a; --b;

        dp[a][b] = true;
    }

    for (int k = 0; k < N; ++k) {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                if (i == j)
                    continue;

                if (dp[i][k] && dp[k][j])
                    dp[i][j] = true;
            }
        }
    }

    int answer = N;
    for (int i = 0; i < N; ++i) {
        for (int j = 0; j < N; ++j) {
            if (i == j)
                continue;

            if (!dp[i][j] && !dp[j][i]) {
                --answer;
                break;
            }
        }
    }

    cout << answer;

    return 0;
}