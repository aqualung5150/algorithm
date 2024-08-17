#include <string>
#include <vector>

using namespace std;

vector<int> solution(string s) {
    vector<int> ans(2);
    
    if (s.length() > 1)
        ++ans[0];
    string str = "";
    for (auto c : s) {
        if (c == '1')
            str += "1";
        else
            ++ans[1];
    }
    
    while (str != "1") {
        int len = str.size();
        
        str = "";
        while (len > 0) {
            if (len % 2)
                str += "1";
            else
                ++ans[1];
            len /= 2;
        }
        
        ++ans[0];
    }
    
    return ans;
}