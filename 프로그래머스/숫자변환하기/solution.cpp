#include <queue>
#include <vector>

using namespace std;

int solution(int x, int y, int n) {
    vector<int> dist(y + 1, -1);
    queue<int> q;
    q.push(x);
    dist[x] = 0;
    
    while (!q.empty()) {
        int here = q.front();
        q.pop();
        
        if (here == y)
            break;
        
        int there[3] = {here * 2, here * 3, here + n};
        
        for (int i = 0; i < 3; ++i) {
            if (there[i] <= y && dist[there[i]] == -1) {
                dist[there[i]] = dist[here] + 1;
                q.push(there[i]);
            }
        }
    }
    
    return dist[y];
}