#include <string>
#include <vector>

using namespace std;

vector<int> solution(int n, int s) {
    int a, b;
    a = s / n;
    if (a == 0)
        return {-1};
    b = s % n;
    
    vector<int> ans(n, a);
    for (int i = 0; i < b; ++i)
        ++ans[n - 1 - i];
    return ans;
}