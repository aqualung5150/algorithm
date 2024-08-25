#include <queue>
#include <vector>

using namespace std;

int solution(vector<int> scoville, int K) {
    priority_queue<int, vector<int>, greater<int>> pq;
    
    for (auto s : scoville)
        pq.push(s);
    
    int ans = 0;
    while (!pq.empty()) {
        int cur = pq.top();
        pq.pop();
        
        if (cur >= K)
            return ans;
        
        if (pq.empty())
            return -1;
        
        cur += pq.top() * 2;
        pq.pop();
        
        pq.push(cur);
        ++ans;
    }
    return -1;
}