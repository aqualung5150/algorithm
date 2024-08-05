#include <string>
#include <vector>
#include <unordered_map>
#include <algorithm>

using namespace std;

vector<vector<int>> solution(vector<vector<int>> data, string ext, int val_ext, string sort_by) {
    unordered_map<string, int> m;
    m["code"] = 0;
    m["date"] = 1;
    m["maximum"] = 2;
    m["remain"] = 3;
    
    vector<vector<int>> ans;
    for (int i = 0; i < data.size(); ++i)
        if (data[i][m[ext]] < val_ext)
            ans.push_back(data[i]);
    
    sort(ans.begin(), ans.end(), [i = m[sort_by]](const vector<int> &a, const vector<int> &b) { return a[i] < b[i]; });
    
    return ans;
}