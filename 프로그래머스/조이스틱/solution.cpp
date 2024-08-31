#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(string name) {
    int size = name.length();
    int ans = 0;
    int move = name.length();
    
    // first non-A
    int i = 0;
    while (i < size && name[i] == 'A')
        ++i;
    // second non-A
    while (i < size) {
        ans += min(name[i] - 'A', 'Z' - name[i] + 1);
        
        int j = i + 1;
        while (j < size && name[j] == 'A')
            ++j;
        
        move = min(move, min(i * 2 + size - j, (size - j) * 2 + i));
        
        i = j;
    }
    // at least one non-A
    if (ans)
        ans += move;

    return ans;
}