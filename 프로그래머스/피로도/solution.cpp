#include <vector>
#include <algorithm>

using namespace std;

int ans = 0;

void dfs(vector<vector<int>> &dungeons, vector<bool> &visited, int count, int k) {
    ans = max(ans, count);
    for (int i = 0; i < dungeons.size(); ++i) {
        if (visited[i])
            continue;
        
        if (k >= dungeons[i][0]) {
            visited[i] = true;
            dfs(dungeons, visited, count + 1, k - dungeons[i][1]);
            visited[i] = false;
        }
    }
}

int solution(int k, vector<vector<int>> dungeons) {
    vector<bool> visited(dungeons.size());
    
    dfs(dungeons, visited, 0, k);
    
    return ans;
}