#include <algorithm>
#include <vector>

using namespace std;

int solution(vector<int> a) {
    if (a.size() == 1)
        return 1;
    
    vector<int> l_min(a.size());
    vector<int> r_min(a.size());
    l_min[0] = a[0];
    r_min[a.size() - 1] = a[a.size() - 1];

    for (int i = 1; i < a.size(); ++i) {
        l_min[i] = min(l_min[i - 1], a[i]);
        r_min[a.size() - 1 - i] = min(r_min[a.size() - i], a[a.size() - 1 - i]);
    }
    
    int ans = 2;
    for (int i = 1; i < a.size() - 1; ++i)
        if (l_min[i - 1] >= a[i] || r_min[i + 1] >= a[i])
            ++ans;
    return ans;
}