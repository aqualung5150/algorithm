#include <string>
#include <vector>
#include <queue>
#include <algorithm>

using namespace std;

struct compare{
    bool operator()(const vector<int> &a, const vector<int> &b) {
        return a[1] > b[1];
    }
};

int solution(vector<vector<int>> jobs) {
    priority_queue<vector<int>, vector<vector<int>>, compare> pq;
    int now = 0;
    int ans = 0;
    int curJob = 0;
    
    sort(jobs.begin(), jobs.end());
    
    while (curJob < jobs.size() || !pq.empty()) {
        while (curJob < jobs.size() && jobs[curJob][0] <= now) {
            pq.push(jobs[curJob]);    
            ++curJob;
        }
        
        if (!pq.empty()) {
            now += pq.top()[1];
            ans += now - pq.top()[0];
            pq.pop();
        } else
            now = jobs[curJob][0];
    }
    
    return ans / jobs.size();
}