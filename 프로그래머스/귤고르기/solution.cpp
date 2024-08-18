#include <unordered_map>
#include <queue>
#include <vector>

using namespace std;

int solution(int k, vector<int> tangerine) {
    unordered_map<int, int> m; // {size, number}
    priority_queue<pair<int, int>> pq; // {nubmer, size}
    
    for (int i = 0; i < tangerine.size(); ++i)
        ++m[tangerine[i]];
    
    for (auto it = m.begin(); it != m.end(); ++it)
        pq.push({it->second, it->first});
    
    int ans = 0;
    while (k > 0) {
        k -= pq.top().first;
        pq.pop();
        ++ans;
    }
    
    return ans;
}