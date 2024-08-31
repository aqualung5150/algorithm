#include <algorithm>
#include <vector>

using namespace std;

int solution(vector<vector<int>> targets) {
    sort(targets.begin(), targets.end(), [](vector<int> &a, vector<int> &b) { return a[1] < b[1]; });
    
    int ans = 0;
    int m = 0;
    for (auto t : targets) {
        if (t[0] >= m) {
            ++ans;
            m = t[1];
        }
    }
    return ans;
}