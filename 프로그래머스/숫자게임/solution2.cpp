#include <string>
#include <vector>
#include <algorithm>

using namespace std;

bool cmp(const int &a, const int &b) {
    return a > b;
}

int solution(vector<int> A, vector<int> B) {
    sort(A.begin(), A.end(), cmp);
    sort(B.begin(), B.end(), cmp);
    
    int ans = 0;
    int b_idx = 0;
    for (int a_idx = 0; a_idx < A.size(); ++a_idx) {
        if (A[a_idx] < B[b_idx]) {
            ++b_idx;
            ++ans;
        }
    }
    return ans;
}