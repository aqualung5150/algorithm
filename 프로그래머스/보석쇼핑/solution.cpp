#include <string>
#include <vector>
#include <unordered_map>
#include <set>

using namespace std;

vector<int> solution(vector<string> gems) {
    set<string> s(gems.begin(), gems.end());
    unordered_map<string, int> m;
    
    int dist = 100000;
    vector<int> ans(2);
    int left = 0;
    int right = 0;
    
    for (int i = 0; i < gems.size(); ++i) {
        ++m[gems[i]];
        if (m.size() == s.size())
            break;
        ++right;
    }
    
    while (right < gems.size()) {
        string curLeft = gems[left];
        --m[curLeft];
        
        if (m[curLeft] == 0) {
            // answer
            if (dist > right - left + 1) {
                dist = right - left + 1;
                ans[0] = left + 1;
                ans[1] = right + 1;
            }
            // move right;
            ++right;
            while (right < gems.size()) {
                string curRight = gems[right];
                ++m[curRight];
                if (curRight == curLeft)
                    break;
                ++right;
            }
        }
        ++left;
    }
    
    return ans;
}