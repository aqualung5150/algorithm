#include <unordered_set>
#include <unordered_map>
#include <vector>

using namespace std;

int solution(vector<int> topping) {
    unordered_map<int, int> m;
    
    for (auto t : topping)
        ++m[t];
    
    int ans = 0;
    unordered_set<int> s;
    for (auto t : topping) {
        s.insert(t);
        
        --m[t];
        if (m[t] == 0)
            m.erase(m.find(t));
        
        if (m.size() == s.size())
            ++ans;
    }
    return ans;
}