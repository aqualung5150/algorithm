#include <string>
#include <vector>
#include <queue>
#include <sstream>
#include <unordered_map>

using namespace std;

vector<int> solution(vector<string> operations) {
    priority_queue<int> pq_desc;
    priority_queue<int> pq_asc;
    
    unordered_map<int, int> count;
    
    for (int i = 0; i < operations.size(); ++i) {
        istringstream iss(operations[i]);
        string op, num;
        iss >> op >> num;
        int n = stoi(num);
        
        if (op == "I") {
            pq_desc.push(n);
            pq_asc.push(-n);
            ++count[n];
        } else if (n == 1 && !pq_desc.empty()) {
            --count[pq_desc.top()];
            pq_desc.pop();
        } else if (!pq_asc.empty()) {
            --count[-pq_asc.top()];
            pq_asc.pop();
        }
        
        while (!pq_desc.empty() && count[pq_desc.top()] == 0)
            pq_desc.pop();
        while (!pq_asc.empty() && count[-pq_asc.top()] == 0)
            pq_asc.pop();
    }
    
    vector<int> ans;
    if (!pq_desc.empty() && !pq_asc.empty()) {
        ans.push_back(pq_desc.top());
        ans.push_back(-pq_asc.top());
    } else {
        ans.push_back(0);
        ans.push_back(0);   
    }
    return ans;
}