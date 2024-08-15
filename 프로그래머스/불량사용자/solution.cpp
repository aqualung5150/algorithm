#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

unsigned char list = 0;
unordered_map<unsigned char, bool> m;

bool isMatched(string a, string b) {
    if (a.size() != b.size())
        return false;
    
    int i = 0;
    while (i < a.size()) {
        if (b[i] == '*' || a[i] == b[i])
            ++i;
        else
            return false;
    }
    return true;
}

void dfs(vector<string> &user, vector<string> &banned, int idx) {
    if (idx == banned.size()) {
        m[list] = true;
    } else {
        for (int i = 0; i < user.size(); ++i) {
            if (list & (1 << i))
                continue;
            
            if (isMatched(user[i], banned[idx])) {
                list += 1 << i;
                dfs(user, banned, idx + 1);
                list -= 1 << i;
            }
        }
    }
    
}

int solution(vector<string> user_id, vector<string> banned_id) {
    dfs(user_id, banned_id, 0);
    return m.size();
}