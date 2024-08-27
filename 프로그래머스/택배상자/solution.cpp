#include <stack>
#include <vector>

using namespace std;

int solution(vector<int> order) {
    stack<int> stk;
    
    int cur = 0;
    for (int i = 1; i <= order.size(); ++i) {
        stk.push(i);
        while (!stk.empty() && stk.top() == order[cur]) {
            stk.pop();
            ++cur;
        }
    }
    return cur;
}