#include <string>
#include <vector>

#define MOD 1234567

using namespace std;

long long solution(int n) {
    vector<long long> dp(n + 1);
    
    dp[0] = 1;
    dp[1] = 1;
    
    for (int i = 2; i <= n; ++i)
        dp[i] = (dp[i - 1] + dp [i - 2]) % MOD;
    
    return dp[n];
}