#include <algorithm>
#include <vector>

using namespace std;

vector<int> solution(int n, long long left, long long right) {
    vector<int> ans;
    
    while (left <= right) {
        ans.push_back(max(left / n + 1, left % n + 1));
        ++left;
    }
    
    return ans;
}