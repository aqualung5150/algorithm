#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<vector<int>> routes) {
    sort(routes.begin(), routes.end(), [](const vector<int> &a, const vector<int> &b) { return a[1] < b[1]; });
    
    int ans = 0;
    vector<bool> visited(routes.size());
    for (int i = 0; i < routes.size(); ++i) {
        if (visited[i]) continue;
        else ++ans;
        
        int camera = routes[i][1];
        
        for (int j = i; j < routes.size(); ++j) {
            int start = routes[j][0];
            int finish = routes[j][1];
            if (start <= camera && camera <= finish)
                visited[j] = true;
        }
    }
    
    return ans;
}