#include <vector>
#include <string>

#define MAX 100000;
#define MIN -100000;

using namespace std;

int solution(vector<string> arr) {
    // 0 -> min | 1 -> max
    vector<vector<vector<int>>> dp(2, vector<vector<int>>(arr.size() / 2 + 1, vector<int>(arr.size() / 2 + 1)));
    
    for (int k = 0; k <= arr.size() / 2; ++k) {
        for (int i = 0; i <= arr.size() / 2 - k; ++i) {
            // init
            if (k == 0) {
                dp[0][i][i] = stoi(arr[i * 2]);
                dp[1][i][i] = stoi(arr[i * 2]);
            } else {
                dp[0][i][i + k] = MAX;
                dp[1][i][i + k] = MIN;
            }
            
            for (int j = i; j < i + k; ++j) {
                if (arr[j * 2 + 1] == "+") {
                    dp[0][i][i + k] = min(dp[0][i][i + k], dp[0][i][j] + dp[0][j + 1][i + k]);
                    dp[1][i][i + k] = max(dp[1][i][i + k], dp[1][i][j] + dp[1][j + 1][i + k]);
                } else {
                    dp[0][i][i + k] = min(dp[0][i][i + k], dp[0][i][j] - dp[1][j + 1][i + k]);
                    dp[1][i][i + k] = max(dp[1][i][i + k], dp[1][i][j] - dp[0][j + 1][i + k]);
                }
            }
        }
    }
    
    return dp[1][0][arr.size() / 2];
}