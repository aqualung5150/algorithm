#include <algorithm>
#include <vector>

using namespace std;

int solution(vector<vector<int>> scores) {
    int target_a = scores[0][0];
    int target_b = scores[0][1];
    sort(scores.begin(), scores.end(), [](const vector<int> &a, const vector<int> &b) {
        if (a[0] == b[0])
            return a[1] < b[1];
        return a[0] > b[0];
    });
    
    int max_b = -1;
    int ans = 1;
    for (int i = 0; i < scores.size(); ++i) {
        int a = scores[i][0];
        int b = scores[i][1];
        
        if (a > target_a && b > target_b)
            return -1;
        
        if (b >= max_b) {
            max_b = b;
            if (a + b > target_a + target_b)
                ++ans;
        }
    }
    return ans;
}