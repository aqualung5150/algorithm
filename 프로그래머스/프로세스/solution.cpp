#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int solution(vector<int> priorities, int location) {
    queue<pair<int, int>> q;
    
    for (int i = 0; i < priorities.size(); ++i)
        q.push({priorities[i], i});
 
    sort(priorities.begin(), priorities.end(), [](const int& a, const int& b) { return a > b; });
    
    int max_i = 0;
    while (!q.empty()) {
        int p = q.front().first;
        int l = q.front().second;
        q.pop();
        
        if (priorities[max_i] == p) {
            ++max_i;
            if (l == location)
                return max_i;
        }
        q.push({p, l});
    }
}