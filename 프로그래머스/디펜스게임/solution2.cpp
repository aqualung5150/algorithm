#include <vector>
#include <queue>

using namespace std;

int solution(int n, int k, vector<int> enemy) {
    int ans = 0;
    priority_queue<int> pq;
    
    for (int e : enemy) {
        if (n < e && k == 0)
            break;
        
        n -= e;
        pq.push(e);
        
        if (n < 0) {
            n += pq.top();
            pq.pop();
            --k;
        }
        
        ++ans;
    }
    
    return ans;
}