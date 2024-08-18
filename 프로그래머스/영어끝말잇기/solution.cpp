#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

vector<int> solution(int n, vector<string> words) {
    vector<int> ans(2);
    
    unordered_map<string, bool> spoken;
    spoken[words[0]] = true;
    char last = words[0][words[0].length() - 1];
    
    for (int i = 1; i < words.size(); ++i) {
        char first = words[i][0];
        
        if (spoken[words[i]] || first != last) {
            ans[0] = i % n + 1;
            ans[1] = i / n + 1;
            break;
        }
        
        spoken[words[i]] = true;
        last = words[i][words[i].length() - 1];
    }
    
    return ans;
}