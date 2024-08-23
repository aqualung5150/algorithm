#include <vector>

using namespace std;

int MOD = 20170805;

int solution(int m, int n, vector<vector<int>> city_map) {
    if (m == 1 && n == 1)
        return 1;
    //[0]->from left, [1]->from above
    vector<vector<vector<int>>> dp(2, vector<vector<int>>(m, vector<int>(n)));
    
    dp[0][0][0] = 1;
    dp[1][0][0] = 1;
    for (int i = 1; i < m; ++i) {
        if (city_map[i][0] != 1)
            dp[1][i][0] = dp[1][i - 1][0];
    }
    for (int i = 1; i < n; ++i) {
        if (city_map[0][i] != 1)
            dp[0][0][i] = dp[0][0][i - 1];
    }
    
    for (int i = 1; i < m; ++i) {
        for (int j = 1; j < n; ++j) {
            // 통행금지
            if (city_map[i][j] == 1) {
                dp[0][i][j] = 0;
                dp[1][i][j] = 0;
                continue;
            }
            // 왼쪽에서 오는 차
            dp[0][i][j] += dp[0][i][j - 1];
            if (city_map[i][j - 1] != 2)
                dp[0][i][j] += dp[1][i][j - 1];
            // 위쪽에서 오는 차
            dp[1][i][j] += dp[1][i - 1][j];
            if (city_map[i - 1][j] != 2)
                dp[1][i][j] += dp[0][i - 1][j];
            
            dp[0][i][j] %= MOD;
            dp[1][i][j] %= MOD;
        }
    }
    
    return (dp[0][m - 1][n - 1] + dp[1][m - 1][n - 1]) % MOD;
}

/*
dp[0] -> 왼쪽에서 온거
dp[1] -> 위에서 온거
dp[0][i] = dp[0][위] + dp[1][위] / dp[1][i] = dp[0][왼] + dp[1][왼] 
=> 만약 [위]==회전금지라면 dp[1][위]는 제외되어야함... [왼]==회전금지도 마찬가지

... return dp[0][목적지] + dp[1][목적지]
*/