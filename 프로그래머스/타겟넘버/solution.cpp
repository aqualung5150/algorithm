#include <vector>
#include <queue>

using namespace std;

int solution(vector<int> numbers, int target) {
    int ans = 0;
    
    queue<pair<int, int>> q; // {dist, idx}
    q.push({numbers[0], 0});
    q.push({-numbers[0], 0});
    
    while (!q.empty()) {
        int dist = q.front().first;
        int idx = q.front().second;
        q.pop();
        
        if (idx == numbers.size() - 1) {
            if (dist == target)
                ++ans;
            continue;
        }
        
        q.push({dist + numbers[idx + 1], idx + 1});
        q.push({dist - numbers[idx + 1], idx + 1});
    }
    
    return ans;
}