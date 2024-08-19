#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

int solution(vector<string> want, vector<int> number, vector<string> discount) {
    unordered_map<string, int> m;
    
    for (int i = 0; i < 9; ++i)
        ++m[discount[i]];
    
    int ans = 0;
    int right = 9;
    while (right < discount.size()) {
        ++m[discount[right]];
        bool valid = true;
        for (int i = 0; i < want.size(); ++i) {
            if (m[want[i]] != number[i]) {
                valid = false;
                break;
            }
        }
        if (valid)
            ++ans;

        --m[discount[right - 9]];
        ++right;
    }
    
    return ans;
}