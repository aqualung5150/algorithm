#include <vector>
#include <algorithm>

using namespace std;

int solution(int k, vector<int> tangerine) {
    int largest = *max_element(tangerine.begin(), tangerine.end());
    
    vector<int> v(largest + 1);
    
    for (auto& t : tangerine)
        ++v[t];
    
    sort(v.begin(), v.end(), [](const int& a, const int& b) { return a > b; });
    
    int ans = 0;
    for (auto& n : v) {
        if (k <= 0)
            break;
        
        k -= n;
        ++ans;
    }
    return ans;
}