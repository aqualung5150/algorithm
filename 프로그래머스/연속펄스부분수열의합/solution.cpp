#include <vector>
#include <algorithm>

using namespace std;

long long solution(vector<int> sequence) {
    // [0] = 1 / [1] = -1
    vector<vector<long long>> dp(2, vector<long long>(sequence.size()));
    
    dp[0][0] = sequence[0];
    dp[1][0] = -sequence[0];
    
    long long ans = max(dp[0][0], dp[1][0]);
    for (int i = 1; i < sequence.size(); ++i) {
        dp[0][i] = max((long long)sequence[i], dp[1][i - 1] + sequence[i]);
        dp[1][i] = max(-(long long)sequence[i], dp[0][i - 1] - sequence[i]);
        
        ans = max(ans, dp[0][i]);
        ans = max(ans, dp[1][i]);
    }
    
    return ans;
}