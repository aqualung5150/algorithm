#include <string>
#include <vector>

using namespace std;

int dx[] = {1, -1, 0, 0};
int dy[] = {0, 0, 1, -1};

int solution(vector<vector<string>> board, int h, int w) {
    int ans = 0;
    for (int i = 0; i < 4; ++i) {
        int nx = dx[i] + w;
        int ny = dy[i] + h;
        
        if (nx < 0 || nx >= board[0].size() || ny < 0 || ny >= board.size() || board[ny][nx] != board[h][w])
            continue;
        ++ans;
    }
    return ans;
}