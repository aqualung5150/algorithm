#include <string>
#include <vector>
#include <queue>

using namespace std;

int solution(vector<int> A, vector<int> B) {
    priority_queue<int> pq1(A.begin(), A.end());
    priority_queue<int> pq2(B.begin(), B.end());
    
    int ans = 0;
    while (!pq1.empty()) {
        int a = pq1.top();
        pq1.pop();
        
        int b = pq2.top();
        if (b > a) {
            ++ans;
            pq2.pop();
        }
    }
    return ans;
}