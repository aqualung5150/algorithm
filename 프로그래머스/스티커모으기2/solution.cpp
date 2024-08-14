#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> sticker) {    
    if (sticker.size() == 1)
        return sticker[0];
    
    int ans = 0;
    vector<int> dp(sticker.size());
    
    // exclude sticker[0];
    dp[0] = 0;
    dp[1] = sticker[1];
    for (int i = 2; i < sticker.size(); ++i)
        dp[i] = max(dp[i - 1], dp[i - 2] + sticker[i]);
    
    ans = dp[sticker.size() - 1];
    
    // include sticker[0];
    dp[0] = sticker[0];
    dp[1] = max(sticker[0], sticker[1]);
    for (int i = 2; i < sticker.size() - 1; ++i)
        dp[i] = max(dp[i - 1], dp[i - 2] + sticker[i]);
    
    ans = max(ans, dp[sticker.size() - 2]);
    
    return ans;
}