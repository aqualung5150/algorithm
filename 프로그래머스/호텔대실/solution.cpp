#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int getTime(string t) {
    int ret = stoi(t.substr(0, 2)) * 60;
    ret += stoi(t.substr(3, 2));
    return ret;
}

int solution(vector<vector<string>> book_time) {
    sort(book_time.begin(), book_time.end());
    
    int ans = 0;
    for (int i = 0; i < 1440; ++i) {
        int cnt = 0;
        for (auto b : book_time) {
            int s = getTime(b[0]);
            int e = getTime(b[1]) + 10;
            if (s <= i && i < e)
                ++cnt;
        }
        ans = max(ans, cnt);
    }
    
    return ans;
}