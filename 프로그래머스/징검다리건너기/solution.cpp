#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool isPossible(vector<int> &stones, int mid, int k) {
    int count = 0;
    for (int i = 0; i < stones.size(); ++i) {
        if (stones[i] < mid)
            ++count;
        else
            count = 0;
        
        if (count >= k)
            return false;
    }
    return true;
}

int solution(vector<int> stones, int k) {
    int left = *min_element(stones.begin(), stones.end());
    int right = *max_element(stones.begin(), stones.end());
    
    int ans = 0;
    while (left <= right) {
        int mid = (left + right) / 2;
        
        if (isPossible(stones, mid, k)) {
            ans = max(ans, mid);
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    
    return ans;
}