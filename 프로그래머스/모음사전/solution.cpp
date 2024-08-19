#include <string>
#include <vector>

using namespace std;

char arr[] = {'A', 'E', 'I', 'O', 'U'};
string target = "";
int ans = 0;

bool dfs(string& word, int idx) {
    if (target == word)
        return true;
    for (int i = 0; i < 5; ++i) {
        if (idx != 5) {
            ++ans;
            target.push_back(arr[i]);
            
            if (dfs(word, idx + 1))
                return true;
            
            target.pop_back();
        }
    }
    return false;
}

int solution(string word) {
    dfs(word, 0);
    return ans;
}