#include <string>
#include <vector>
#include <string.h>
#include <algorithm>

using namespace std;

int dp[501][501];

int solution(vector<vector<int>> triangle) {
    memset(dp, 0, sizeof(dp));
    
    for (int i = 0; i < triangle.size(); ++i) {
        for (int j = 0; j < triangle[i].size(); ++j) {
            dp[i + 1][j + 1] = triangle[i][j];
        }
    }
    
    for (int i = 1; i <= triangle.size(); ++i) {
        for (int j = 1; j <= i; ++j) {
            dp[i][j] += max(dp[i - 1][j - 1], dp[i - 1][j]);
        }
    }
    
    int ans = 0;
    for (int i = 1; i <= triangle.size(); ++i)
        ans = max(ans, dp[triangle.size()][i]);
    
    return ans;
}