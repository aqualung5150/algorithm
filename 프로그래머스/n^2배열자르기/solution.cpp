#include <algorithm>
#include <vector>

using namespace std;

vector<int> solution(int n, long long left, long long right) {
    long long x1 = left % n;
    long long y1 = left / n;
    long long x2 = right % n;
    long long y2 = right / n;
    
    vector<int> ans;
    
    while (y1 <= y2) {
        while (x1 < n) {
            ans.push_back(max(y1 + 1, x1 + 1));
            
            if (y1 == y2 && x1 == x2)
                break;
            
            ++x1;
        }
        x1 = 0;
        ++y1;
    }
    
    return ans;
}