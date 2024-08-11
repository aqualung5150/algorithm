#include <string>
#include <vector>

using namespace std;

vector<vector<int>> ans;

void move(int idx, int from, int to) {
    if (idx > 1)
        move(idx - 1, from, 6 - from - to);
    ans.push_back({from, to});
    if (idx > 1)
        move(idx - 1, 6 - from - to, to);
}

vector<vector<int>> solution(int n) {
    move(n, 1, 3);
    return ans;
}