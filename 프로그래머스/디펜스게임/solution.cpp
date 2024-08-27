#include <vector>
#include <queue>

using namespace std;

int solution(int n, int k, vector<int> enemy) {
    int left = 0;
    int right = enemy.size();
    int ans;
    
    while (left <= right) {
        int mid = (right + left) / 2;
        
        priority_queue<int> pq;
        for (int i = 0; i < mid; ++i)
            pq.push(enemy[i]);
        
        for (int i = 0; i < k && !pq.empty(); ++i)
            pq.pop();
        
        long long sum = 0;
        while (!pq.empty()) {
            sum += pq.top();
            pq.pop();
        }
        
        if (sum > n)
            right = mid - 1;
        else {
            ans = mid;
            left = mid + 1;
        }
    }
    
    return ans;   
}