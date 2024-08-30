#include <vector>

using namespace std;

vector<int> solution(long long begin, long long end) {
    vector<int> ans(end - begin + 1, 1);
    
    int i = 0;
    if (begin == 1) {
        ans[0] = 0;
        ++i;
    }
    
    for (; i <= end - begin; ++i) {
        int target = begin + i;
        for (int j = 2; j * j <= target; ++j) {
            if (target % j == 0) {
                if (target / j <= 10000000) {
                    ans[i] = target / j;
                    break;
                }
                ans[i] = j;
            }
        }
    }
    
    return ans;
}