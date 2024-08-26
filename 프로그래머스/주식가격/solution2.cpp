#include <vector>
#include <stack>

using namespace std;

vector<int> solution(vector<int> prices) {
    vector<int> ans(prices.size());
    stack<int> stk;
    for (int i = 0; i < prices.size(); ++i) {
        while (!stk.empty() && prices[stk.top()] > prices[i]) {
            ans[stk.top()] = i - stk.top();
            stk.pop();
        }
        stk.push(i);
    }
    while (!stk.empty()) {
        ans[stk.top()] = prices.size() - 1 - stk.top();
        stk.pop();
    }
    
    return ans;
}