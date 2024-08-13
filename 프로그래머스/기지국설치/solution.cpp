#include <iostream>
#include <vector>

using namespace std;

int solution(int n, vector<int> stations, int w) {
    int ans = 0;
    int cur = 0;
    for (int i = 1; i <= n;) {
        if (i >= stations[cur] - w && i <= stations[cur] + w) {
            i = stations[cur] + w + 1;
            ++cur;
        }
        else {
            ++ans;
            i += w * 2 + 1;
        }
    }
    return ans;
}