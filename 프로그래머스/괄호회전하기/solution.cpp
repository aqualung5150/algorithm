#include <string>
#include <stack>

using namespace std;

int solution(string s) {
    int ans = 0;
    
    for (int i = 0; i < s.size(); ++i) {
        stack<int> brackets;
        bool valid = true;
        
        for (int j = i; j < i + s.size(); ++j) {
            char bracket = s[j % s.size()];
            
            if (bracket == '(')
                brackets.push(1);
            else if (bracket == ')') {
                if (brackets.top() == 1)
                    brackets.pop();
                else {
                    valid = false;
                    break;
                }
            } else if (bracket == '{')
                brackets.push(2);
            else if (bracket == '}') {
                if (brackets.top() == 2)
                    brackets.pop();
                else {
                    valid = false;
                    break;
                }
            } else if (bracket == '[')
                brackets.push(3);
            else if (bracket == ']') {
                if (brackets.top() == 3)
                    brackets.pop();
                else {
                    valid = false;
                    break;
                }
            }
        }
        
        if (brackets.empty() && valid)
            ++ans;
    }
    
    return ans;
}