#include <iostream>
#include <vector>

using namespace std;

int solution(int n, vector<int> stations, int w) {
    int ans = 0;
    
    stations.push_back(n + w + 1);
    int left = 1;
    int right;
    for (auto station : stations) {
        right = station - w - 1;
        
        if (left > right) {
            left = station + w + 1;
            continue;
        }
        
        ans += (right - left + 1) / (w * 2 + 1);
        if ((right - left + 1) % (w * 2 + 1))
            ++ans;
        
        left = station + w + 1;
    }
    
    return ans;
}