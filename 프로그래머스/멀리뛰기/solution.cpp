#include <string>
#include <vector>

using namespace std;

vector<long long> dp;

long long solution(int n) {
    long long mod = 1234567;
    dp.resize(n + 1);
    dp[1] = 1;
    dp[2] = 2;
    
    for (int i = 3; i <= n; ++i)
        dp[i] = (dp[i - 1] + dp [i - 2]) % mod;
    
    return dp[n];
}