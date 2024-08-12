#include <string>
#include <vector>
#include <algorithm>

using namespace std;

long long solution(int n, vector<int> times) {
    sort(times.begin(), times.end());
    long long left = n / times.size() * times[0];
    long long right = n / times.size() * times[times.size() - 1];
    
    long long ans = 1000000000000000000;
    while (left <= right) {
        long long mid = (left + right) / 2;
        
        long long check = 0;
        for (int i = 0; i < times.size(); ++i)
            check += mid / times[i];
        
        if (check < n) {
            left = mid + 1;
        } else {
            ans = min(ans, mid);
            right = mid - 1;
        }       
    }
    return ans;
}