#include <vector>
#include <cmath>

using namespace std;

long long solution(int k, int d) {
    long long ans = 0;
    for (long long a = 0; a <= d; a += k) {
        long long b = sqrt((long long)d * d - a * a);
        ans += b / k + 1;
    }
    return ans;
}