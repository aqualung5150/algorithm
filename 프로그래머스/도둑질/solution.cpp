#include <algorithm>
#include <vector>

using namespace std;

int solution(vector<int> money) {
    int ans;
    
    vector<int> dp(money.size());
    // [0]선택
    dp[0] = money[0];
    dp[1] = max(dp[0], money[1]);
    for (int i = 2; i < money.size() - 1; ++i)
        dp[i] = max(dp[i - 1], dp[i - 2] + money[i]);
    
    ans = dp[money.size() - 2];
    
    // [0]선택안함
    dp[0] = 0;
    dp[1] = money[1];
    for (int i = 2; i < money.size(); ++i)
        dp[i] = max(dp[i - 1], dp[i - 2] + money[i]);
    
    ans = max(ans, dp[money.size() - 1]);
    
    return ans;
}