#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int find_root(vector<int> &parent, int x) {
    if (parent[x] == x)
        return x;
    return find_root(parent, parent[x]);
}

void union_root(vector<int> &parent, int x, int y) {
    x = find_root(parent, x);
    y = find_root(parent, y);
    
    if (x != y)
        parent[y] = x;
}

int solution(int n, vector<vector<int>> costs) {
    sort(costs.begin(), costs.end(), [](const vector<int> &a, const vector<int> &b) { return a[2] < b[2]; });
    
    vector<int> parent(n);
    for (int i = 0; i < parent.size(); ++i)
        parent[i] = i;
    
    int ans = 0;
    int count = 0;
    for (auto cost : costs) {
        int v1 = cost[0];
        int v2 = cost[1];
        int c = cost[2];
        
        if (find_root(parent, v1) == find_root(parent, v2))
            continue;
        
        ans += c;
        union_root(parent, v1, v2);
        ++count;
        
        if (count == n - 1)
            return ans;
    }
}