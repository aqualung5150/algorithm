#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool isPossible(vector<int> &rocks, int distance, int mid, int n) {
    int curDist = 0;
    for (int i = 0; i < rocks.size(); ++i) {
        // rocks[i]와 distance의 거리도 고려해주어야 함
        if (rocks[i] - curDist < mid || distance - rocks[i] < mid) {
            --n;
            if (n < 0)
                return false;
        } else {
            curDist = rocks[i];
        }
    }
    
    if (distance - curDist < mid) {
        return false;
    }
    return true;
}

int solution(int distance, vector<int> rocks, int n) {
    sort(rocks.begin(), rocks.end());
    
    int left = 1;
    int right = distance / (rocks.size() - n + 1);
    
    int ans = 0;
    while (left <= right) {
        int mid = (left + right) / 2;
        
        if (isPossible(rocks, distance, mid, n)) {
            ans = max(ans, mid);
            left = mid + 1;
        } else {
            right = mid - 1;
        }
    }
    return ans;
}