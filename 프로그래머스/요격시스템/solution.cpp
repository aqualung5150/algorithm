#include <algorithm>
#include <vector>

using namespace std;

int solution(vector<vector<int>> targets) {
    sort(targets.begin(), targets.end(), [](vector<int> &a, vector<int> &b) { return a[1] < b[1]; });
    
    vector<bool> visited(targets.size());
    
    int ans = 0;
    for (int i = 0; i < targets.size(); ++i) {
        if (visited[i])
            continue;
        
        ++ans;
        int m = targets[i][1];
        for (int j = i; j < targets.size(); ++j) {
            if (visited[j])
                continue;
            
            int s = targets[j][0];
            int e = targets[j][1];
            if (s < m && m <= e)
                visited[j] = true;
        }
    }
    
    return ans;
}