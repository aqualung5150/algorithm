#include <string>
#include <vector>
#include <algorithm>
#include <map>
#include <set>

using namespace std;

int dx[] = {1, -1, 0, 0};
int dy[] = {0, 0, 1, -1};
int ans = 0;
map<int, int> group;

void dfs(vector<vector<int>> &board, int x, int y, int groupNum) {
    board[y][x] = groupNum;
    ++group[groupNum];
    
    for (int i = 0; i < 4; ++i) {
        int nx = dx[i] + x;
        int ny = dy[i] + y;

        if (nx < 0 || nx >= board[0].size() || ny < 0 || ny >= board.size() || board[ny][nx] != 1)
            continue;

        dfs(board, nx, ny, groupNum);
    }
}

int solution(vector<vector<int>> land) {
    int curGroup = 2;
    for (int x = 0; x < land[0].size(); ++x) {
        for (int y = 0; y < land.size(); ++y) {
            if (land[y][x] == 1) {
                dfs(land, x, y, curGroup);
                ++curGroup;
            }
        }
    }
    
    int ans = 0;
    for (int x = 0; x < land[0].size(); ++x) {
        int cur = 0;
        set<int> s;
        
        for (int y = 0; y < land.size(); ++y)
            if (land[y][x] >= 2)
                s.insert(land[y][x]);
    
        for (auto it = s.begin(); it != s.end(); ++it) {
            cur += group[*it];
        }
        
        ans = max(ans, cur);
    }
    
    return ans;
}
