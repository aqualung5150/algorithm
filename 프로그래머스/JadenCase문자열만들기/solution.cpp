#include <string>
#include <vector>
#include <cctype>

using namespace std;

string solution(string s) {
    string ans = "";
    
    int i = 0;
    bool isFirst = true;
    for (int i = 0; i < s.length(); ++i) {
        if (s[i] == ' ') {
            ans += " ";
            isFirst = true;
        } else if (isFirst) {
            ans += toupper(s[i]);
            isFirst = false;
        } else
            ans += tolower(s[i]);
    }
    return ans;
}