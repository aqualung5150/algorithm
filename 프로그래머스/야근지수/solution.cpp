#include <string>
#include <vector>
#include <queue>

using namespace std;

long long solution(int n, vector<int> works) {
    priority_queue<int> pq;
    for (int i = 0; i < works.size(); ++i)
        pq.push(works[i]);
    
    while (n) {
        int cur = pq.top();
        if (!cur) return 0;
        pq.pop();
        pq.push(--cur);
        --n;
    }
    
    long long ans = 0;
    while (!pq.empty()) {
        ans += pq.top() * pq.top();
        pq.pop();
    }
    
    return ans;
}