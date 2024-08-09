#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<int> A, vector<int> B) {
    sort(A.begin(), A.end());
    sort(B.begin(), B.end(), [](const int &a, const int &b) { return a > b; });
    
    int ans = 0;
    for (int i = 0; i < A.size(); ++i)
        ans += A[i] * B[i];
    return ans;
}