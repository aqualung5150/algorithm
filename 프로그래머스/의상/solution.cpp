#include <string>
#include <vector>
#include <unordered_map>

using namespace std;

int solution(vector<vector<string>> clothes) {
    unordered_map<string, int> m;
    
    for (int i = 0; i < clothes.size(); ++i)
        ++m[clothes[i][1]];
    
    int ans = 1;
    for (auto it = m.begin(); it != m.end(); ++it)
        ans *= it->second + 1;
    --ans;
    return ans;
}

/*
입지 않는 경우의 수를 위해 종류가 1가지씩 더 있다고 계산

아무것도 입지 않는 경우는 없기에 최종조합의수 - 1
*/