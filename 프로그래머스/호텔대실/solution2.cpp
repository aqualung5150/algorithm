#include <string>
#include <vector>
#include <algorithm>
#include <queue>

using namespace std;

int getTime(string t) {
    int ret = stoi(t.substr(0, 2)) * 60;
    ret += stoi(t.substr(3, 2));
    return ret;
}

int solution(vector<vector<string>> book_time) {
    sort(book_time.begin(), book_time.end());
    
    priority_queue<int, vector<int>, greater<int>> pq;
    int ans = 0;
    for (auto b : book_time) {
        int s = getTime(b[0]);
        int e = getTime(b[1]) + 10;
        
        while (!pq.empty() && pq.top() <= s)
            pq.pop();
        pq.push(e);
        ans = max(ans, (int)pq.size());
    }
    
    return ans;
}