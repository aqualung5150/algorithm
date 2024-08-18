#include <vector>
#include <unordered_set>

using namespace std;

int solution(vector<int> elements) {
    int len = elements.size();
    
    for (int i = 0; i < len - 1; ++i)
        elements.push_back(elements[i]);
    
    vector<vector<int>> dp(elements.size(), vector<int>(elements.size(), 0));
    
    unordered_set<int> s;
    
    for (int k = 0; k < len; ++k) {
        for (int i = 0; i < elements.size() - k; ++i){
            dp[i][i + k] = dp[i][i + k - 1] + elements[i + k];
            s.insert(dp[i][i + k]);
        }
    }
    
    return s.size();
}