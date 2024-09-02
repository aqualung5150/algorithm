#include <queue>
#include <vector>

using namespace std;

int solution(int x, int y, int n) {
    queue<pair<int, int>> q; // {coord, dist}
    q.push({y, 0});
    
    while (!q.empty()) {
        int here = q.front().first;
        int dist = q.front().second;
        q.pop();
        
        if (here == x)
            return dist;
        
        if (here % 2 == 0)
            q.push({here / 2, dist + 1});
        if (here % 3 == 0)
            q.push({here / 3, dist + 1});
        if (here - n > 0)
            q.push({here - n, dist + 1});
    }
    
    return -1;
}