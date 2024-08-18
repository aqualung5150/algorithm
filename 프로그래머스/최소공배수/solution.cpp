#include <algorithm>
#include <vector>

using namespace std;

int solution(vector<int> arr) {
    sort(arr.begin(), arr.end(), [](const int& a, const int& b) { return a > b; });
    
    int mul = 1;
    while (1) {
        int cur = arr[0] * mul;
        
        bool valid = true;
        for (int i = 1; i < arr.size(); ++i) {
            if (cur % arr[i]) {
                valid = false;
                break;
            }
        }
        
        if (valid)
            return cur;
        
        ++mul;
    }
}