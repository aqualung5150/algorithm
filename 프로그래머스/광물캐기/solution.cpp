#include <string>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

int solution(vector<int> picks, vector<string> minerals) {
    priority_queue<vector<int>> pq; // {value, maxValue, idx}
    priority_queue<int> pick; // dia=2, iron=1, stone=0
    for (int i = 0; i < 3; ++i)
        for (int j = 0; j < picks[i]; ++j)
            pick.push(2 - i);
    
    int canMine = pick.size();
    for (int i = 0; i < minerals.size(); i += 5) {
        if (!canMine)
            break;
        int value = 0;
        int maxValue = 1;
        
        for (int j = 0; j < 5 && j + i < minerals.size(); ++j) {
            string cur = minerals[j + i];
            if (cur == "diamond") {
                value += 25;
                maxValue = 3;
            }
            else if (cur == "iron") {
                value += 5;
                maxValue = max(maxValue, 2);
            }
            else
                ++value;
        }
        
        pq.push({value, maxValue, i});
        --canMine;
    }
    
    int ans = 0;
    
    while (!pq.empty() && !pick.empty()) {
        int i = pq.top()[2];
        int p = pick.top();
        
        pq.pop();
        pick.pop();
        
        for (int j = 0; j < 5 &&  j + i < minerals.size(); ++j) {
            string cur = minerals[i + j];
            
            if (cur == "diamond") {
                if (p == 2)
                    ++ans;
                else if (p == 1)
                    ans += 5;
                else
                    ans += 25;
            }
            else if (cur == "iron") {
                if (p)
                    ++ans;
                else
                    ans += 5;
            }
            else
                ++ans;
        }
    }
    
    return ans;
}