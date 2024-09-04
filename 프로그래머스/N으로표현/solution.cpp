#include <vector>
#include <iostream>
#include <unordered_set>

using namespace std;

int solution(int N, int number) {
    if (N == number)
        return 1;
    vector<unordered_set<int>> dp(9);
    dp[1].insert(N);
    
    for (int i = 2; i <= 8; ++i) {
        // append
        int next = N;
        for (int k = 1; k < i; ++k) {
            next *= 10;
            next += N;
        }
        if (next == number)
            return i;
        dp[i].insert(next);
        
        for (int j = 1; j < i; ++j) {
            for (auto a : dp[j]) {
                for (auto b : dp[i - j]) {
                    if (a == 0 || b == 0)
                        continue;
                    // plus
                    next = a + b;
                    if (next == number)
                        return i;
                    dp[i].insert(next);
                    // minus
                    next = a - b;
                    if (next == number)
                        return i;
                    dp[i].insert(next);
                    // mult
                    next = a * b;
                    if (next == number)
                        return i;
                    dp[i].insert(next);
                    // mod
                    next = a / b;
                    if (next == number)
                        return i;
                    dp[i].insert(next);
                }
            }
        }
    }
    return -1;
}