#include <string>
#include <algorithm>

using namespace std;

bool isValid(string s, int left, int right) {
    while (left <= right) {
        if (s[left] != s[right])
            return false;
        ++left;
        --right;
    }
    return true;
}

int solution(string s) {
    int ans = 1;
    
    for (int i = 0; i < s.size() - 1; ++i) {
        int left = i;
        
        auto it = s.rbegin();
        while (1) {
            it = find(it, s.rend() - left, s[i]);
            if (it == s.rend())
                break;
            int right = s.rend() - it - 1;
            ++it;
            
            int len = right - left + 1;
            if (isValid(s, left, right)) {
                ans = max(ans, len);
                break;
            }
        }
    }
    
    return ans;
}