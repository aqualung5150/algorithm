#include <vector>
#include <cmath>

using namespace std;

int ret = 0;
vector<int> ans;

bool isValid(int idx) {
    for (int i = 0; i < idx; ++i)
        if (ans[i] == ans[idx] || abs(ans[i] - ans[idx]) == idx - i)
            return false;
    return true;
}

void dfs(int n, int idx) {
    if (idx == n) {
        ++ret;
    } else {
        for (int i = 0; i < n; ++i) {
            ans[idx] = i;
            if (isValid(idx))
                dfs(n, idx + 1);
        }
    }
}

int solution(int n) {
    ans.resize(n);
    
    dfs(n, 0);
    
    return ret;
}