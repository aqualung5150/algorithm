#include <vector>

#define MOD 1234567

using namespace std;

int solution(int n) {
    vector<int> dp(n + 1);
    dp[1] = 1;
    
    for (int i = 2; i <= n; ++i)
        dp[i] = (dp[i - 2] + dp[i - 1]) % MOD;
    
    return dp[n];
}