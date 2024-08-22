#include <queue>
#include <vector>

using namespace std;

vector<int> solution(int n, vector<vector<int>> roads, vector<int> sources, int destination) {
    vector<int> dist(n + 1, -1);
    vector<vector<int>> edges(n + 1);
    for (int i = 0; i < roads.size(); ++i) {
        edges[roads[i][0]].push_back(roads[i][1]);
        edges[roads[i][1]].push_back(roads[i][0]);
    }
    
    queue<int> q;
    q.push(destination);
    dist[destination] = 0;
    
    while (!q.empty()) {
        int here = q.front();
        q.pop();
        
        for (int there : edges[here]) {
            if (dist[there] != -1)
                continue;
            
            dist[there] = dist[here] + 1;
            q.push(there);
        }
    }
    
    vector<int> ans;
    for (int s : sources)
        ans.push_back(dist[s]);
    return ans;
}